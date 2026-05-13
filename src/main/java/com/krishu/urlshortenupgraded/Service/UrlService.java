package com.krishu.urlshortenupgraded.Service;

import com.krishu.urlshortenupgraded.DAO.DAO;
import com.krishu.urlshortenupgraded.Models.UrlShorten;
import com.krishu.urlshortenupgraded.Models.url;

import java.sql.SQLException;

public class UrlService {
    public static String getShortenUrl(String original_url) throws SQLException {
        String short_url= UrlShorten.generate();
        url n_url=new url();
        n_url.setOriginal_url(original_url);
        n_url.setShort_url(short_url);
        DAO.saveUrl(n_url);
        return short_url;
    }

    public static String resolver(String short_url) throws SQLException {
        url g_url= DAO.getByShortUrl(short_url);
        if(g_url==null){
            return null;
        }
        return g_url.getOriginal_url();
    }
}
