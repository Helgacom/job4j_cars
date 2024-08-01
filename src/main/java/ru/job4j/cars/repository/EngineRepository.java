package ru.job4j.cars.repository;

import ru.job4j.cars.model.Engine;

import java.util.List;
import java.util.Optional;

public interface EngineRepository {

    Optional<Engine> save(Engine engine);

    Optional<Engine> findById(Long id);

    List<Engine> findAll();

    boolean deleteById(Long id);
}
