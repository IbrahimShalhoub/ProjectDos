package com.example.catalog.catalog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
        System.out.println("In Catalog Server");
        List<List>a=catalogService.getListOfCatalogs();
        //System.out.println(a.get(0).get(0));
        return catalogService.getListOfCatalogs();
    }
    @GetMapping("/search/{s}")
    public List getListOfTopic(@PathVariable String s){
        System.out.println("In Catalog Server");
        return catalogService.getListOfTopic(s);
    }
    @GetMapping("/info")
    public List getListOfInfo(){
        System.out.println("In Catalog Server");
        return catalogService.getListOfInfo();
    }
    @GetMapping("/info/{s}")
    public List getInfo(@PathVariable String s){
        System.out.println("In Catalog Server");
        return catalogService.getInfo(s);
    }
    @GetMapping("/infoQ/{s}")
    public List getInfoQ(@PathVariable Long s){
        System.out.println("In Catalog Server");
        return catalogService.getInfoQ(s);
    }
    @GetMapping("/infoedit/{s}")
    public void infoEdit(@PathVariable Long s){
        System.out.println("In Catalog Server");
        catalogService.infoEdit(s);

    }
    @PostMapping("/cashedit/{s}")
    public void databaseedit(@PathVariable Long s){
        System.out.println("In Catalog Server");
        catalogService.updatebase(s);
    }



}

