package com.aynu.helomybatis.test;

import com.aynu.helomybatis.dao.EmployeeMapper;
import com.aynu.helomybatis.pojo.Employee;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

/**
 *
 * 2.SqlSession代表和数据库的一次会话，用完必须关闭
 * 3.SqlSession和connection一样，都是非线程安全的，每次都应该去获取新的对象
 * 4.mapper接口没有实现类，但是mybatis会为这个接口生成一个代理对象
 *          （将接口与xml进行绑定）
 *          EmployeeMapper empMapper = sqlSession.getMapper(EmployeeMapper.class);
 * 5.两个重要的配置文件：
 *          mybatis的全局配置文件，包含数据连接池信息，事务管理其信息等系统运行信息
 *          sql映射文件：保存了每一个sql语句的映射信息
 *                          （将sql抽取出来）
 * @author hqlsyq
 * @creat 2022-09-17 10:55
 */

public class MyBatis {
//    private SqlSession sqlSession; 不是线程安全的，不要把他设为共享成员变量，每次使用都应该获取新的对象

    public SqlSessionFactory getSqlSessionFactory() throws IOException{
        String resource = "conf/mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        return new SqlSessionFactoryBuilder().build(inputStream);
    }
    /**
     * 1.根据xml配置文件（全局配置文件）创建一个SqlSessionFactory对象
     *      有数据源一些运行环境信息
     * 2.sql映射文件：配置了每一个sql，以及sql的封装规则等。
     * 3.将sql映射文件注册在全局配置文件中
     * 4.写代码：
     *      1）.根据权及配置文件得到SqlSessionFactory
     *      2）.使用sqlSession工厂获取到sqlSession对象使用他来进行增删改查
     *          一个sqlSession就是代表和数据库的一次会话，用完关闭
     *      3）.使用sql的唯一标志来告诉mybatis执行哪个sql，sql都是保存在sql映射文件中的
     * @throws IOException
     */
    @Test
    public void test() throws IOException {
        String resource = "conf/mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            Employee employee = sqlSession.selectOne("com.aynu.helomybatis.EmployeeMapper.selectEmp", 1);
            System.out.println(employee);
        } finally {
            sqlSession.close();
        }

    }

    @Test
    public void test01() throws IOException {
        //1、获取sqlSessionFactory
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        //2、获取sqlSession对象
        SqlSession openSession = sqlSessionFactory.openSession();
       try {
           //3、获取接口实现类对象
           //会为接口自动创建一个代理对象，代理对象执行增删改查
           EmployeeMapper mapper = openSession.getMapper(EmployeeMapper.class);

           Employee employee = mapper.getEmpById(1);

           System.out.println(employee);
       }finally {
           openSession.close();
       }
    }
}
