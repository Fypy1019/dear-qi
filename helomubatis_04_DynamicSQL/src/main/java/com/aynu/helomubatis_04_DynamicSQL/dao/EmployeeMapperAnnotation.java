package com.aynu.helomubatis_04_DynamicSQL.dao;

import com.aynu.helomubatis_04_DynamicSQL.pojo.Employee;
import org.apache.ibatis.annotations.Select;

/**
 * @author hqlsyq
 * @creat 2022-09-19 14:53
 */
public interface EmployeeMapperAnnotation {
    @Select("select * from tbl_employee where id = #{id}")
    public Employee getEmpById(Integer id);
}
