package com.saifutdinov.http;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class HttpClientImpl implements HttpClient {

    @Override
    public String get(String url, Map<String, String> headers, Map<String, String> params) {
        if (params != null && !params.isEmpty()) {
            StringBuilder query = new StringBuilder(url).append("?");
            for (Map.Entry<String, String> entry : params.entrySet()) {
                query.append(URLEncoder.encode(entry.getKey(), StandardCharsets.UTF_8))
                        .append("=")
                        .append(URLEncoder.encode(entry.getValue(), StandardCharsets.UTF_8))
                        .append("&");
            }
            url = query.substring(0, query.length() - 1);
        }

        HttpURLConnection conn = openConnection(url, "GET");
        applyHeaders(conn, headers);
        setTimeouts(conn);

        String result = extractResponse(conn);
        conn.disconnect();
        return result;
    }

    @Override
    public String post(String url, Map<String, String> headers, Map<String, String> data) {
        HttpURLConnection conn = openConnection(url, "POST");
        applyHeaders(conn, headers);
        writeBody(conn, data);

        String result = extractResponse(conn);
        conn.disconnect();
        return result;
    }

    @Override
    public String put(String url, Map<String, String> headers, Map<String, String> data) {
        HttpURLConnection conn = openConnection(url, "PUT");
        applyHeaders(conn, headers);
        writeBody(conn, data);

        String result = extractResponse(conn);
        conn.disconnect();
        return result;
    }

    @Override
    public String delete(String url, Map<String, String> headers, Map<String, String> data) {
        HttpURLConnection conn = openConnection(url, "DELETE");
        applyHeaders(conn, headers);
        writeBody(conn, data);

        String result = extractResponse(conn);
        conn.disconnect();
        return result;
    }

    private HttpURLConnection openConnection(String url, String method) {
        try {
            URL target = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) target.openConnection();
            conn.setRequestMethod(method);
            return conn;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void applyHeaders(HttpURLConnection conn, Map<String, String> headers) {
        if (headers != null) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                conn.setRequestProperty(entry.getKey(), entry.getValue());
            }
        }
    }

    private void writeBody(HttpURLConnection conn, Map<String, String> data) {
        conn.setDoOutput(true);
        try (BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(conn.getOutputStream(), StandardCharsets.UTF_8))) {
            writer.write(new ObjectMapper().writeValueAsString(data));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void setTimeouts(HttpURLConnection conn) {
        conn.setConnectTimeout(5000);
        conn.setReadTimeout(5000);
    }

    private static String extractResponse(HttpURLConnection conn) {
        if (conn == null) {
            return null;
        }
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
            StringBuilder result = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }
            return result.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
