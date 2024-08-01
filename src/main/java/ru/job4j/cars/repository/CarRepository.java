package ru.job4j.cars.repository;

import ru.job4j.cars.model.Car;

import java.util.List;
import java.util.Optional;

public interface CarRepository {

    Optional<Car> save(Car car);

    Optional<Car> findById(Long id);

    List<Car> findAll();

    boolean deleteById(Long id);
}
