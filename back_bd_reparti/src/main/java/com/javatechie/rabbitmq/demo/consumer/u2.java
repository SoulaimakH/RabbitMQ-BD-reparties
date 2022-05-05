package com.javatechie.rabbitmq.demo.consumer;

import com.javatechie.rabbitmq.demo.config.MessagingConfig;
import com.javatechie.rabbitmq.demo.entity.Ho.Product;
import com.javatechie.rabbitmq.demo.repository.Ho.repository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class u2 {
    @Autowired
    repository prorepo ;
    @RabbitListener(queues = MessagingConfig.QUEUE2)
    public void consumeMessageFromQueue(Product prod) {

        System.out.println("Message recieved from queue2 : " + prod);
        if(Objects.equals(prod.getStatus(), "deleted"))
        {
            try{
                prorepo.delete(prod);
            }
            catch (Exception e){
                System.out.println(e);
            }
        }
        else {
            try{
                prorepo.save(prod);
            }
            catch (Exception e){
                System.out.println("u2"+e);
            }
        }

    }
}
