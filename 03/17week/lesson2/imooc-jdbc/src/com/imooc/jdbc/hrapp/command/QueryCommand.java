package com.imooc.jdbc.hrapp.command;

import java.sql.*;
import java.util.Scanner;

public class QueryCommand implements Command {
    @Override
    public void execute() {
        System.out.println("打印请输入部门名称");
        Scanner in = new Scanner(System.in);
        String pdname = in.next();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            // 1.加载并注册JDBC驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            //2.创建数据库链接
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/imooc?serverTimezone=UTC&useSSL=false&useUnicode=true&characteEncoding=UTF-8&serveTimezone=Asia", "root", "12345678");
            //3.创建Statement数据库对象
            stmt = conn.createStatement();
            rs = stmt.executeQuery("select*from employee where dname='" + pdname + "'");
            //  4.遍历查询结果
            //  rs.next() 返回布尔值 代表是否存在下一条记录
            // 如果有 返回true 同时结果集提取下一条记录
            // 如果没有 返回false 循环就会停止
            while (rs.next()) {
                Integer eno = rs.getInt(1); // JDBC中字段索引从1开始 而非0
                String ename = rs.getString("ename");
                Float salary = rs.getFloat("salary");
                String dname = rs.getString("dname");
                System.out.println(dname + "-" + eno + "-" + ename + "-" + salary);

            }
            //5.关闭链接释放资源
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            //5.关闭链接 释放资源
//            if (rs != null) {
//                rs.close();
//            }
            if (stmt != null) {
//                stmt.close();
            }
//            if (conn != null && !conn.isClosed()) {
////                conn.close();
//            }
        }
    }
}
