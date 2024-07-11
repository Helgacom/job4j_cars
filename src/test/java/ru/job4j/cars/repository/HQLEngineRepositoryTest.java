package ru.job4j.cars.repository;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.job4j.cars.model.Engine;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

class HQLEngineRepositoryTest {

    private static HQLEngineRepository engineRepository;
    private static SessionFactory sf;

    @BeforeAll
    public static void initRepository() {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        engineRepository = new HQLEngineRepository(new CrudRepository(sf));
    }

    @Test
    void save() {
        var engine = new Engine();
        engine.setName("test");
        assertThat(engineRepository.save(engine)).isEqualTo(Optional.of(engine));
        assertThat(engineRepository.findById(engine.getId()).get().getName()).isEqualTo("test");
    }

    @Test
    void findById() {
        var engine = new Engine();
        engine.setName("test");
        engineRepository.save(engine);
        assertThat(engineRepository.findById(engine.getId())).isEqualTo(Optional.of(engine));
    }

    @Test
    void findAll() {
        var engine = new Engine();
        engine.setName("test");
        engineRepository.save(engine);
        assertThat(engineRepository.findAll()).hasSize(1)
                .isInstanceOf(List.class)
                .containsExactly(engine);
    }

    @Test
    void deleteById() {
        var engine = new Engine();
        engine.setName("test");
        engineRepository.save(engine);
        engineRepository.deleteById(engine.getId());
        assertThat(engineRepository.findAll()).doesNotContain(engine);
    }
}