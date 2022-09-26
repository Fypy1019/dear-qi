package com.aynu.helomybatis_04_DynamicSQL.dao;

import com.aynu.helomybatis_04_DynamicSQL.pojo.Department;
import com.aynu.helomybatis_04_DynamicSQL.pojo.Employee;

import java.util.List;

/**
 * @author hqlsyq
 * @creat 2022-09-23 13:46
 */
public interface DepartmentMapper {
    public Department getDeptById(Integer id);

    public Department getDeptByIdPlus(Integer id);
//myself
    public Department getdeptByName(String departmentName);

    public Department getDeptByIdStep(Integer id);

    public List<Employee> getEmpsByDeptId(Integer deptId);
//多列值传递
    public List<Employee> getEmpsByDeptIdLines(Integer deptId);
}
