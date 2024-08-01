package ru.job4j.cars.repository;

import ru.job4j.cars.model.Type;

import java.util.List;
import java.util.Optional;

public interface CarTypeRepository {

    Type save(Type type);

    Optional<Type> findById(Long id);

    List<Type> findAll();
}
