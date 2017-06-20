package com.baijia123.xss;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * 
 * @author WangFuCai ��ȫ��Ϣ�����
 */
public class XSSSecurityFilter implements Filter {

    private static Logger logger = Logger.getLogger(XSSSecurityFilter.class);

    /**
     * ���ٲ���
     */
    public void destroy() {
        logger.info("XSSSecurityFilter destroy() begin");
        XSSSecurityManager.destroy();
        logger.info("XSSSecurityFilter destroy() end");
    }

    /**
     * ��ȫ��� ��ȡ������Ϣ
     */
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // �ж��Ƿ�ʹ��HTTP
        checkRequestResponse(request, response);
        // ת��
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        // http��Ϣ��װ��
        XSSHttpRequestWrapper xssRequest = new XSSHttpRequestWrapper(httpRequest);

        // ��request��Ϣ���з�װ������У�鹤������У��ʧ�ܣ����Ƿ��ַ���������������Ϣ������־��¼�������жϴ���
        if (xssRequest.validateParameter(httpResponse)) {
            if (XSSSecurityConfig.IS_LOG) {
                // ��¼����������־
                // ��ʹ�����ݿ⡢��־���ļ��ȷ�ʽ
            }
            if (XSSSecurityConfig.IS_CHAIN) {
                httpRequest.getRequestDispatcher(XSSSecurityCon.FILTER_ERROR_PAGE).forward(httpRequest, httpResponse);
                return;
            }
        }
        chain.doFilter(xssRequest, response);
    }

    /**
     * ��ʼ������
     */
    public void init(FilterConfig filterConfig) throws ServletException {
        XSSSecurityManager.init(filterConfig);
    }

    /**
     * �ж�Request ,Response ����
     * 
     * @param request
     *            ServletRequest
     * @param response
     *            ServletResponse
     * @throws ServletException
     */
    private void checkRequestResponse(ServletRequest request, ServletResponse response) throws ServletException {
        if (!(request instanceof HttpServletRequest)) {
            throw new ServletException("Can only process HttpServletRequest");

        }
        if (!(response instanceof HttpServletResponse)) {
            throw new ServletException("Can only process HttpServletResponse");
        }
    }

}
