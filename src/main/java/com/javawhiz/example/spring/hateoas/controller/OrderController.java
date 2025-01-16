package com.javawhiz.example.spring.hateoas.controller;

import com.javawhiz.example.spring.hateoas.model.Order;
import com.javawhiz.example.spring.hateoas.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @RequestMapping("/")
    public CollectionModel<Order> listOrders(@RequestParam(value = "start", defaultValue = "1") int start,
                                             @RequestParam(value = "count", defaultValue = "10") int count) {
        List<Order> orders = orderService.getOrders(start, count);
        return CollectionModel.of(orders, getLinks(start, count));
}

    private Iterable<Link> getLinks(int start, int count) {
        Link selfRel = linkTo(methodOn(OrderController.class).listOrders(start, count)).withSelfRel();
        Link previous =
                linkTo(methodOn(OrderController.class).listOrders(Math.max(start - count, 1), count)).withRel("previous");
        Link next = linkTo(methodOn(OrderController.class).listOrders(start + count, count)).withRel("next");
        return List.of(selfRel, previous, next);
    }
}
