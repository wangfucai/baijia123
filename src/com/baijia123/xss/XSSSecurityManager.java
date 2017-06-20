package com.baijia123.xss;

import java.util.Iterator;
import java.util.regex.Pattern;

import javax.servlet.FilterConfig;

import org.apache.log4j.Logger;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * 
 * @author WangFuCai ��ȫ�������ù����࣬��XSSSecurityManger�޸�
 */
public class XSSSecurityManager {
    private static Logger logger = Logger.getLogger(XSSSecurityManager.class);

    /**
     * REGEX��У��������ʽ
     */
    public static String REGEX;

    /**
     * �����ַ�ƥ��
     */
    private static Pattern XSS_PATTERN;

    private XSSSecurityManager() {
        // ���ɱ�ʵ����
    }

    public static void init(FilterConfig config) {
        logger.info("XSSSecurityManager init(FilterConfig config) begin");
        // ��ʼ�����������ļ�
        String xssPath = config.getServletContext().getRealPath("/") + config.getInitParameter("securityconfig");

        // ��ʼ����ȫ��������
        try {
            if (initConfig(xssPath)) {
                // ����ƥ����
                XSS_PATTERN = Pattern.compile(REGEX);
            }
        } catch (DocumentException e) {
            logger.error("��ȫ���������ļ�xss_security_config.xml�����쳣", e);
        }
        logger.info("XSSSecurityManager init(FilterConfig config) end");
    }

    /**
     * ��ȡ��ȫ��������ļ�xss_security_config.xml ����XSSSecurityConfig������Ϣ
     * 
     * @param path
     *            �����ļ���ַ eg C:/apache-tomcat-6.0.33/webapps/security_filter/WebRoot/config/xss/xss_security_config.xml
     * @return
     * @throws DocumentException
     */
    @SuppressWarnings("unchecked")
    public static boolean initConfig(String path) throws DocumentException {
        logger.info("XSSSecurityManager.initConfig(String path) begin");
        Element superElement = new SAXReader().read(path).getRootElement();
        XSSSecurityConfig.IS_CHECK_HEADER = new Boolean(getEleValue(superElement, XSSSecurityCon.IS_CHECK_HEADER));
        XSSSecurityConfig.IS_CHECK_PARAMETER = new Boolean(getEleValue(superElement, XSSSecurityCon.IS_CHECK_PARAMETER));
        XSSSecurityConfig.IS_LOG = new Boolean(getEleValue(superElement, XSSSecurityCon.IS_LOG));
        XSSSecurityConfig.IS_CHAIN = new Boolean(getEleValue(superElement, XSSSecurityCon.IS_CHAIN));
        XSSSecurityConfig.REPLACE = new Boolean(getEleValue(superElement, XSSSecurityCon.REPLACE));

        Element regexEle = superElement.element(XSSSecurityCon.REGEX_LIST);

        if (regexEle != null) {
            Iterator<Element> regexIt = regexEle.elementIterator();
            StringBuffer tempStr = new StringBuffer("^");
            // xml��cdata��ǩ��������ʱ����Ĭ����\ǰ��\����Ҫ��\\�滻Ϊ\
            while (regexIt.hasNext()) {
                Element regex = (Element) regexIt.next();
                String tmp = regex.getText();
                tmp = tmp.replaceAll("\\\\\\\\", "\\\\");
                tempStr.append(tmp);
                tempStr.append("|");
            }
            if (tempStr.charAt(tempStr.length() - 1) == '|') {
                REGEX = tempStr.substring(0, tempStr.length() - 1) + "$";
                logger.info("��ȫƥ�����" + REGEX);
            } else {
                logger.error("��ȫ���������ļ�����ʧ��:������ʽ�쳣 " + tempStr.toString());
                return false;
            }
        } else {
            logger.error("��ȫ���������ļ���û�� " + XSSSecurityCon.REGEX_LIST + " ����");
            return false;
        }
        logger.info("XSSSecurityManager.initConfig(String path) end");
        return true;

    }

    /**
     * ��Ŀ��element�л�ȡָ����ǩ��Ϣ�����Ҳ����ñ�ǩ����¼������־
     * 
     * @param element
     *            Ŀ��ڵ�
     * @param tagName
     *            �ƶ���ǩ
     * @return
     */
    private static String getEleValue(Element element, String tagName) {
        if (isNullStr(element.elementText(tagName))) {
            logger.error("��ȫ���������ļ���û�� " + XSSSecurityCon.REGEX_LIST + " ����");
        }
        return element.elementText(tagName);
    }

    /**
     * �ԷǷ��ַ������滻
     * 
     * @param text
     * @return
     */
    public static String securityReplace(String text) {
        if (isNullStr(text)) {
            return text;
        } else {
            return text.replaceAll(REGEX, XSSSecurityCon.REPLACEMENT);
        }
    }

    /**
     * ƥ���ַ��Ƿ������ַ�
     * 
     * @param text
     * @return
     */
    public static boolean matches(String text) {
        if (text == null) {
            return false;
        }
        return XSS_PATTERN.matcher(text).matches();
    }

    /**
     * �ͷŹؼ���Ϣ
     */
    public static void destroy() {
        logger.info("XSSSecurityManager.destroy() begin");
        XSS_PATTERN = null;
        REGEX = null;
        logger.info("XSSSecurityManager.destroy() end");
    }

    /**
     * �ж��Ƿ�Ϊ�մ�������ŵ�ĳ����������
     * 
     * @param value
     * @return
     */
    public static boolean isNullStr(String value) {
        return value == null || value.trim().equals("");
    }
}
