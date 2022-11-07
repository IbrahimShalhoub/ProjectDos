package Front;


import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@Service
public class FrontService {
    private final RestTemplate restTemplate;

    public FrontService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }



    public List getAll() {
        String url = "http://localhost:8082/search";
        return this.restTemplate.getForObject(url, List.class);
    }

    public List getSearch(String a) {
        String url = "http://localhost:8082/search/"+a;
        return this.restTemplate.getForObject(url, List.class);

    }

    public List getallinfo() {
        String url = "http://localhost:8082/info";
        return this.restTemplate.getForObject(url, List.class);

    }

    public List getInfo(String s) {
        String url = "http://localhost:8082/info/"+s;
        return this.restTemplate.getForObject(url, List.class);
    }

    public String pursh(String s) {
        String url = "http://localhost:8084/"+s;
        //  this.restTemplate.getForObject(url, List.class);
        HttpHeaders headers = new HttpHeaders();
        // set `content-type` header
        headers.setContentType(MediaType.APPLICATION_JSON);
        // set `accept` header
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(s, headers);
        return restTemplate.postForObject(url, entity, String.class);
        //return s;


    }
}

