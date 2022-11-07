package com.example.catalog.catalog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CatalogController {
    CatalogService catalogService;
    @Autowired
    public CatalogController(CatalogService catalogService) {
        this.catalogService = catalogService;
    }

    @GetMapping("/search")
    public List getListOfCatalogs(){
        return catalogService.getListOfCatalogs();
    }
    @GetMapping("/search/{s}")
    public List getListOfTopic(@PathVariable String s){
        return catalogService.getListOfTopic(s);
    }
    @GetMapping("/info")
    public List getListOfInfo(){
        return catalogService.getListOfInfo();
    }
    @GetMapping("/info/{s}")
    public List getInfo(@PathVariable String s){
        return catalogService.getInfo(s);
    }
    @GetMapping("/infoQ/{s}")
    public String getInfoQ(@PathVariable Long s){
        return catalogService.getInfoQ(s);
    }
    @GetMapping("/infoedit/{s}")
    public void infoEdit(@PathVariable Long s){
        catalogService.infoEdit(s);

    }


}

