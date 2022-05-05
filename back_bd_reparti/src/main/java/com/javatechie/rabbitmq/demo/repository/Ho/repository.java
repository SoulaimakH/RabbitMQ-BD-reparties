package com.javatechie.rabbitmq.demo.repository.Ho;

import com.javatechie.rabbitmq.demo.entity.Ho.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface repository extends JpaRepository<Product, Integer> {
}
