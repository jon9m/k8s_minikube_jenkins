package com.mmk.restapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class MovieController {

    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/people")
    public String getResponse() {
        ResponseEntity<String> exchange = restTemplate.exchange("http://api.open-notify.org/astros.json", HttpMethod.GET, null, String.class);
        return exchange.getBody();
    }

}
