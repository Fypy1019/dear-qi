package com.aynu.helomybatis_04_DynamicSQL.dao;

import com.aynu.helomybatis_04_DynamicSQL.pojo.Employee;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 接口与配置文件动态绑定
 *
 * @author hqlsyq
 * @creat 2022-09-17 16:00
 */
public interface EmployeeMapper {
    public Employee getEmpById(Integer id);

    public Long addEmp(Employee employee);
//mybatis允许增删改直接定义以下类型返回值
    //Integer、Long、Boolean

    public Boolean updateEmp(Employee employee);

    public void deleteEmpById(Integer id);

    public Employee getEmpByIdAndLastName(@Param("id") Integer id, @Param("lastName") String lastName);

    public Employee getEmpByMap(Map<String,Object> map);

    public List<Employee> getEmpsByLastNameLike(String lastName);

    //返回一条记录map：key就是列名，值就是对应的值
    public Map<String,Object> getEmpByIdReturnMap(Integer id);

    //多条记录封装一个map：Map<Integer,Employee>键是这条记录的主键，值是记录封装后的javaBean
    @MapKey("lastName")//告诉mybatis封装这个map的时候使用哪个属性作为map的key
    public Map<Integer,Employee> getEmpByLastNameLikeReturnMap(String lastName);
}
