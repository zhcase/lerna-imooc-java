package com.imooc.jdbc.hrapp.command;

import com.imooc.jdbc.common.DbUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * 删除员工数据
 */
public class DeleteCommand implements Command {
    @Override
    public void execute() throws SQLException {
        Scanner in = new Scanner(System.in);
        System.out.println("请输入员工编号");
        int eno = in.nextInt();

        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = DbUtils.getConnection();
            String sql = "delete from employee where eno=?";
            pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, eno);
            int cnt = pstmt.executeUpdate();
            if (cnt == 1) {
                System.out.println("员工离职手续已完成");
            } else {
                System.out.println("未找到eno" + eno + "员工数据");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            DbUtils.closeConnection(null, pstmt, conn);
        }
    }
}
