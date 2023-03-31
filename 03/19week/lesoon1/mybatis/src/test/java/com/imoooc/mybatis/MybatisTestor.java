package com.imoooc.mybatis;

import com.com.imoooc.utils.MybatisUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.imooc.mybatis.dto.GoodsDTO;
import com.imooc.mybatis.entity.Goods;
import com.imooc.mybatis.entity.GoodsDetail;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.util.*;

public class MybatisTestor {
    @Test
    public void testSqlSessionFactory() throws IOException {
//        利用Reader加载classpath下的mybatis-config.xml核心配置文件
        Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
//        初始化SqlSessionFactory对象，同时解析mybatis-config.xml文件
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        System.out.println("SessionFactory加载成功");
        SqlSession sqlSession = null;
        Connection connection = null;
        try {
//        创建SqlSession对象,SqlSession是JDBC的扩展类 用于与数据库交互
            sqlSession = sqlSessionFactory.openSession();
//        创建数据库连接（测试）
            connection = sqlSession.getConnection();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (sqlSession != null) {
                //如果type="POOLED" 代表使用连接池 close则是将连接回收到连接池中
                // 如果type="UNPOOLED, 代表直连，close则会调用Connection.close()方法关闭连接
                sqlSession.close();
            }
        }
        System.out.println(connection);
    }

    @Test
    public void testSelectAll() {
        SqlSession session = null;

        try {
            session = MybatisUtils.openSession();
            List<Goods> list = session.selectList("goods.selectAll");
            for (Goods g : list) {
                System.out.println(g.getTitle());
            }

        } catch (Exception e) {
            throw e;
        } finally {
            MybatisUtils.closeSession(session);
        }
    }

    @Test
    public void testSelectById() {
        SqlSession session = null;
        try {
            session = MybatisUtils.openSession();
            Goods goods = session.selectOne("goods.selectById", 1603);
            System.out.println(goods.getTitle());

        } catch (Exception e) {
            throw e;
        }

    }

    @Test
    public void testSelectByPriceRange() {
        SqlSession session = null;
        try {
            session = MybatisUtils.openSession();
            Map param = new HashMap();
            param.put("min", 100);
            param.put("max", 500);
            param.put("limit", 10);
            List<Goods> list = session.selectList("goods.selectByPriceRange", param);
            for (Goods g : list) {
                System.out.println(g.getTitle() + ":" + g.getCurrentPrice());
            }
        } catch (Exception e) {
            throw e;
        }
    }

    @Test
    public void testSelectGoodsMap() {
        SqlSession session = null;
        try {
            session = MybatisUtils.openSession();
            List<Map> list = session.selectList("goods.selectGoodsMap");
            for (Map map : list) {
                System.out.println(map);
            }
        } catch (Exception e) {
            throw e;
        }
    }

    @Test
    public void testSelectGoodsDTO() {
        SqlSession session = null;
        try {
            session = MybatisUtils.openSession();
            List<GoodsDTO> list = session.selectList("goods.selectGoodsDTO");
            for (GoodsDTO g : list) {
//                System.out.println(g.getGoods().getTitle());
            }
        } catch (Exception e) {
            throw e;
        }

    }

    @Test
    public void testInsert() throws Exception {
        SqlSession session = null;
        try {
            session = MybatisUtils.openSession();
            Goods goods = new Goods();
            goods.setTitle("测试商品");
            goods.setSubTitle("测试子标题");
            goods.setOriginalCost(200f);
            goods.setCurrentPrice(100f);
            goods.setDiscount(0.5f);
            goods.setIsFreeDelivery(1);
            goods.setCategoryId(43);
            // insert() 方法返回值代表本次成功插入的记录总数
            int num = session.insert("goods.insert", goods);
            session.commit(); //提交事务数据
            System.out.println(goods.getGoodsId());
        } catch (Exception e) {
            if (session != null) {
                session.rollback(); //回滚事务
            }
            throw e;
        }
    }

    @Test
    public void testUpdate() throws Exception {
        SqlSession session = null;
        try {
            session = MybatisUtils.openSession();
            Goods goods = session.selectOne("goods.selectById", 739);
            goods.setTitle("更新测试商品");
            session.update("goods.update", goods);
            session.commit();
        } catch (Exception e) {
        }
    }

    @Test
    public void testDelete() throws Exception {
        SqlSession session = null;
        try {
            session = MybatisUtils.openSession();
            int num = session.delete("goods.delete", 739);
            session.commit();
            System.out.println(num);
        } catch (Exception e) {
        }
    }


