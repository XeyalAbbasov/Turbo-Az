package com.khayal.turboaz.model;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
@Table(name = "cars")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String brand;
    private String model;
    private String city;
    private String year;
    private Double price;
    private String color;
    private LocalDateTime publishDate;
    private LocalDateTime publishUpdate;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id",referencedColumnName = "id",nullable = false)
    private User user;

    public Car(String brand, String model, String city, String year, Double price,String color,User user){
        this(brand,model, city, year,price,color,LocalDateTime.now(),LocalDateTime.now(),user);
    }

    //create Car
    public Car(String brand, String model, String city, String year, Double price,String color,
               LocalDateTime publishDate,LocalDateTime publishUpdate,User user) {
        this.brand = brand;
        this.model = model;
        this.city = city;
        this.year = year;
        this.price = price;
        this.color=color;
        this.publishDate=publishDate;
        this.publishUpdate=publishUpdate;
        this.user=user;
    }

    public Car(Integer id,String brand, String model, String city, String year, Double price,String color,User user){
        this(id,brand,model, city, year,price,color,LocalDateTime.now(),user);
    }
    //update Car
    public Car(Integer id, String brand, String model, String city, String year, Double price,String color,LocalDateTime publishUpdate,User user) {
        this.id=id;
        this.brand = brand;
        this.model = model;
        this.city = city;
        this.year = year;
        this.price = price;
        this.color=color;
        this.publishUpdate=publishUpdate;
        this.user=user;

    }

}
