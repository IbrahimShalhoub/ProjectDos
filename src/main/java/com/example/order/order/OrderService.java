package com.example.order.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;


@Service
public class OrderService {
    OrderRepository orderRepository;
    private final RestTemplate restTemplate;
    @Autowired
    public OrderService(OrderRepository orderRepository, RestTemplateBuilder restTemplateBuilder) {
        this.orderRepository = orderRepository;
        this.restTemplate = restTemplateBuilder.build();
    }


    public List putf(Long a) {
        //String url = "http://192.168.56.103:8082/infoQ/"+a;
        //String url = "http://localhost:8082/infoQ/"+a;
        String url = "http://localhost:8090/infoQ/"+a;

        ResponseEntity<List> response =restTemplate.getForEntity(url, List.class);
        List s=response.getBody();
        if(s.isEmpty()){
            return List.of();
        }
        String [] forsplitting=s.toString().split(",");
       int q=Integer.parseInt(forsplitting[1].trim());

        if(q>0) {
            orderRepository.save(new Order(a,1));
            url = "http://localhost:8084/dataedit/"+a;
            HttpHeaders headers = new HttpHeaders();
            // set `content-type` header
            headers.setContentType(MediaType.APPLICATION_JSON);
            // set `accept` header
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            HttpEntity<String> entity = new HttpEntity<>(a.toString(), headers);

            ResponseEntity<List> responseEntityStr = restTemplate.
                    postForEntity(url, entity, List.class);
            ///////////
            //url = "http://192.168.56.103:8082/infoedit/"+a;
//            url = "http://localhost:8082/infoedit/"+a;
            url = "http://localhost:8090/infoedit/"+a;
            response =restTemplate.getForEntity(url, List.class);
            if(! (response.getStatusCode()== HttpStatus.OK)){
                url = "http://localhost:8082/infoedit/"+a;
                response =restTemplate.getForEntity(url, List.class);
            }
             return s;
        }
        return List.of();

    }

    public void dataedit(Long s) {
        orderRepository.save(new Order(s,1));
    }
}

