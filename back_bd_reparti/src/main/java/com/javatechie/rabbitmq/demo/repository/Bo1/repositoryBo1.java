package com.javatechie.rabbitmq.demo.repository.Bo1;

import com.javatechie.rabbitmq.demo.entity.Bo1.ProductBo1;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface repositoryBo1 extends JpaRepository<ProductBo1, Integer> {
}
