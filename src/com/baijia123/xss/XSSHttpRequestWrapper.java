package com.baijia123.xss;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * @author WangFuCai request��Ϣ��װ�࣬�����жϡ�����request�����������ַ�
 */
public class XSSHttpRequestWrapper extends HttpServletRequestWrapper {
    /**
     * ��װhttp����
     * 
     * @param request
     */
    public XSSHttpRequestWrapper(HttpServletRequest request) {
        super(request);
    }

    @Override
    public String getHeader(String name) {
        String value = super.getHeader(name);
        // �����������ַ��滻���������ַ������滻
        if (XSSSecurityManager.matches(value) && XSSSecurityConfig.REPLACE) {
            value = XSSSecurityManager.securityReplace(name);
        }
        return value;
    }

    @Override
    public String[] getParameterValues(String name) {
        // TODO Auto-generated method stub
        String[] values = super.getParameterValues(name);
        if (null == values) {
            return null;
        }
        for (int i = 0; i < values.length; i++) {
            if (XSSSecurityManager.matches(values[i]) && XSSSecurityConfig.REPLACE) {
                values[i] = XSSSecurityManager.securityReplace(name);
            }
        }
        return values;
    }

    @Override
    public String getParameter(String name) {
        String value = super.getParameter(name);
        // �����������ַ��滻���������ַ������滻
        if (XSSSecurityManager.matches(value) && XSSSecurityConfig.REPLACE) {
            value = XSSSecurityManager.securityReplace(name);
        }
        return value;
    }

    /**
     * û��Υ������ݣ��ͷ���false;
     * 
     * @return
     */
    @SuppressWarnings("unchecked")
    private boolean checkHeader() {
        Enumeration<String> headerParams = this.getHeaderNames();
        while (headerParams.hasMoreElements()) {
            String headerName = headerParams.nextElement();
            String headerValue = this.getHeader(headerName);
            if (XSSSecurityManager.matches(headerValue)) {
                return true;
            }
        }
        return false;
    }

    /**
     * û��Υ������ݣ��ͷ���false;
     * 
     * @return
     */
    @SuppressWarnings("unchecked")
    private boolean checkParameter() {
        Map<String, Object> submitParams = this.getParameterMap();
        Set<String> submitNames = submitParams.keySet();
        for (String submitName : submitNames) {
            Object submitValues = submitParams.get(submitName);
            if (submitValues instanceof String) {
                if (XSSSecurityManager.matches((String) submitValues)) {
                    return true;
                }
            } else if (submitValues instanceof String[]) {
                for (String submitValue : (String[]) submitValues) {
                    if (XSSSecurityManager.matches((String) submitValue)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * û��Υ������ݣ��ͷ���false; ������Υ�����ݣ�����������Ϣ�ж��Ƿ���ת������ҳ��
     * 
     * @param response
     * @return
     * @throws IOException
     * @throws ServletException
     */
    public boolean validateParameter(HttpServletResponse response) throws ServletException, IOException {
        // ��ʼheaderУ�飬��header��Ϣ����У��
        if (XSSSecurityConfig.IS_CHECK_HEADER) {
            if (this.checkHeader()) {
                return true;
            }
        }
        // ��ʼparameterУ�飬��parameter��Ϣ����У��
        if (XSSSecurityConfig.IS_CHECK_PARAMETER) {
            if (this.checkParameter()) {
                return true;
            }
        }
        return false;
    }
}
