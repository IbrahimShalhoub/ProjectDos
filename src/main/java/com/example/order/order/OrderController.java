package com.example.order.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {
    OrderService orderService;
    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }
    @PostMapping("/{a}")
    public List putf(@PathVariable String a){
        System.out.println("In Catalog Server");
        return orderService.putf(Long.parseLong(a));
        //return a;
    }
    @PostMapping("/dataedit/{s}")
    public void dataedit(@PathVariable Long s){
        System.out.println("In Catalog Server");
        orderService.dataedit(s);
    }

}

