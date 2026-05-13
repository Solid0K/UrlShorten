package com.krishu.urlshortenupgraded;

import com.krishu.urlshortenupgraded.Service.UrlService;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/r/*")
public class RedirectServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String path=request.getPathInfo();
        if(path==null || path.equals("/")){
            response.sendRedirect(request.getContextPath());
            return;
        }
        String shortenUrl=path.substring(1);
        try {
            String originalUrl= UrlService.resolver(shortenUrl);
            if(originalUrl!=null){
                response.sendRedirect(originalUrl);
            }else{
                response.getWriter().println("Page not found");
            }
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
