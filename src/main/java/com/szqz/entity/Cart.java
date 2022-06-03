package com.szqz.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
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
@Table(name = "cart")
@AllArgsConstructor
@NoArgsConstructor
public class Cart extends Model<Cart> {

    @Id
    private Long id;

    @Column(name = "item_name")
    private String itemName;

    private String seller;
    private String buyer;
    @Column(name = "price")
    private double price;
    private String introduce;
    private String picture;
    @Column(name = "add_time")
    private Date addTime;
}
