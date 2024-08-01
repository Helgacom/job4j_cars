package ru.job4j.cars.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.job4j.cars.model.File;

import java.util.List;

@AllArgsConstructor
@Getter
public class PostDto {
    private final Long categoryId;
    private final Long carModelId;
    private final Long typeId;
    private final Long engineId;
    private final String description;
    private final List<File> fileList;
}
