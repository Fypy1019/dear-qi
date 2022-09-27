package com.aynu.helomybatis_02.dao;

import com.aynu.helomybatis_02.pojo.Employee;
import org.apache.ibatis.annotations.Select;

/**
 * @author hqlsyq
 * @creat 2022-09-19 14:53
 */
public interface EmployeeMapperAnnotation {
    @Select("select * from tbl_employee where id = #{id}")
    public Employee getEmpById(Integer id);
}
