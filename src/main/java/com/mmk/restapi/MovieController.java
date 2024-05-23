package com.mmk.restapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.InetAddress;
import java.util.Objects;

@RestController
//@EnableConfigurationProperties(Config.class)
public class MovieController {

    Logger logger = LogManager.getLogger(MovieController.class);

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    private Config config;

    private String serverAddress;

    @GetMapping(value = "/people", produces = "application/json")
    public String getAstronauts() {
        try {
            serverAddress = String.valueOf(InetAddress.getLocalHost());
            logger.debug("Inside getAstronauts method");
            ResponseEntity<Spacedude> exchange = restTemplate.exchange("http://api.open-notify.org/astros.json", HttpMethod.GET, null, Spacedude.class);
            logger.info("Getting people in the space");
            Objects.requireNonNull(exchange.getBody()).setMeta(new Meta(serverAddress));
            return new ObjectMapper().writeValueAsString(exchange.getBody());
        } catch (Exception e) {
            logger.error("Error {}", e.getMessage());
        }
        return "no result";
    }

    @GetMapping(value = "/status/up")
    public void setIssStatusUp() {
        logger.debug("************* SET STATUS -> UP");
        config.setStatus("up");
    }

    @GetMapping(value = "/status/down")
    public void setIssStatusDown() {
        logger.debug("************* SET STATUS -> DOWN");
        config.setStatus("down");
    }

}
