package com.imoooc.mybatis;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;

public class MybatisTestor {
    @Test
    public  void testSqlSessionFactory() throws IOException {
//        利用Reader加载classpath下的mybatis-config.xml核心配置文件
        Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
//        初始化SqlSessionFactory对象，同时解析mybatis-config.xml文件
        SqlSessionFactory sqlSessionFactory= new SqlSessionFactoryBuilder().build(reader);
        System.out.println("SessionFactory加载成功");
        SqlSession sqlSession=null;
        Connection connection=null;
        try {
//        创建SqlSession对象,SqlSession是JDBC的扩展类 用于与数据库交互
            sqlSession = sqlSessionFactory.openSession();
//        创建数据库连接（测试）
            connection = sqlSession.getConnection();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if(sqlSession!=null){
                //如果type="POOLED" 代表使用连接池 close则是将连接回收到连接池中
                // 如果type="UNPOOLED, 代表直连，close则会调用Connection.close()方法关闭连接
                sqlSession.close();
            }
        }
        System.out.println(connection);
    }
}
