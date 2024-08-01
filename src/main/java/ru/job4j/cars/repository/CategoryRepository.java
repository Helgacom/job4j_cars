package ru.job4j.cars.repository;

import ru.job4j.cars.model.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository {

    Optional<Category> save(Category category);

    List<Category> findAll();

    Optional<Category> findById(Long id);

    boolean deleteById(Long id);
}
