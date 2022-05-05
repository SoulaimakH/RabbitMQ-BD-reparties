package com.javatechie.rabbitmq.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Bo {

    private String region;
    private String product;
    private int qty;
    private float cost;
    private double amt;
    private float tax;
    private double total;
    private int bo_num;
}
