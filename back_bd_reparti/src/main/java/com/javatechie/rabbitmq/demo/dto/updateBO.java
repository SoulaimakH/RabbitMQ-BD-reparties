package com.javatechie.rabbitmq.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class updateBO extends Bo {

    private Date date;

    private String region;

    private String product;

    private int qty;

    private float cost;

    private double amt;

    private float tax;

    private double total;

    private int bo_num;
}
