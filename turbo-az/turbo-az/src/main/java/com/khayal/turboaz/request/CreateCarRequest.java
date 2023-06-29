package com.khayal.turboaz.request;
import java.time.LocalDateTime;

public class CreateCarRequest {

    private String brand;
    private String model;
    private String city;
    private String year;
    private Double price;
    private String color;
    private Integer userId;
    private String email;
    private LocalDateTime publishDate;

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public String getCity() {
        return city;
    }

    public String getYear() {
        return year;
    }

    public Double getPrice() {
        return price;
    }

    public String getColor() {
        return color;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getEmail() {
        return email;
    }

    public LocalDateTime getPublishDate() {
        return publishDate;
    }
}
