package org.example.Servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import com.google.gson.Gson;
import org.example.WifiInfo;

import java.util.ArrayList;
import java.util.List;

@WebServlet("/wifiInfo")
public class WifiInfoServlet extends HttpServlet {

    private static final String JDBC_URL = "jdbc:sqlite:/path/to/your/sqlite/database.db";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        List<WifiInfo> wifiInfoList = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(JDBC_URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM wifi_info")) {

            while (rs.next()) {
                WifiInfo wifiInfo = new WifiInfo();
                wifiInfo.setDistance(rs.getDouble("distance"));
                wifiInfo.setManagerNumber(rs.getString("manager_number"));
                wifiInfo.setDistrict(rs.getString("district"));
                wifiInfo.setWifiName(rs.getString("wifi_name"));
                wifiInfo.setAddress(rs.getString("address"));
                wifiInfo.setInstallLocation(rs.getString("install_location"));
                wifiInfo.setInstallType(rs.getString("install_type"));
                wifiInfo.setInstallAgency(rs.getString("install_agency"));
                wifiInfo.setServiceType(rs.getString("service_type"));
                wifiInfo.setNetworkType(rs.getString("network_type"));
                wifiInfo.setInstallYear(rs.getInt("install_year"));
                wifiInfo.setIndoorOutdoor(rs.getString("indoor_outdoor"));
                wifiInfo.setWifiEnvironment(rs.getString("wifi_environment"));
                wifiInfo.setXCoordinate(rs.getDouble("x_coordinate"));
                wifiInfo.setYCoordinate(rs.getDouble("y_coordinate"));
                wifiInfo.setWorkDate(rs.getString("work_date"));
                wifiInfoList.add(wifiInfo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        Gson gson = new Gson();
        String jsonResponse = gson.toJson(wifiInfoList);
        resp.getWriter().write(jsonResponse);
    }
}