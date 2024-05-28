<%@ page import="java.sql.*, java.util.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Location History</title>
</head>
<body>
    <h1>Location History</h1>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Search Location</th>
            <th>Field3</th>
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
                rs = stmt.executeQuery("SELECT * FROM location_history");

                while (rs.next()) {
                    int id = rs.getInt("id");
                    String searchLocation = rs.getString("search_location");
                    String field3 = rs.getString("Field3");
        %>
        <tr>
            <td><%= id %></td>
            <td><%= searchLocation %></td>
            <td><%= field3 %></td>
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