    @Test
    public void testModle() throws Exception {
        SqlSession session = null;
        try {
            session = MybatisUtils.openSession();


            session.commit();
        } catch (Exception e) {
            session.rollback();
        }
    }

    @Test
    public void testDynamicSQL() throws Exception {
        SqlSession session = null;
        try {
            session = MybatisUtils.openSession();
            Map param = new HashMap();
            param.put("categoryId", 44);
            param.put("currentPrice", 500);
            //查询条件
            List<Goods> list = session.selectList("goods.dynamicSQL", param);
            for (Goods g : list) {
                System.out.println(g.getTitle() + ":");
            }
            session.commit();
        } catch (Exception e) {
        }
    }

    @Test
    public void testLv1Cache() {
        SqlSession session = null;
        try {
            session = MybatisUtils.openSession();
            Goods goods = session.selectOne("goods.selectById", 1603);
            Goods goods1 = session.selectOne("goods.selectById", 1603);

            System.out.println(goods.getTitle());
            System.out.println(goods.hashCode() + ":" + goods1.hashCode());

        } catch (Exception e) {
            throw e;
        }
        try {
            session = MybatisUtils.openSession();
            Goods goods = session.selectOne("goods.selectById", 1603);
            session.commit();
            Goods goods1 = session.selectOne("goods.selectById", 1603);

            System.out.println(goods.getTitle());
            System.out.println(goods.hashCode() + ":" + goods1.hashCode());

        } catch (Exception e) {
            throw e;
        }
    }

    @Test
    public void testLv2Cache() {
        try {
            SqlSession session = MybatisUtils.openSession();
            Goods goods = session.selectOne("goods.selectById", 1603);
            System.out.println(goods.hashCode());

        } catch (Exception e) {
            throw e;
        }

        try {
            SqlSession session1 = MybatisUtils.openSession();
            Goods goods = session1.selectOne("goods.selectById", 1603);
            System.out.println(goods.hashCode());

        } catch (Exception e) {
            throw e;
        }
    }

    @Test
    public void testOneToMany() throws Exception {
        SqlSession session = null;
        try {
            session = MybatisUtils.openSession();

            List<Goods> list = session.selectList("goods.selectOneToMany");

            for (Goods goods : list) {
                System.out.println(goods.getTitle() + ":" + goods.getGoodsDetails());
            }

        } catch (Exception e) {
            throw e;

        }
    }


    //    有问题
    @Test
    public void testManyToOne() throws Exception {
        SqlSession session = null;
        try {
            session = MybatisUtils.openSession();

            List<GoodsDetail> list = session.selectList("goodsDetail.selectManyToOne");

            for (GoodsDetail gd : list) {
                System.out.println(gd.getGdId() + ":" + gd.getGoods().getTitle());
                System.out.println(gd.getGoods());
            }

        } catch (Exception e) {
            throw e;

        }
    }

    @Test
    public void testSelectPage() throws Exception {
        SqlSession session = null;
        try {
            session = MybatisUtils.openSession();
//            startPage 方法会自动将下一次查询进行分页
            PageHelper.startPage(2, 10);
            Page<Goods> page = (Page) session.selectList("goods.selectPage");
            System.out.println("总页数:" + page.getPages());
            System.out.println("总记录数:" + page.getTotal());
            System.out.println("开始行号    :" + page.getStartRow());
            System.out.println("结束行号:" + page.getEndRow());
            System.out.println("当前页码:" + page.getPageNum());
            List<Goods> data = page.getResult();
            for (Goods g : data) {
                System.out.println(g.getTitle());
            }
            session.commit();
        } catch (Exception e) {
            session.rollback();
        }
    }

    @Test
    public void testBatchInsert() throws Exception {
        SqlSession session = null;
        try {
            long st = new Date().getTime();
            session = MybatisUtils.openSession();
            List list = new ArrayList();
            for (int i = 0; i < 100;i++){
                Goods goods = new Goods();
                goods.setTitle("测试商品");
                goods.setSubTitle("测试子标题");
                goods.setOriginalCost(200f);
                goods.setCurrentPrice(100f);
                goods.setDiscount(0.5f);
                goods.setIsFreeDelivery(1);
                goods.setCategoryId(43);
                list.add(goods);
            }
//            for( Goods g:list){
//                System.out.println(g);
//            }
            session.insert("goods.batchInsert",list);
        } catch (Exception e) {
        }
    }

}
