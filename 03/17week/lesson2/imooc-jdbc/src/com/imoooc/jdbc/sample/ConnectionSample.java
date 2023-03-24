package com.imoooc.jdbc.sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionSample {
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/imooc?serverTimezone=UTC&useSSL=false&useUnicode=true&characteEncoding=UTF-8&serveTimezone=Asia";
            Connection conn = DriverManager.getConnection(url, "root", "12345678");
            System.out.println(conn);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
