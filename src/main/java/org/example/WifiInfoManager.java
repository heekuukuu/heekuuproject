package org.example;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class WifiInfoManager {
    public List<WifiInfo> fetchAllWifiInfo() {
        List<WifiInfo> wifiInfoList = new ArrayList<>();
        String sql = "SELECT * FROM wifi_info";

        try (Connection conn = DatabaseConnector.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                WifiInfo wifiInfo = new WifiInfo(
                        rs.getString("name"),
                        rs.getString("address"),
                        rs.getDouble("latitude"),
                        rs.getDouble("longitude"),
                        rs.getString("installed_date")
                );
                wifiInfoList.add(wifiInfo);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return wifiInfoList;
    }
}
