package ru.job4j.cars.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.cars.model.Type;
import ru.job4j.cars.repository.HQLCarTypeRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SimpleTypeService implements TypeService {

    private final HQLCarTypeRepository carTypeRepository;

    @Override
    public Type save(Type type) {
        return carTypeRepository.save(type);
    }

    @Override
    public Optional<Type> findById(Long id) {
        return carTypeRepository.findById(id);
    }

    @Override
    public List<Type> findAll() {
        return carTypeRepository.findAll();
    }
}
