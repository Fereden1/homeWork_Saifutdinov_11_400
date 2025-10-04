package com.saifutdinov.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class Sample {

    public static void main(String[] args) {
        // GET request
        try {
            URL url = new URL("https://jsonplaceholder.typicode.com/posts?userId=1");
            HttpURLConnection getConn = (HttpURLConnection) url.openConnection();
            getConn.setConnectTimeout(5000);
            getConn.setReadTimeout(5000);
            getConn.setRequestMethod("GET");
            getConn.setRequestProperty("Content-Type", "application/json");

            System.out.println(fetchResponse(getConn));
            getConn.disconnect();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // POST request
        try {
            URL postUrl = new URL("https://gorest.co.in/public/v2/users");
            HttpURLConnection postConn = (HttpURLConnection) postUrl.openConnection();
            postConn.setRequestMethod("POST");
            postConn.setRequestProperty("Content-Type", "application/json");
            postConn.setRequestProperty("Accept", "application/json");
            postConn.setRequestProperty("Authorization", "Bearer 58762cdab4e248c10d165f6bbe89d18a444dff00267b6cfcec49acf9dceb94b7");
            postConn.setDoOutput(true);

            String payload = "{\"name\":\"Sen. Anala Iyer\",\"email\":\"dsen_anala_iyer123@stroman-leannon.test\",\"gender\":\"female\",\"status\":\"active\"}";
            try (OutputStream os = postConn.getOutputStream()) {
                byte[] data = payload.getBytes(StandardCharsets.UTF_8);
                os.write(data, 0, data.length);
            }

            System.out.println(postConn.getResponseCode());
            System.out.println(fetchResponse(postConn));
            postConn.disconnect();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String fetchResponse(HttpURLConnection conn) {
        if (conn == null) {
            return null;
        }
        try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
            StringBuilder result = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                result.append(line);
            }
            return result.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
