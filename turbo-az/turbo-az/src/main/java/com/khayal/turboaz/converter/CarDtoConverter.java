package com.khayal.turboaz.converter;

import com.khayal.turboaz.dto.CarDto;
import com.khayal.turboaz.model.Car;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CarDtoConverter {

    public CarDto convert(Car from) {
        return new CarDto(from.getBrand(),from.getModel(),from.getCity(),from.getYear(),from.getPrice(),from.getColor(),from.getUser());
    }

    public List<CarDto> convert(List<Car> fromList) {

        return fromList.stream().map(this::convert).collect(Collectors.toList());
    }
}
