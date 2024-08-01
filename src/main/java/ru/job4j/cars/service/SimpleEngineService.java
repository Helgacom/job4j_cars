package ru.job4j.cars.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.cars.model.Engine;
import ru.job4j.cars.repository.HQLEngineRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SimpleEngineService implements EngineService {

    private final HQLEngineRepository engineRepository;

    @Override
    public Optional<Engine> save(Engine engine) {
        return engineRepository.save(engine);
    }

    @Override
    public Optional<Engine> findById(Long id) {
        return engineRepository.findById(id);
    }

    @Override
    public List<Engine> findAll() {
        return engineRepository.findAll();
    }

    @Override
    public boolean deleteById(Long id) {
        return engineRepository.deleteById(id);
    }
}
