package com.aynu.helomybatis_03.dao;

import com.aynu.helomybatis_03.pojo.Employee;

/**
 * @author hqlsyq
 * @creat 2022-09-20 17:10
 */
public interface EmployeeMapperPlus {
    public Employee getEmpById(Integer id);

    public Employee getEmpAndDept(Integer id);

    public Employee getEmpByIdStep(Integer id);
}
