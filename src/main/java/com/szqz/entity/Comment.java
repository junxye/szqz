package com.szqz.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
@Table(name = "comment")
@AllArgsConstructor
@NoArgsConstructor
public class Comment {

    @Id
    private Long id;
    private String message;
    private int grade;
    private String seller;
    private String buyer;
    @Column(name = "add_time")
    private Date addTime;

    public Comment(String seller, String buyer, String message, int grade){
        this.seller = seller;
        this.buyer = buyer;
        this.message = message;
        this.grade = grade;
    }
}
