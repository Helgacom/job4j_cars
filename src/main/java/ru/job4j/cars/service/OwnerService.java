package ru.job4j.cars.service;

import ru.job4j.cars.model.Owner;

import java.util.List;
import java.util.Optional;

public interface OwnerService {

    Optional<Owner> save(Owner owner);

    Optional<Owner> findById(Long id);

    List<Owner> findAll();

    boolean deleteById(Long id);
}
