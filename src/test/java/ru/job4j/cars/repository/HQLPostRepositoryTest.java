package ru.job4j.cars.repository;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.job4j.cars.model.*;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class HQLPostRepositoryTest {

    private static SessionFactory sf;
    private static CrudRepository crudRepository;
    private static HQLPostRepository postRepository;
    private static HQLFileRepository fileRepository;
    private static HQLCarTypeRepository carTypeRepository;

    @BeforeAll
    public static void init() {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        crudRepository = new CrudRepository(sf);
        postRepository = new HQLPostRepository(crudRepository);
        fileRepository = new HQLFileRepository(crudRepository);
        carTypeRepository = new HQLCarTypeRepository(crudRepository);
    }

    @Test
    void whenSavaPost() {
        var post = new Post();
        var rsl = postRepository.save(post);
        assertThat(rsl).isEqualTo(post);
    }

    @Test
    void findPostsByToday() {
        var post = new Post();
        postRepository.save(post);
        assertThat(postRepository.findPostsByToday()).contains(post);
    }

    @Test
    void findAllWithPhotos() {
        var post = new Post();
        File file = new File();
        file.setName("name");
        file.setPath("path");
        fileRepository.save(file);
        post.setFileList(List.of(file));
        postRepository.save(post);
        var rsl = postRepository.findAllWithPhotos();
        assertThat(rsl).contains(post);
    }

    @Test
    void findPostsByCarType() {
        var post = new Post();
        File file = new File();
        file.setName("name");
        file.setPath("path");
        fileRepository.save(file);
        var type = new Type();
        type.setName("type");
        carTypeRepository.save(type);
        post.setType(type);
        post.setFileList(List.of(file));
        postRepository.save(post);
        assertThat(postRepository.findPostsByCarType("type")).contains(post);
    }
}