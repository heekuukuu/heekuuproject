package org.example.controller;

import org.example.service.WifiService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/wifi")
public class WifiServlet extends HttpServlet {
    private WifiService wifiService = new WifiService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String location = request.getParameter("location");
        String wifiInfo = wifiService.getWifiInfo(location);
        response.setContentType("application/json");
        response.getWriter().write(wifiInfo);
    }
}