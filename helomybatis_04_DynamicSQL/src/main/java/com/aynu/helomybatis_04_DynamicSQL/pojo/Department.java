package com.aynu.helomybatis_04_DynamicSQL.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author hqlsyq
 * @creat 2022-09-20 17:47
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Department {
    private Integer id;
    private String departmentName;
    private List<Employee> employees;

    public Department(Integer id) {
        this.id = id;
    }
}
