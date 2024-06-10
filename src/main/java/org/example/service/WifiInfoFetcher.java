package org.example.service;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import java.io.IOException;

public class WifiInfoFetcher {
    private static final String API_URL = "http://openapi.seoul.go.kr:8088/747a584b6f6a6130383057694a6d6f/json/TbPublicWifiInfo/1/5/%EB%85%B8%EC%9B%90%EA%B5%AC/";

    public static void main(String[] args) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(API_URL)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                String jsonData = response.body().string();
                System.out.println(jsonData);
            } else {
                System.err.println("API 요청 실패: " + response.code());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}