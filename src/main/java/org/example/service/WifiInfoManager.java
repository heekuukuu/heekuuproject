package org.example.service;

import org.example.model.WifiInfo;
import org.example.util.DatabaseConnector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class WifiInfoManager {

    public void saveWifiInfo(WifiInfo wifiInfo) {
        String sql = "INSERT INTO wifi_info (name, location, longitude, latitude,distance) VALUES (?, ?, ?, ?,?)";

        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, wifiInfo.getName());
            pstmt.setString(2, wifiInfo.getLocation());
            pstmt.setDouble(3, wifiInfo.getLongitude());
            pstmt.setDouble(4, wifiInfo.getLatitude());
            pstmt.setDouble(5, wifiInfo.getLatitude());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public WifiInfo getWifiInfoById(int id) {
        String sql = "SELECT * FROM wifi_info WHERE id = ?";
        WifiInfo wifiInfo = null;

        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                String name = rs.getString("name");
                String location = rs.getString("location");
                double longitude = rs.getDouble("longitude");
                double latitude = rs.getDouble("latitude");
                double distance = rs.getDouble("distance");

                wifiInfo = new WifiInfo(name, location, longitude, latitude,distance);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return wifiInfo;
    }
}