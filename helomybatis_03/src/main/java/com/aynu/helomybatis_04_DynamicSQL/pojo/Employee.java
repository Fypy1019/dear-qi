package com.aynu.helomybatis_04_DynamicSQL.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

/**
 * @author hqlsyq
 * @creat 2022-09-17 10:52
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Alias("emp")//别名处理器产生冲突时使用的注解,引用时可以用emp也可以使用全类名
public class Employee {
    private Integer id;
    private String lastName;
    private String email;
    private String gender;
    private Department dept;
}
