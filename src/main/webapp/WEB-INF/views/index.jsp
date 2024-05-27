<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.google.gson.JsonObject" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>API Data</title>
</head>
<body>
    <h1>API Data</h1>
    <%
        // 서블릿에서 전달된 데이터 가져오기
        JsonObject jsonData = (JsonObject) request.getAttribute("apiData");
        if (jsonData != null) {
            String name = jsonData.get("NAME").getAsString();
            String type = jsonData.get("TYPE").getAsString();
    %>
            <p>Name: <%= name %></p>
            <p>Type: <%= type %></p>
    <%
        } else {
    %>
            <p>No data available.</p>
    <%
        }
    %>
</body>
</html>
