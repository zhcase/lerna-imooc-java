package com.imooc.jdbc.common;

import java.sql.*;

public class DbUtils {

    /**
     *  创建新的数据库连接
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        // 1.加载并注册JDBC驱动
        Class.forName("com.mysql.cj.jdbc.Driver");
        //2.创建数据库链接
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/imooc?serverTimezone=UTC&useSSL=false&useUnicode=true&characteEncoding=UTF-8&serveTimezone=Asia", "root", "12345678");
        return conn;
    }

    /**
     *  关闭链接释放资源
     * @param rs 结果集集合
     * @param stmt Statement对象
     * @param conn Connection 对象
     * @throws SQLException
     */
    public static void closeConnection(ResultSet rs, Statement stmt, Connection conn) throws SQLException {
        //5.关闭链接 释放资源
        if (rs != null) {
            rs.close();
        }
        if (stmt != null) {
            stmt.close();
        }
        if (conn != null && !conn.isClosed()) {
            conn.close();
        }
    }
}
