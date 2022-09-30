package com.aynu.helomybatis_06_ssm.pojo;

import org.apache.ibatis.type.Alias;

import java.io.Serializable;

/**
 * @author hqlsyq
 * @creat 2022-09-17 10:52
 */

@Alias("emp")//别名处理器产生冲突时使用的注解,引用时可以用emp也可以使用全类名
public class Employee implements Serializable {
    private Integer id;
    private String lastName;
    private String email;
    private String gender;

    public Employee(Integer id, String lastName, String email, String gender) {
        this.id = id;
        this.lastName = lastName;
        this.email = email;
        this.gender = gender;
    }

    public Employee() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;

    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }
}
