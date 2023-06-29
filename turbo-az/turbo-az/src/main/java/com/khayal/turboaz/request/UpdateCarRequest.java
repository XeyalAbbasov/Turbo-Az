package com.khayal.turboaz.request;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UpdateCarRequest {

    private String brand;
    private String model;
    private String city;
    private String year;
    private Double price;
    private String color;
    private Integer userId;
    private LocalDateTime publishUpdate;
    private String userEmail;
}
