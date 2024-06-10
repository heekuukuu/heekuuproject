package org.example.service;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.example.model.WifiInfo;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WifiService {
    private static final String DB_URL = "jdbc:sqlite:wifi_info.db";
    private static final String API_URL = "http://openapi.seoul.go.kr:8088/747a584b6f6a6130383057694a6d6f/json/TbPublicWifiInfo/1/5/%EB%85%B8%EC%9B%90%EA%B5%AC/"; // Open API URL을 여기에 입력하세요

    public String getWifiInfo(String location) {
        List<WifiInfo> wifiInfos = fetchWifiInfoFromApi(location);
        saveWifiInfoToDatabase(wifiInfos);

        Gson gson = new Gson();
        return gson.toJson(wifiInfos);
    }

    private List<WifiInfo> fetchWifiInfoFromApi(String location) {
        List<WifiInfo> wifiInfos = new ArrayList<>();
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(API_URL + "?location=" + location).build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful() && response.body() != null) {
                String responseBody = response.body().string();
                JsonElement jsonElement = JsonParser.parseString(responseBody);
                JsonArray wifiInfoArray = jsonElement.getAsJsonArray();

                for (JsonElement element : wifiInfoArray) {
                    JsonObject wifiInfoJson = element.getAsJsonObject();
                    String name = wifiInfoJson.get("name").getAsString();
                    String address = wifiInfoJson.get("address").getAsString();
                    double latitude = wifiInfoJson.get("latitude").getAsDouble();
                    double longitude = wifiInfoJson.get("longitude").getAsDouble();
                    double  distance = wifiInfoJson.get("distance").getAsDouble();

                    WifiInfo wifiInfo = new WifiInfo(name, address, latitude, longitude,distance);
                    wifiInfos.add(wifiInfo);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return wifiInfos;
    }

    private void saveWifiInfoToDatabase(List<WifiInfo> wifiInfos) {
        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            String sql = "INSERT INTO wifi_info (name, address, latitude, longitude) VALUES (?, ?, ?, ?,?)";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                for (WifiInfo wifiInfo : wifiInfos) {
                    pstmt.setString(1, wifiInfo.getName());
                    pstmt.setString(2, wifiInfo.getLocation());
                    pstmt.setDouble(3, wifiInfo.getLatitude());
                    pstmt.setDouble(4, wifiInfo.getLongitude());
                    pstmt.setDouble(5, wifiInfo.getLongitude());
                    pstmt.executeUpdate();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}