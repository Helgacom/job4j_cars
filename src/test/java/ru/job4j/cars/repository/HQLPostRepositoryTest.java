package ru.job4j.cars.repository;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.job4j.cars.model.*;

import static org.assertj.core.api.Assertions.*;

class HQLPostRepositoryTest {

    private static SessionFactory sf;
    private static CrudRepository crudRepository;
    private static HQLPostRepository postRepository;

    @BeforeAll
    public static void init() {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        crudRepository = new CrudRepository(sf);
        postRepository = new HQLPostRepository(crudRepository);
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
}