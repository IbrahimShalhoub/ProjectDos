package com.example.front.Front;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Array;
import java.util.*;

@Service
public class FrontService {
    private final RestTemplate restTemplate;
    private HashMap<Integer,List> information;
    boolean CFlag=true;
    boolean OFlag=true;


    public FrontService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
        information=new HashMap<>();
    }



    public List getAll()  {
        //String url = "http://192.168.56.103:8082/search";
        String url="";
        if(CFlag){
         url = "http://localhost:8082/search";

        }
        else { url = "http://localhost:8090/search";}
        CFlag=!CFlag;

        List a;
        ResponseEntity<List> response =restTemplate.getForEntity(url, List.class);
        if(!(response.getStatusCode()==HttpStatus.OK)){
             a=getAll();
             return a;
        }
        a=response.getBody();

        int key = 0;
        int quan = 0;
        int price = 0;
        String title = null;
        String subject = null;
        String [] forsplitting=a.toString().split("],");
        //System.out.println(a);
      for(int i=0;i<a.size();i++){
          if(i==0){
             key=Integer.parseInt(forsplitting[i].split(",")[0].split("\\[\\[")[1]);
            }
            else{
                key=Integer.parseInt(forsplitting[i].split(",")[0].split("\\[")[1]);}
                quan=Integer.parseInt(forsplitting[i].split(",")[3].trim());
                price =Integer.parseInt(forsplitting[i].split(",")[4].trim().split("]]")[0]);
                title =forsplitting[i].split(",")[2].trim();
                subject=forsplitting[i].split(",")[1].trim();
          information.put(key,List.of(
                  key,
                  quan,
                  price,
                  title,
                  subject
          ));
                //System.out.println(forsplitting[i].split(",")[4].trim().split("]]")[0]);
        }


      System.out.println("done adding to cache");

        return a;
    }

    public List getSearch(String a) {

        //String url = "http://192.168.56.103:8082/search/"+a;
        //String url = "http://localhost:8082/search/"+a;
        String url="";
        if(CFlag){
             url = "http://localhost:8082/search/"+a;}
        else {
             url = "http://localhost:8090/search/"+a;}
        CFlag=!CFlag;
        List aa;
        ResponseEntity<List> response =restTemplate.getForEntity(url, List.class);
        if(!(response.getStatusCode()==HttpStatus.OK)){
            aa=getAll();
            return aa;
        }
        aa=response.getBody();
        if(aa.isEmpty())
            return List.of("there is no book with such ID");
        String [] forsplitting=aa.toString().split("],");
        int key = 0;
        int quan = 0;
        int price = 0;
        String title = null;
        String subject = null;
        for(int i=0;i<forsplitting.length;i++){

            if(i==0){
             key=Integer.parseInt(forsplitting[i].split(",")[0].split("\\[\\[")[1]);
            }
            else{
                key=Integer.parseInt(forsplitting[i].split(",")[0].split("\\[")[1]);}
                quan=Integer.parseInt(forsplitting[i].split(",")[1].trim());
                price =Integer.parseInt(forsplitting[i].split(",")[2].trim());
                title =forsplitting[i].split(",")[3].trim();
                subject=forsplitting[i].split(",")[4].trim().split("]]")[0];
                //System.out.println(forsplitting[i].split(",")[4].trim().split("]]")[0]);
        }
        information.put(key,List.of(
                key,
                quan,
                price,
                title,
                subject
        ));
        System.out.println("done adding to cache");
        return aa;

    }

    public List getallinfo() {
       // String url = "http://192.168.56.103:8082/info";
        //String url = "http://localhost:8082/info";
        String url="";
        if(CFlag){
            url = "http://localhost:8082/info";
        }
        else{
            url = "http://localhost:8090/info";
        }
        CFlag=!CFlag;
        List a;
        ResponseEntity<List> response =restTemplate.getForEntity(url, List.class);
        if(!(response.getStatusCode()==HttpStatus.OK)){
            a=getAll();
            return a;
        }
        a=response.getBody();
        if(a.isEmpty())
            return List.of("there is no book with such ID");
        int key = 0;
        int quan = 0;
        int price = 0;
        String title = null;
        String subject = null;
        String [] forsplitting=a.toString().split("],");

        for(int i=0;i<a.size();i++){
            if(i==0){
                key=Integer.parseInt(forsplitting[i].split(",")[0].split("\\[\\[")[1]);
            }
            else{
                key=Integer.parseInt(forsplitting[i].split(",")[0].split("\\[")[1]);}
            quan=Integer.parseInt(forsplitting[i].split(",")[3].trim());
            price =Integer.parseInt(forsplitting[i].split(",")[4].trim().split("]]")[0]);
            title =forsplitting[i].split(",")[2].trim();
            subject=forsplitting[i].split(",")[1].trim();
            information.put(key,List.of(
                    key,
                    quan,
                    price,
                    title,
                    subject
            ));

        }
        System.out.println("done adding to cache");
        return a;

    }

    public List getInfo(String s) {
//        String url = "http://192.168.56.103:8082/info/"+s;
        if(information.containsKey(Integer.parseInt(s))){
            return information.get(Integer.parseInt(s));
        }
        String url="";
        if(CFlag){
            url = "http://localhost:8082/info/"+s;
        }
        else{
            url = "http://localhost:8090/info/"+s;
        }
        CFlag=!CFlag;
//        String url = "http://localhost:8082/info/"+s;
        List aa;
        ResponseEntity<List> response =restTemplate.getForEntity(url, List.class);
        if(!(response.getStatusCode()==HttpStatus.OK)){
            aa=getAll();
            return aa;
        }
        aa=response.getBody();
        if(aa.isEmpty())
            return List.of("there is no book with such ID");
        String [] forsplitting=aa.toString().split("],");
        int key = 0;
        int quan = 0;
        int price = 0;
        String title = null;
        String subject = null;
        for(int i=0;i<forsplitting.length;i++){

            if(i==0){
                key=Integer.parseInt(forsplitting[i].split(",")[0].split("\\[\\[")[1]);
            }
            else{
                key=Integer.parseInt(forsplitting[i].split(",")[0].split("\\[")[1]);}
            quan=Integer.parseInt(forsplitting[i].split(",")[1].trim());
            price =Integer.parseInt(forsplitting[i].split(",")[2].trim());
            title =forsplitting[i].split(",")[3].trim();
            subject=forsplitting[i].split(",")[4].trim();
            //System.out.println(forsplitting[i].split(",")[4].trim().split("]]")[0]);
        }
        information.put(key,List.of(
                key,
                quan,
                price,
                title,
                subject
        ));
        System.out.println("done adding to cache");
        return aa;
    }

    public String purch(String s) {
        if(information.containsKey(Integer.parseInt(s))){
            List a=information.get(Integer.parseInt(s));
            String [] forsplitting=a.toString().split(",");
            if(Integer.parseInt(forsplitting[1].trim())<=0){
                System.out.println("Got quantity from cash to check it");
                return " Quantity is not enough";
            }

        }
        //String url = "http://192.168.56.104:8084/"+s;
       // String url = "http://localhost:8084/"+s;
        String url="";
        if(OFlag){
            url = "http://localhost:8084/"+s;
        }
        else{
            url = "http://localhost:8091/"+s;
        }
        OFlag=!OFlag;
        //  this.restTemplate.getForObject(url, List.class);
        HttpHeaders headers = new HttpHeaders();
        // set `content-type` header
        headers.setContentType(MediaType.APPLICATION_JSON);
        // set `accept` header
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(s, headers);

        ResponseEntity<List> responseEntityStr = restTemplate.
                postForEntity(url, entity, List.class);
        if(!(responseEntityStr.getStatusCode()==HttpStatus.OK)){
            return purch(s);
        }
        if(responseEntityStr.getStatusCode()==HttpStatus.OK&& !responseEntityStr.getBody().isEmpty())
            return "Operation done!";
        return "there is a problem!";
        //return s;


    }

    public void cashedit(String s) {
        int key=Integer.parseInt(s);
        if(information.containsKey(key)){
            System.out.println("there is the same key in the cash and it will be deleted");
            information.remove(key);
        }
    }
}

