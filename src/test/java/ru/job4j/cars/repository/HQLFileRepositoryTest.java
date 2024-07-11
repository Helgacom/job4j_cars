package ru.job4j.cars.repository;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.job4j.cars.model.File;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

class HQLFileRepositoryTest {

    private static HQLFileRepository fileRepository;
    private static SessionFactory sf;

    @BeforeAll
    public static void initRepository() {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        fileRepository = new HQLFileRepository(new CrudRepository(sf));
    }

    @Test
    void save() {
        var file = new File();
        file.setName("test");
        file.setPath("path");
        assertThat(fileRepository.save(file).getPath()).isEqualTo("path");
        assertThat(fileRepository.findById(file.getId()).get().getName()).isEqualTo("test");
    }

    @Test
    void findById() {
        var file = new File();
        file.setName("test");
        file.setPath("path");
        fileRepository.save(file);
        assertThat(fileRepository.findById(file.getId())).isEqualTo(Optional.of(file));
    }

    @Test
    void findAll() {
        var file1 = new File();
        file1.setName("test1");
        file1.setPath("path1");
        var file2 = new File();
        file2.setName("test2");
        file2.setPath("path2");
        fileRepository.save(file1);
        fileRepository.save(file2);
        assertThat(fileRepository.findAll()).isEqualTo(List.of(file1, file2));
    }

    @Test
    void deleteById() {
        var file = new File();
        file.setName("test");
        file.setPath("path");
        fileRepository.save(file);
        fileRepository.deleteById(file.getId());
        assertThat(fileRepository.findAll()).doesNotContain(file);
    }
}