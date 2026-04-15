package com.csf.pagmodulo.infrastructure.web.filter;

import jakarta.servlet.ReadListener;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;

import java.io.*;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class CachedBodyHttpServletRequest extends HttpServletRequestWrapper {

    private final byte[] cachedBody;
    private Map<String, String[]> parameterMap;

    public CachedBodyHttpServletRequest(HttpServletRequest request) throws IOException {
        super(request);
        this.cachedBody = request.getInputStream().readAllBytes();
    }

    @Override
    public ServletInputStream getInputStream() {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(cachedBody);
        return new ServletInputStream() {
            public int read() { return byteArrayInputStream.read(); }
            public boolean isFinished() { return byteArrayInputStream.available() == 0; }
            public boolean isReady() { return true; }
            public void setReadListener(ReadListener rl) {}
        };
    }

    @Override
    public BufferedReader getReader() {
        return new BufferedReader(new InputStreamReader(getInputStream()));
    }

    public byte[] getCachedBody() {
        return cachedBody;
    }

    @Override
    public String getParameter(String name) {
        String[] values = getParameterMap().get(name);
        return (values != null && values.length > 0) ? values[0] : null;
    }

    @Override
    public Map<String, String[]> getParameterMap() {
        if (parameterMap != null) return parameterMap;

        Map<String, List<String>> merged = new LinkedHashMap<>();
        super.getParameterMap().forEach((k, v) ->
                merged.computeIfAbsent(k, x -> new ArrayList<>()).addAll(Arrays.asList(v)));

        String contentType = getContentType();
        if (contentType != null && contentType.contains("application/x-www-form-urlencoded") && cachedBody.length > 0) {
            String body = new String(cachedBody, StandardCharsets.UTF_8);
            for (String pair : body.split("&")) {
                String[] kv = pair.split("=", 2);
                if (kv.length == 2) {
                    String key = URLDecoder.decode(kv[0], StandardCharsets.UTF_8);
                    String val = URLDecoder.decode(kv[1], StandardCharsets.UTF_8);
                    merged.computeIfAbsent(key, x -> new ArrayList<>()).add(val);
                }
            }
        }

        parameterMap = new LinkedHashMap<>();
        merged.forEach((k, v) -> parameterMap.put(k, v.toArray(new String[0])));
        return Collections.unmodifiableMap(parameterMap);
    }

    @Override
    public Enumeration<String> getParameterNames() {
        return Collections.enumeration(getParameterMap().keySet());
    }

    @Override
    public String[] getParameterValues(String name) {
        return getParameterMap().get(name);
    }
}


