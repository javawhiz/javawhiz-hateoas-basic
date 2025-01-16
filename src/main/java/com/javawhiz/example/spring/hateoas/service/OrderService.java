package com.javawhiz.example.spring.hateoas.service;

import com.javawhiz.example.spring.hateoas.model.Order;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

@Service
public class OrderService {

    public List<Order> getOrders(int start, int count) {
        List<Order> orders = new LinkedList<>();
        for (int i = start; i < start + count; i++) {
            orders.add(Order.builder()
                            .id(i)
                            .name("Order " + i)
                            .description("Description " + i)
                            .amount(BigDecimal.valueOf(i * 10L))
                            .build());
        }
        return orders;
    }
}
