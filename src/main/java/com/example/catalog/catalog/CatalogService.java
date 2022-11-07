package com.example.catalog.catalog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
@Service
public class CatalogService {
    CatalogRepository repository;
    @Autowired
    public CatalogService(CatalogRepository repository) {
        this.repository = repository;
    }

    public List getListOfCatalogs() {
        return repository.getAll();
    }

    public List getListOfTopic(String a) {
        return repository.getTopic(a);
    }

    public List getListOfInfo() {
        return repository.getAllInfo();
    }

    public List getInfo(String s) {
        return repository.getInfo(Long.parseLong(s));

    }

    public String getInfoQ(Long s) {
        return repository.getInfoQ(s);
    }
    @Transactional
    public void infoEdit(Long s) {
        repository.infoEdit(s);
    }
}

