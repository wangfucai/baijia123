package com.baijia123.beanmap;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.StringUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JacksonUtil {
    private static final ObjectMapper mapper = new ObjectMapper();
    public static final String JSONP_CALLBACK = "callback";

    static {
        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public static String toJson(Object obj) {
        try {
            return mapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T toObject(String json, Class<T> requiredType) {
        try {
            return mapper.readValue(json, requiredType);
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }

    public static <T> T toObject(String json, TypeReference<T> type) {
        try {
            return mapper.readValue(json, type);
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }

    public static String toJsonpIfNeed(HttpServletRequest request, HttpServletResponse response, Object obj) {
        StringBuilder jsonStr = new StringBuilder();
        boolean isJsonp = !StringUtils.hasText(request.getParameter(JSONP_CALLBACK));
        if (isJsonp) {
            response.setContentType("application/x-javascript");
            jsonStr.append(request.getParameter(JSONP_CALLBACK) + "(");
        }
        jsonStr.append(toJson(obj));
        if (isJsonp) {
            jsonStr.append(")");
        }
        return jsonStr.toString();
    }

    public static void writeJson(HttpServletRequest request, HttpServletResponse response, Object result) {
        PrintWriter printWriter = null;
        response.setContentType("text/plain; charset=UTF-8");

        try {
            printWriter = response.getWriter();
            String json = toJsonpIfNeed(request, response, result);
            printWriter.write(json);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (null != printWriter) {
                printWriter.close();
            }
        }
    }

}
