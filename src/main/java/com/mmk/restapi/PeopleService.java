package com.mmk.restapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.InetAddress;
import java.util.Objects;

@Service
@Slf4j
public class PeopleService {
    private String serverAddress;

    @Autowired
    RestTemplate restTemplate;

    public String getIssPeople() {
        try {
            serverAddress = String.valueOf(InetAddress.getLocalHost());
            log.debug("Inside getAstronauts method");
            ResponseEntity<Spacedude> exchange = restTemplate.exchange("http://api.open-notify.org/astros.json", HttpMethod.GET, null, Spacedude.class);
            log.info("Getting people in the space");
            Objects.requireNonNull(exchange.getBody()).setMeta(new Meta(serverAddress));
            return new ObjectMapper().writeValueAsString(exchange.getBody());
        } catch (Exception e) {
            log.error("Error {}", e.getMessage());
        }
        return "no result";
    }
}