package com.krishu.urlshortenupgraded.DAO;

import com.krishu.urlshortenupgraded.DB.DBConnection;
import com.krishu.urlshortenupgraded.Models.url;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DAO {
    private static final Connection connection;
    private static PreparedStatement statement;

    static {
        try {
            connection = DBConnection.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    static boolean isExist(String code){
        String sql="select * from urls where short_url=?";
        try{
            statement=connection.prepareStatement(sql);
            statement.setString(1,code);
            ResultSet set=statement.executeQuery();
            if(set.next()){
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public static void saveUrl(url URL) throws SQLException {
        String sql="insert into urls(original_url,short_url) values(?,?)";
        try{
            statement=connection.prepareStatement(sql);
            statement.setString(1,URL.getOriginal_url());
            statement.setString(2,URL.getShort_url());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static url getByShortUrl(String code){
        String sql="select * from urls where short_url=?";
        try{
            statement=connection.prepareStatement(sql);
            statement.setString(1,code);
            ResultSet set=statement.executeQuery();
            if(set.next()){
                url n_url=new url();
                n_url.setId(set.getLong("id"));
                n_url.setOriginal_url(set.getString("original_url"));
                n_url.setShort_url(code);
                return n_url;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
