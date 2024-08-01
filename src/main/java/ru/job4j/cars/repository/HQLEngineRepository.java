package ru.job4j.cars.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.Engine;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class HQLEngineRepository implements EngineRepository {

    private final CrudRepository crudRepository;

    @Override
    public Optional<Engine> save(Engine engine) {
        Optional<Engine> rsl = Optional.empty();
        try {
            crudRepository.run(session -> session.persist(engine));
            rsl = Optional.of(engine);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rsl;
    }

    @Override
    public Optional<Engine> findById(Long id) {
        return crudRepository.optional("FROM Engine WHERE id = :fId", Engine.class,
                Map.of("fId", id));
    }

    @Override
    public List<Engine> findAll() {
        return crudRepository.query("FROM Engine c ORDER BY c.id",
                Engine.class);
    }

    @Override
    public boolean deleteById(Long id) {
        return crudRepository.runBoolean("DELETE Engine WHERE id = :fId",
                Map.of("fId", id));
    }
}
