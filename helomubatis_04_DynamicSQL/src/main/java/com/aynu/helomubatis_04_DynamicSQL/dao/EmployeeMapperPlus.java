package com.aynu.helomubatis_04_DynamicSQL.dao;

import com.aynu.helomubatis_04_DynamicSQL.pojo.Employee;

/**
 * @author hqlsyq
 * @creat 2022-09-20 17:10
 */
public interface EmployeeMapperPlus {
    public Employee getEmpById(Integer id);

    public Employee getEmpAndDept(Integer id);

    public Employee getEmpByIdStep(Integer id);
}
