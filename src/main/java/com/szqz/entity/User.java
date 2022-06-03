package com.szqz.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.util.Date;
import java.util.List;

// @Data : 替代get,set
@Data
@EqualsAndHashCode(callSuper = false)
@Table(name = "user")
@AllArgsConstructor
@NoArgsConstructor
public class User extends Model<User> {

    @Id
    private Long id;
    private String account;

    @NotBlank(message = "密码不能为空")
    @Column(name = "pass_word")
    private String passWord;

    @NotBlank(message = "账号不能为空")
    @Column(name = "phone_number")
    private String phoneNumber;

    private int age;
    private String sex;
    private String introduce;
    private String role;
    private String picture;
    @Column(name = "grade")
    private double grade;   // 评分

    @Column(name = "add_time", length = 20)
    private Date addTime;

//    private int items;
//    private int noPass;

    public User(String phoneNumber, String passWord){
        this.phoneNumber = phoneNumber;
        this.passWord = passWord;
    }

    public User(String account, String passWord, int age, String sex, String introduce, String picture){
        this.account = account;
        this.passWord = passWord;
        this.age = age;
        this.sex = sex;
        this.introduce = introduce;
        this.picture = picture;
    }
/*
    @Override
    public String toString() {
        return "User [id=" + id + ", account=" + account + ", passWord=" + passWord
                + ", sex=" + sex + ", age=" + age +  ", phoneNumber=" + phoneNumber
                +  ", introduce=" + introduce + ", addTime=" + addTime
                +  ", items=" + items + ", noPass=" + noPass + "]";
    }*/
}
