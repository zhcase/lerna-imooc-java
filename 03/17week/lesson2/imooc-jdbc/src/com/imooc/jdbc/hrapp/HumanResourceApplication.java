package com.imooc.jdbc.hrapp;

import com.imooc.jdbc.hrapp.command.*;
//import com.imooc.jdbc.hrapp.command.QueryCommand;


import java.sql.SQLException;
import java.util.Scanner;

public class HumanResourceApplication {
    public static void main(String[] args) throws SQLException {
        System.out.println("1-查询部门员工");
        System.out.println("2-办理员工入职");
        System.out.println("3-调整薪资");
        System.out.println("4-员工离职");

        System.out.println("请选择功能");
        Scanner in = new Scanner(System.in);
        Integer cmd = in.nextInt();
        Command command=null;
        switch (cmd) {
            case 1: //查询部门员工
                 command = new PstmQueryCommand();
                command.execute();
                break;
            case 2:
                 command=new InsertCommand();
                command.execute();
                break;
            case 3:
                command=new UpdateCommand();
                command.execute();
                break;
            case 4:
                command=new DeleteCommand();
                command.execute();
                break;
        }
    }
}
