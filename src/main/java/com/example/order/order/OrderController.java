package com.example.order.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class OrderController {
    OrderService orderService;
    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }
    @PostMapping("/{a}")
    public String putf(@PathVariable String a){
        return orderService.putf(Long.parseLong(a));
        //return a;
    }

}

