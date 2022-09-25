package com.aynu.helomybatis_04_DynamicSQL.dao;

import com.aynu.helomybatis_04_DynamicSQL.pojo.Employee;

/**接口与配置文件动态绑定
 * @author hqlsyq
 * @creat 2022-09-17 16:00
 */
public interface EmployeeMapper {
    public Employee getEmpById(Integer id);
}
