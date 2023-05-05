package ru.elmanov.mongodb.demo.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import ru.elmanov.mongodb.demo.model.Car;
import ru.elmanov.mongodb.demo.repository.CarRepository;

@Service
@Slf4j
@RequiredArgsConstructor
public class CarService {

    private final CarRepository carRepository;

    public Car createCar(Car car) {
        if (Boolean.TRUE.equals(carRepository.exists(Example.of(car)))) {
            var mess = "car exists with id: %s".formatted(car.getId());
            log.warn(mess);
            return null;
        }
        return carRepository.insert(car);
    }
}
