package ru.job4j.cars.service;

import ru.job4j.cars.dto.FileDto;
import ru.job4j.cars.model.File;

import java.util.List;
import java.util.Optional;

public interface FileService {

    File save(FileDto fileDto);

    List<File> findAll();

    Optional<FileDto> findById(Long id);

    boolean deleteById(Long id);
}
