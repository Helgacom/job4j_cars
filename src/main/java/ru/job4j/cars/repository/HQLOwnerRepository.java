package ru.job4j.cars.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.Owner;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class HQLOwnerRepository implements OwnerRepository {

    private final CrudRepository crudRepository;

    @Override
    public Optional<Owner> save(Owner owner) {
        Optional<Owner> rsl = Optional.empty();
        try {
            crudRepository.run(session -> session.persist(owner));
            rsl = Optional.of(owner);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rsl;
    }

    @Override
    public Optional<Owner> findById(int id) {
        return crudRepository.optional("FROM Owner WHERE id = :fId", Owner.class,
                Map.of("fId", id));
    }

    @Override
    public Collection<Owner> findAll() {
        return crudRepository.query("FROM Owner c ORDER BY c.id",
                Owner.class);
    }

    @Override
    public boolean deleteById(int id) {
        return crudRepository.runBoolean("DELETE Owner WHERE id = :fId",
                Map.of("fId", id));
    }
}
