package ru.job4j.cars.service;

import ru.job4j.cars.model.Type;

import java.util.List;
import java.util.Optional;

public interface TypeService {

    Type save(Type type);

    Optional<Type> findById(Long id);

    List<Type> findAll();
}
