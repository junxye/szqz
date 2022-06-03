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
@Table(name = "item")
@AllArgsConstructor
@NoArgsConstructor
public class Item {

    @Id
    private int id;
    @Column(name = "item_name")
    private String itemName;
    @Column(name = "price")
    private double price;
    private String seller;
    private String buyer;
    private String introduce;
    @Column(name = "item_check")
    private int itemCheck; // 0 未检测， 1 通过， 2 未通过

    @Column(name = "check_opinion")
    private String checkOpinion;
    @Column(name = "is_sell")
    private int isSell; // 0 售出， 1 未售出， 2 被拍未售

    private String picture;

    @Column(name = "add_time")
    private Date addTime;

    @Column(name = "sell_time")
    private Date sellTime;

    public Item(String name, double price, String introduce, String picture){
        this.itemName = name;
        this.price = price;
        this.introduce = introduce;
        this.picture = picture;
    }

    @Override
    public String toString() {
        return "Item [id=" + id + ", itemName=" + itemName + ", price=" + price
                + ", seller=" + seller + ", introduce=" + introduce +  ", itemCheck=" + itemCheck
                +  ", checkOpinion=" + checkOpinion + ", addTime=" + addTime + "]";
    }
}
