package com.khayal.turboaz.dto;
import com.khayal.turboaz.model.User;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class CarDto {

    //eger converterin ichine hansisa deyisheni qoymasaqda burada qeyd etdiyimiz uchun return'de
    //hemin deyishenin adi gorsenir
    private String brand;
    private String model;
    private String city;
    private String year;
    private Double price;
    private String color;
    private User user;

    public CarDto(String brand, String model, String city, String year, Double price,String color,User user) {
        this.brand = brand;
        this.model = model;
        this.city = city;
        this.year = year;
        this.price = price;
        this.color=color;
        this.user=user;
    }
}
