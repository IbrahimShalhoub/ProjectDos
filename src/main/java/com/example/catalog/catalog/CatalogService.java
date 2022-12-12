package com.example.catalog.catalog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
@Service
public class CatalogService {
    CatalogRepository repository;
    private final RestTemplate restTemplate;
    @Autowired
    public CatalogService(CatalogRepository repository, RestTemplateBuilder restTemplateBuilder) {
        this.repository = repository;
        this.restTemplate = restTemplateBuilder.build();
    }

    public List getListOfCatalogs() {
        return repository.fid();
    }

    public List getListOfTopic(String a) {
        return repository.getTopic(a);
    }

    public List getListOfInfo() {
        return repository.fid();
    }

    public List getInfo(String s) {
        return repository.getInfo(Long.parseLong(s));

    }

    public List getInfoQ(Long s) {
        return repository.getInfoQ(s);
    }
    @Transactional
    public void infoEdit(Long s) {

        repository.infoEdit(s);
        String url="http://localhost:8083/cashedit/"+s;
        HttpHeaders headers = new HttpHeaders();
        // set `content-type` header
        headers.setContentType(MediaType.APPLICATION_JSON);
        // set `accept` header
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(s.toString(), headers);

        ResponseEntity<List> responseEntityStr = restTemplate.postForEntity(url, entity, List.class);
        ////////////
        url="http://localhost:8090/cashedit/"+s;
        responseEntityStr = restTemplate.postForEntity(url, entity, List.class);
    }
    @Transactional
    public void updatebase(Long s) {

        repository.upd(s);
    }
}

