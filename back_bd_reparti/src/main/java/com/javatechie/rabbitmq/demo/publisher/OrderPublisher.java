package com.javatechie.rabbitmq.demo.publisher;

import com.javatechie.rabbitmq.demo.config.MessagingConfig;
import com.javatechie.rabbitmq.demo.dto.Bo;
import com.javatechie.rabbitmq.demo.dto.updateBO;
import com.javatechie.rabbitmq.demo.entity.Bo1.ProductBo1;
import com.javatechie.rabbitmq.demo.entity.Bo2.ProductBo2;
import com.javatechie.rabbitmq.demo.entity.Ho.Product;
import com.javatechie.rabbitmq.demo.repository.Bo1.repositoryBo1;
import com.javatechie.rabbitmq.demo.repository.Bo2.repositoryBo2;
import com.javatechie.rabbitmq.demo.repository.Ho.repository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/Bo")
public class OrderPublisher {
    @Autowired(required=true)
    repositoryBo1 repositoryBo1 ;
    @Autowired(required=true)
    repository repository ;
    @Autowired(required=true)
    repositoryBo2 repositoryBo2 ;
    @Autowired
    private RabbitTemplate template;
@GetMapping("getallprod/{base}")
public List hoproduct( @PathVariable String base){
    if(Objects.equals(base, "Ho"))
return repository.findAll();
    if(Objects.equals(base, "Bo1"))
        return repositoryBo1.findAll();
        else
        return repositoryBo2.findAll();
}

    @GetMapping("getprod/{base}/{id}")
    public List getprod( @PathVariable String base,@PathVariable int id){
        if(Objects.equals(base, "Ho"))
            return Collections.singletonList(repository.findById(id));
        if(Objects.equals(base, "Bo1"))
            return Collections.singletonList(repositoryBo1.findById(id));
        else
            return Collections.singletonList(repositoryBo2.findById(id));
    }
    @PostMapping("add/{boname}")
    public String bookOrder(@RequestBody Bo bo, @PathVariable String boname) {
        int id =(int)(Math.random()*589);
        if(Objects.equals(boname, "Bo1"))
        {
            //System.out.println("dsd");
            ProductBo1 p = new ProductBo1();
            Date date = new Date();
            p=p.Product(bo,date);
            p.setStatus("new");
            p.setId(id);
            repositoryBo1.save(p);
            //p.setId(UUID.randomUUID().toString());
            //System.out.println("dd "+p);
           // template.setDefaultReceiveQueue( MessagingConfig.QUEUE2);

            template.convertAndSend(MessagingConfig.EXCHANGE, MessagingConfig.ROUTING_KEY, p);
            return "Success !!";
        }
        else {
            if(Objects.equals(boname, "Bo2")){
                ProductBo2 p = new ProductBo2();
                Date date = new Date();
                p=p.Product(bo,date);
                p.setStatus("new");
                p.setId( id);
                repositoryBo2.save(p);
                //template.setDefaultReceiveQueue( MessagingConfig.QUEUE2);
                //template.setRoutingKey(MessagingConfig.ROUTING_KEY2);
                template.convertAndSend(MessagingConfig.EXCHANGE, MessagingConfig.ROUTING_KEY2, p);

                return "Success !!";
            }
            else {
                return "verifier le nom de bo";
            }
        }



    }



    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("update/{boname}/{id}")
    public String updatedata(@RequestBody updateBO bo, @PathVariable String boname, @PathVariable int id) {
        if(Objects.equals(boname, "Bo1"))
        {

            ProductBo1 p = new ProductBo1();

            p=p.Product(bo,bo.getDate());
            p.setId(id);
            p.setStatus("updated");
            repositoryBo1.save(p);
           // System.out.println(p);
           template.convertAndSend(MessagingConfig.EXCHANGE, MessagingConfig.ROUTING_KEY, p);
            return "Success !!";
        }
        else {
            if(Objects.equals(boname, "Bo2")){
                ProductBo2 p = new ProductBo2();
                p=p.Product(bo,bo.getDate());
                p.setId(id);
                p.setStatus("updated");
                repositoryBo2.save(p);
                template.convertAndSend(MessagingConfig.EXCHANGE, MessagingConfig.ROUTING_KEY2, p);
                return "Success !!";
            }
            else {
                return "verifier le nom de bo";
            }
        }



    }

    @DeleteMapping("delete/{boname}/{id}")
    public String deletedata( @PathVariable String boname, @PathVariable int id) {
        if (Objects.equals(boname, "Bo1")) {

            ProductBo1 p = repositoryBo1.findById(id).get();
            p.setStatus("deleted");
            repositoryBo1.delete(p);
            template.convertAndSend(MessagingConfig.EXCHANGE, MessagingConfig.ROUTING_KEY, p);
            return "Success !!";
        } else {
            if (Objects.equals(boname, "Bo2")) {
                ProductBo2 p = repositoryBo2.findById(id).get();
                p.setStatus("deleted");
                repositoryBo2.delete(p);
                template.convertAndSend(MessagingConfig.EXCHANGE, MessagingConfig.ROUTING_KEY2, p);
                return "Success !!";
            } else {
                return "verifier le nom de bo";
            }
        }
    }
}
