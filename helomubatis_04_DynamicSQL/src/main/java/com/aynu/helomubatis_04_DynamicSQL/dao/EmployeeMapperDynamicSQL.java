package com.aynu.helomubatis_04_DynamicSQL.dao;

import com.aynu.helomubatis_04_DynamicSQL.pojo.Employee;

import java.util.List;

/**
 * @author hqlsyq
 * @creat 2022-09-24 15:49
 */
public interface EmployeeMapperDynamicSQL {
    //查询携带了哪个字段查询条件就带上这个字段
    public List<Employee> getEmpsByConditionIf(Employee employee);

    public List<Employee> getEmpsByConditionTrim(Employee employee);
}
