package com.example.order.order;

import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import javax.xml.catalog.Catalog;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    OrderRepository orderRepository;
    private final RestTemplate restTemplate;
    @Autowired
    public OrderService(OrderRepository orderRepository, RestTemplateBuilder restTemplateBuilder) {
        this.orderRepository = orderRepository;
        this.restTemplate = restTemplateBuilder.build();
    }


    public String putf(Long a) {
        String url = "http://localhost:8082/infoQ/"+a;

        String s= restTemplate.getForObject(url, String.class);
        int q=Integer.parseInt(s);
        if(q>0) {
            orderRepository.save(new Order(a,1));
        }
        url = "http://localhost:8082/infoedit/"+a;
        return restTemplate.getForObject(url, String.class);

    }
}

