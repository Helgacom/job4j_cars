package ru.job4j.cars.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.cars.model.CarModel;
import ru.job4j.cars.repository.HQLModelRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SimpleModelService implements ModelService {

    private final HQLModelRepository modelRepository;

    @Override
    public Optional<CarModel> save(CarModel model) {
        return modelRepository.save(model);
    }

    @Override
    public List<CarModel> findAll() {
        return modelRepository.findAll();
    }

    @Override
    public Optional<CarModel> findById(Long id) {
        return modelRepository.findById(id);
    }

    @Override
    public boolean deleteById(Long id) {
        return modelRepository.deleteById(id);
    }
}
