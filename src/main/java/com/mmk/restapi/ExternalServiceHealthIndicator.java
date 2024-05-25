package com.mmk.restapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class ExternalServiceHealthIndicator implements HealthIndicator {

    @Autowired
    private Config config;

    @Override
    public Health health() {
        Health.Builder healthBuilder = new Health.Builder();

        if (config.getStatus().equalsIgnoreCase("up")) {
            return healthBuilder.up()
                    .withDetail("ISS Service", "Service is Up and Running ✅")
                    .withDetail("url", "https://example.com").build();
        } else {
            return healthBuilder.down()
                    .withDetail("ISS Service", "Service is Down 🔻")
                    .withDetail("alternative_url", "https://example.com").build();
        }
    }
}