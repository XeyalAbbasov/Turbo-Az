package com.khayal.turboaz.repository;

import com.khayal.turboaz.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarRepository extends JpaRepository<Car,Integer> {


}