package com.khayal.turboaz.controller;
import com.khayal.turboaz.dto.CarDto;
import com.khayal.turboaz.model.Car;
import com.khayal.turboaz.request.CreateCarRequest;
import com.khayal.turboaz.request.UpdateCarRequest;
import com.khayal.turboaz.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cars")
public class CarController {

    private final CarService carService;

    @GetMapping
    public ResponseEntity<List<CarDto>> showAllCars(){
        return ResponseEntity.ok(carService.showAllCars());
    }

    @PostMapping
    public ResponseEntity<CarDto> createCarAdvert(@RequestBody final CreateCarRequest createCarRequest) {
        return ResponseEntity.ok(carService.creatCarAdvert(createCarRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CarDto> updateCarAdvert(@RequestBody final UpdateCarRequest updateCarRequest,@PathVariable Integer id) {
        return ResponseEntity.ok(carService.updateCarAdvert(updateCarRequest,id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCarAdvert(@PathVariable Integer id) {
        carService.deleteCarAdvert(id);
        return ResponseEntity.ok().build();
    }
}
