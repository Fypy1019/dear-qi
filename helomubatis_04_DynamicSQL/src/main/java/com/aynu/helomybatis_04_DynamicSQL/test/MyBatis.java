package com.aynu.helomybatis_04_DynamicSQL.test;

import com.aynu.helomybatis_04_DynamicSQL.dao.*;
import com.aynu.helomybatis_04_DynamicSQL.pojo.Department;
import com.aynu.helomybatis_04_DynamicSQL.pojo.Employee;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

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
            Employee employee = sqlSession.selectOne("com.aynu.helomybatis_03.EmployeeMapper.selectEmp", 1);
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

           Employee employee = mapper.getEmpById(5);

           System.out.println(employee);
       }finally {
           openSession.close();
       }
    }

    @Test
    public void test02() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession openSession = sqlSessionFactory.openSession();
        try {
            EmployeeMapperAnnotation mapper = openSession.getMapper(EmployeeMapperAnnotation.class);
            Employee empById = mapper.getEmpById(5);
            System.out.println(empById);
        }finally {
            openSession.close();
        }
    }

    /**
     * 测试增删改查
     * @throws IOException
     *
     *1、 mybatis允许增删改直接定义以下类型返回值
     *      Integer、Long、Boolean
     *2、我们需要手动提交数据
     *      sqlSessionFactory.openSession();----->手动提交
     *      sqlSessionFactory.openSession(true);----->自动提交
     *
     */
    @Test
    public void test03() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        //1.获取到的SqlSession不会自动提交出去
        SqlSession openSession = sqlSessionFactory.openSession(true);
        try {
            EmployeeMapper mapper = openSession.getMapper(EmployeeMapper.class);
            //测试添加
//            Employee employee = new Employee(null,"jack","jack@aynu.com","1");
//            mapper.addEmp(employee);
            //测试修改
            Employee employee = new Employee(7, "Anna", "Anna@aynu.com", "1",null);
            Boolean aBoolean = mapper.updateEmp(employee);
            System.out.println(aBoolean);
            //测试删除
//            mapper.deleteEmpById(4);
            
            //2.手动提交数据（参数不加true时）
//            openSession.commit();
        }finally {
            openSession.close();
        }
    }
    @Test
    public void test04() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        //1.获取到的SqlSession不会自动提交出去
        SqlSession openSession = sqlSessionFactory.openSession(true);
        try {
            EmployeeMapper mapper = openSession.getMapper(EmployeeMapper.class);

//            Employee empByIdAndLastName = mapper.getEmpByIdAndLastName(5, "Anna");
//            System.out.println(empByIdAndLastName);


//            Map<String, Object> map = new HashMap<>();
//            map.put("id",5);
//            map.put("lastName","Anna");
//            Employee empByMap = mapper.getEmpByMap(map);
//            System.out.println(empByMap);


            List<Employee> empsByLastNameLike = mapper.getEmpsByLastNameLike("%a%");
            for (Employee employee:empsByLastNameLike) {
                System.out.println(employee);
            }

//            Map<String, Object> empByIdReturnMap = mapper.getEmpByIdReturnMap(5);
//            System.out.println(empByIdReturnMap);


            Map<Integer, Employee> empByLastNameLikeReturnMap = mapper.getEmpByLastNameLikeReturnMap("%a%");
            System.out.println(empByLastNameLikeReturnMap);

        }finally {
            openSession.close();
        }
    }
    @Test
    public void test05() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession openSession = sqlSessionFactory.openSession(true);
        try {
            EmployeeMapperPlus mapper = openSession.getMapper(EmployeeMapperPlus.class);
//            Employee empById = mapper.getEmpById(1);
//            System.out.println(empById);

//            Employee empAndDept = mapper.getEmpAndDept(1);
//            System.out.println(empAndDept);
//            System.out.println(empAndDept.getDept());

//            Employee empByIdStep = mapper.getEmpByIdStep(1);
//            System.out.println(empByIdStep);
//            System.out.println(empByIdStep.getDept());

//            测试延时加载(按需加载、懒加载)
            Employee empByIdStep = mapper.getEmpByIdStep(3);
            System.out.println(empByIdStep);
            System.out.println(empByIdStep.getDept());

        }finally {
            openSession.close();
        }
    }
    @Test
    public void test06() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession openSession = sqlSessionFactory.openSession(true);
        try {
            DepartmentMapper mapper = openSession.getMapper(DepartmentMapper.class);
            Department deptByIdPlus = mapper.getDeptByIdPlus(1);
            System.out.println(deptByIdPlus);
            System.out.println(deptByIdPlus.getEmployees());
        }finally {
            openSession.close();
        }
    }

    @Test
    public void test07() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession openSession = sqlSessionFactory.openSession(true);
        try {
            DepartmentMapper mapper = openSession.getMapper(DepartmentMapper.class);
            Department getdeptByName = mapper.getdeptByName("开发部");
                System.out.println(getdeptByName);
        }finally {
            openSession.close();
        }
    }
    @Test
    public void test08() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession openSession = sqlSessionFactory.openSession(true);
        try {
            DepartmentMapper mapper = openSession.getMapper(DepartmentMapper.class);
            Department deptByIdStep = mapper.getDeptByIdStep(1);
            System.out.println(deptByIdStep);
            System.out.println(deptByIdStep.getDepartmentName());

        }finally {
            openSession.close();
        }
    }
    @Test
    public void testDynamicSQL() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession openSession = sqlSessionFactory.openSession(true);
        try {
            EmployeeMapperDynamicSQL mapper = openSession.getMapper(EmployeeMapperDynamicSQL.class);
//            SELECT * FROM tbl_employee WHERE id=? AND last_name LIKE ? AND email=?
//            gender为空，所以查询不带
            Employee employee = new Employee(null, "%o%", "Bob@aynu.com", null,null);
            //查询的时候如果某些条件没带可能sql拼装会有问题，例如这里id为null，这时的sql语句为
//            SELECT * FROM tbl_employee WHERE AND last_name LIKE ? AND email=?    直接将and拼装在后面了
//            解决办法 ：
//                      1. where 1=1 然后后面都加and（某些公司使用）
//                      此时  SELECT * FROM tbl_employee WHERE 1=1 AND last_name LIKE ? AND email=?
//                      2.mybatis使用where标签来将所有的查询条件包括在内（mybatis官方推荐方法）
//                        mybatis会将where标签中拼装的sql多出来的and或者or去掉 （前面的，去不了后面的，所以要规范语法）
//  弹幕：          where 元素只会在子元素返回任何内容的情况下才插入 “WHERE” 子句。而且，若子句的开头为 “AND” 或 “OR”，where 元素也会将它们去除。

            List<Employee> empsByConditionIf = mapper.getEmpsByConditionIf(employee);
            for (Employee emp : empsByConditionIf) {
                System.out.println(emp);
            }
        }finally {
            openSession.close();
        }
    }
    @Test
    //测试trim，注释在xml中
    public void testTrim() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession openSession = sqlSessionFactory.openSession(true);
        try {
            EmployeeMapperDynamicSQL mapper = openSession.getMapper(EmployeeMapperDynamicSQL.class);
            Employee employee = new Employee(null, "%o%", "Torb@aynu.com", null, null);
            List<Employee> empsByConditionTrim = mapper.getEmpsByConditionTrim(employee);
            for (Employee emp : empsByConditionTrim) {
                System.out.println(emp);
            }
        }finally {
            openSession.close();
        }
    }
}
