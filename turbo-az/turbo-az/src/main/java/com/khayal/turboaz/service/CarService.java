package com.khayal.turboaz.service;

import com.khayal.turboaz.converter.CarDtoConverter;
import com.khayal.turboaz.dto.CarDto;
import com.khayal.turboaz.exception.CarNotFoundException;
import com.khayal.turboaz.exception.EmailNotFoundException;
import com.khayal.turboaz.exception.UserNotFoundException;
import com.khayal.turboaz.model.Car;
import com.khayal.turboaz.model.User;
import com.khayal.turboaz.repository.CarRepository;
import com.khayal.turboaz.repository.UserRepository;
import com.khayal.turboaz.request.CreateCarRequest;
import com.khayal.turboaz.request.UpdateCarRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CarService {

    private final CarRepository carRepository;
    private final UserRepository userRepository;
    private final CarDtoConverter converter;
    private final AuthenticationService authenticationService;

    public List<CarDto> showAllCars() {
        List<CarDto> returnValue = new ArrayList<>();
        List<Car> all = carRepository.findAll();
        for (Car car : all) {
            CarDto carDto = new CarDto();
            carDto.setBrand(car.getBrand());
            carDto.setCity(car.getCity());
            carDto.setColor(car.getColor());
            carDto.setYear(car.getYear());
            carDto.setPrice(car.getPrice());
            carDto.setModel(car.getModel());
            carDto.setUser(car.getUser());
            returnValue.add(carDto);
        }
        return returnValue;
    }

    public CarDto creatCarAdvert(final CreateCarRequest createCarRequest) {

        User user = authenticationService.findUserById(createCarRequest.getUserId());

        if (!findEmail(createCarRequest.getEmail()) || !user.isActive()) {
            throw new EmailNotFoundException(
                    "Email is not valid by following User : " + createCarRequest.getUserId()+" or "+
                    "User is not active for uploading your advertisement by following name : "+user.getUsername());
        } else {
            Car car = new Car(
                    createCarRequest.getBrand(),
                    createCarRequest.getModel(),
                    createCarRequest.getCity(),
                    createCarRequest.getYear(),
                    createCarRequest.getPrice(),
                    createCarRequest.getColor(),
                    user);

            return converter.convert(carRepository.save(car));
        }
    }
    public CarDto updateCarAdvert(final UpdateCarRequest updateCarRequest,Integer carId) {

        Car car = findCarById(carId);

        User user=authenticationService.findUserById(updateCarRequest.getUserId());

        boolean userEmail = findEmail(updateCarRequest.getUserEmail());
       if (!userEmail || !user.isActive()) {
           throw new EmailNotFoundException("Email is not valid by following User :" + updateCarRequest.getUserEmail());
       }else{
            Car updateCar = new Car(
                    car.getId(),
                    updateCarRequest.getBrand(),
                    updateCarRequest.getModel(),
                    updateCarRequest.getCity(),
                    updateCarRequest.getYear(),
                    updateCarRequest.getPrice(),
                    updateCarRequest.getColor(),
                    user);
            updateCar.setPublishDate(car.getPublishDate());

            return converter.convert(carRepository.save(updateCar));
        }
    }
    public void deleteCarAdvert(Integer id) {

        if (carExists(id)){
            carRepository.deleteById(id);
        }else {
            throw new CarNotFoundException("Car not found for updating by following id :" + id);
        }
    }

    protected boolean findEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    protected Car findCarById(Integer id) {
        return carRepository.findById(id).orElseThrow(() -> new CarNotFoundException("Car not found for updating by following id :" + id));
    }

    protected boolean carExists(Integer id){

        return carRepository.existsById(id);
    }

    @ExceptionHandler
    public ResponseEntity<String> handleException(EmailNotFoundException exception) {

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());

    }


}
