package ru.job4j.cars.service;

import ru.job4j.cars.model.Brand;

import java.util.List;
import java.util.Optional;

public interface BrandService {

    Optional<Brand> save(Brand brand);

    List<Brand> findAll();

    Optional<Brand> findById(Long id);

    boolean deleteById(Long id);
}
