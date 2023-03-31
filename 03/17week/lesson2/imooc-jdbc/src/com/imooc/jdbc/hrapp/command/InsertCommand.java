package com.imooc.jdbc.hrapp.command;

import com.imooc.jdbc.common.DbUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.Scanner;

public class InsertCommand implements  Command {
    @Override
    public  void execute() throws SQLException {
        Scanner in =  new Scanner(System.in);
        System.out.println("请输入员工编号");
        Integer eno = in.nextInt();
        System.out.println("请输入员工姓名：");
        String ename=in.next();
        System.out.println("请输入员工薪资");
        Float salary =in.nextFloat();
        System.out.println("请输入属于部门");
        String dname=in.next();
        System.out.println("请输入入职日期");

        String hiredate=in.next();
        Connection conn=null;
        PreparedStatement pstmt=null;
        try {
            conn= DbUtils.getConnection();
            String sql="insert into employee(eno,ename,salary,dname,hiredate) values(?,?,?,?,?)";
             pstmt = conn.prepareStatement(sql);
             pstmt.setInt(1,eno);
             pstmt.setString(2,ename);
             pstmt.setFloat(3,salary);
             pstmt.setString(4,dname);
            pstmt.setString(5,hiredate);
            int cnt = pstmt.executeUpdate();//所有写操作都使用executeUpdate
            System.out.println("cnt:"+cnt);
            System.out.println(ename+"员工股入职手续已办理");
        }catch (SQLException e){
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            DbUtils.closeConnection(null,pstmt,conn);
        }
    }
}
