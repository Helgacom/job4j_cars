package ru.job4j.cars.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.Brand;
import ru.job4j.cars.model.File;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class HQLBrandRepository implements BrandRepository {

    private final CrudRepository crudRepository;

    @Override
    public Optional<Brand> save(Brand brand) {
        Optional<Brand> rsl = Optional.empty();
        try {
            crudRepository.run(session -> session.persist(brand));
            rsl = Optional.of(brand);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rsl;
    }

    @Override
    public List<Brand> findAll() {
        return crudRepository.query("FROM Brand", Brand.class);
    }

    @Override
    public Optional<Brand> findById(Long id) {
        return crudRepository.optional(
                "FROM  Brand WHERE id = :fId", Brand.class,
                Map.of("fId", id));
    }

    @Override
    public boolean deleteById(Long id) {
        return crudRepository.runBoolean("DELETE Brand WHERE id = :fId",
                Map.of("fId", id));
    }
}
