package com.javatechie.rabbitmq.demo.entity.Bo2;



import com.javatechie.rabbitmq.demo.dto.Bo;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table
public class ProductBo2 {
    @Id
    private int id;
    public ProductBo2 Product(Bo bo , Date d){
        this.date=d;
        this.amt=bo.getAmt();
        this.product=bo.getProduct();
        this.cost=bo.getCost();
        this.qty=bo.getQty();
        this.region=bo.getRegion();
        this.tax=bo.getTax();
        this.total=bo.getTotal();
        this.bo_num=bo.getBo_num();
        return this;
    }
    @Column
    private Date date;
    @Column
    private String region;
    @Column
    private String product;
    @Column
    private int qty;
    @Column
    private float cost;
    @Column
    private double amt;
    @Column
    private float tax;
    @Column
    private double total;
    @Column
    private int bo_num;
    @Column
    private String status;

}

