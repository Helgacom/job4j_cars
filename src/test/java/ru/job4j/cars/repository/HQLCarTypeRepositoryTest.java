package ru.job4j.cars.repository;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.job4j.cars.model.Type;

import static org.assertj.core.api.Assertions.*;

class HQLCarTypeRepositoryTest {

    private static HQLCarTypeRepository carTypeRepository;
    private static SessionFactory sf;

    @BeforeAll
    public static void initRepository() {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        carTypeRepository = new HQLCarTypeRepository(new CrudRepository(sf));
    }

    @Test
    void save() {
        var type = new Type();
        type.setName("test");
        assertThat(carTypeRepository.save(type)).isEqualTo(type);
    }

    @Test
    void findById() {
        var type = new Type();
        type.setName("test");
        carTypeRepository.save(type);
        var rsl = carTypeRepository.findById(type.getId()).get();
        assertThat(rsl).isEqualTo(type);
    }
}