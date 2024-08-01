package ru.job4j.cars.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.CarModel;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class HQLModelRepository implements ModelRepository {

    private final CrudRepository crudRepository;

    @Override
    public Optional<CarModel> save(CarModel model) {
        Optional<CarModel> rsl = Optional.empty();
        try {
            crudRepository.run(session -> session.persist(model));
            rsl = Optional.of(model);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rsl;
    }

    @Override
    public List<CarModel> findAll() {
        return crudRepository.query("FROM CarModel", CarModel.class);
    }

    @Override
    public Optional<CarModel> findById(Long id) {
        return crudRepository.optional(
                "FROM CarModel WHERE id = :fId", CarModel.class,
                Map.of("fId", id));
    }

    @Override
    public boolean deleteById(Long id) {
        return crudRepository.runBoolean("DELETE CarModel WHERE id = :fId",
                Map.of("fId", id));
    }
}
