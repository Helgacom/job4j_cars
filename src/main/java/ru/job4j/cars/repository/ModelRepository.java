package ru.job4j.cars.repository;

import ru.job4j.cars.model.CarModel;

import java.util.List;
import java.util.Optional;

public interface ModelRepository {

    Optional<CarModel> save(CarModel model);

    List<CarModel> findAll();

    Optional<CarModel> findById(Long id);

    boolean deleteById(Long id);
}
