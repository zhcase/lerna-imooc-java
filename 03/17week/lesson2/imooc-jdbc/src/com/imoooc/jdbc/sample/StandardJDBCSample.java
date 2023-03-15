package com.imoooc.jdbc.sample;

import com.mysql.cj.jdbc.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.concurrent.ExecutionException;

/**
 * 标准JDBC操作五步骤
 */
public class StandardJDBCSample {
    public static void main(String[] args) {
        Connection conn=null;
        try {


            // 1.加载并注册JDBC驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            // 2.创建数据库连接
             conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/imooc?userSSL=false&useUnicode=true", "root", "12345678");
            //3.创建Statement对象
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from employee where dname='研发部'");
            //4. 遍历查询结果
            while (rs.next()) {
                Integer eno = rs.getInt(1); //eno
                String ename = rs.getString("ename");
                Float salary = rs.getFloat("salary");
                Float dname = rs.getFloat("salary");
                System.out.println(dname + "-" + eno + "-" + ename + "-" + salary);
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }finally {
            try {


            if(conn!=null&&conn.isClosed()==false) {
//        5.关闭连接 释放资源
                conn.close();
            }
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }

    }

}
