package ru.job4j.cars.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.Category;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class HQLCategoryRepository implements CategoryRepository {

    private final CrudRepository crudRepository;

    @Override
    public Optional<Category> save(Category category) {
        Optional<Category> rsl = Optional.empty();
        try {
            crudRepository.run(session -> session.persist(category));
            rsl = Optional.of(category);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rsl;
    }

    @Override
    public List<Category> findAll() {
        return crudRepository.query("FROM Category", Category.class);
    }

    @Override
    public Optional<Category> findById(Long id) {
        return crudRepository.optional(
                "FROM  Category WHERE id = :fId", Category.class,
                Map.of("fId", id));
    }

    @Override
    public boolean deleteById(Long id) {
        return crudRepository.runBoolean("DELETE Category WHERE id = :fId",
                Map.of("fId", id));
    }
}
