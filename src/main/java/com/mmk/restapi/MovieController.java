package com.mmk.restapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.InetAddress;
import java.util.Objects;

@RestController
public class MovieController {

    Logger logger = LogManager.getLogger(MovieController.class);

    @Autowired
    private Config config;

    @Autowired
    private PeopleService service;

    @GetMapping(value = "/people", produces = "application/json")
    public String getAstronauts() {
        try {
            return service.getIssPeople();
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
