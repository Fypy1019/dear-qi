package com.aynu.helomybatis_03.pojo;

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
@Alias("emp")//别名处理器产生冲突时使用的注解
public class Employee {
    private Integer id;
    private String lastName;
    private String email;
    private String gender;
}
