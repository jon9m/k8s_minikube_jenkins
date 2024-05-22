package com.mmk.restapi;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Spacedude {
    private String message;
    private String number;
    private People[] people;
    private Meta meta;
}

record People(String name, String craft) {
}

record Meta(String ip){}
