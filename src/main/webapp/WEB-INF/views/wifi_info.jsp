<%@ page import="java.sql.*, java.util.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>WiFi Information</title>
</head>
<body>
    <h1>WiFi Information</h1>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Address</th>
            <th>Latitude</th>
            <th>Longitude</th>
            <th>Installed Date</th>
        </tr>
        <%
            Connection conn = null;
            Statement stmt = null;
            ResultSet rs = null;
            try {
                Class.forName("org.sqlite.JDBC");
                String dbPath = application.getRealPath("/") + "WEB-INF/db/mydatabase.db";
                conn = DriverManager.getConnection("jdbc:sqlite:" + dbPath);
                stmt = conn.createStatement();
                rs = stmt.executeQuery("SELECT * FROM wifi_info");

                while (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    String address = rs.getString("address");
                    double latitude = rs.getDouble("latitude");
                    double longitude = rs.getDouble("longitude");
                    String installedDate = rs.getString("installed_date");
        %>
        <tr>
            <td><%= id %></td>
            <td><%= name %></td>
            <td><%= address %></td>
            <td><%= latitude %></td>
            <td><%= longitude %></td>
            <td><%= installedDate %></td>
        </tr>
        <%
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (rs != null) try { rs.close(); } catch (SQLException e) {}
                if (stmt != null) try { stmt.close(); } catch (SQLException e) {}
                if (conn != null) try { conn.close(); } catch (SQLException e) {}
            }
        %>
    </table>
</body>
</html>
