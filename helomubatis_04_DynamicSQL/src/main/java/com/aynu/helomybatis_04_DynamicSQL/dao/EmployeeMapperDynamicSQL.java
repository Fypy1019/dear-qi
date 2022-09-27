package com.aynu.helomybatis_04_DynamicSQL.dao;

import com.aynu.helomybatis_04_DynamicSQL.pojo.Employee;
import org.apache.ibatis.annotations.Param;
import org.thymeleaf.expression.Ids;

import java.util.List;

/**
 * @author hqlsyq
 * @creat 2022-09-24 15:49
 */
public interface EmployeeMapperDynamicSQL {
    //查询携带了哪个字段查询条件就带上这个字段
    public List<Employee> getEmpsByConditionIf(Employee employee);

    public List<Employee> getEmpsByConditionTrim(Employee employee);

    public List<Employee> getEmpsByConditionChoose(Employee employee);
//带哪个值，更新哪个值
    public void updateEmp(Employee employee);

    public List<Employee> getEmpsByConditionForeach(@Param("ids") List<Integer> ids);//或者不写@Param，将collection="list"

    public void addEmps(List<Employee> employees);
}
