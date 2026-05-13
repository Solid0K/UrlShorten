package com.krishu.urlshortenupgraded;

import com.krishu.urlshortenupgraded.Service.UrlService;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/shorten")
public class ShortenUrlServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response){
        String originalUrl=request.getParameter("url");
        try {
            String shortenUrl= UrlService.getShortenUrl(originalUrl);
            response.getWriter().println("Short URL: "+request.getContextPath() +"/r/"+shortenUrl);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
