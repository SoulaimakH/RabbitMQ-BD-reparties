package com.javatechie.rabbitmq.demo.repository.Bo2;

import com.javatechie.rabbitmq.demo.entity.Bo2.ProductBo2;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface repositoryBo2 extends JpaRepository<ProductBo2, Integer> {
}
