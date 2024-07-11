package ru.job4j.cars.repository;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.job4j.cars.model.Car;
import ru.job4j.cars.model.HistoryOwners;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.*;

class HQLCarRepositoryTest {

    private static HQLCarRepository carRepository;
    private static SessionFactory sf;
    private static Set<HistoryOwners> historyOwners = new HashSet<>();

    @BeforeAll
    public static void initRepository() {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        carRepository = new HQLCarRepository(new CrudRepository(sf));
    }

    @Test
    void save() {
        var car = new Car();
        car.setName("test");
        car.setHistoryOwners(historyOwners);
        assertThat(carRepository.save(car)).isEqualTo(Optional.of(car));
        assertThat(carRepository.findById(car.getId()).get().getName()).isEqualTo("test");
    }

    @Test
    void findById() {
        var car = new Car();
        car.setName("test");
        car.setHistoryOwners(historyOwners);
        carRepository.save(car);
        assertThat(carRepository.findById(car.getId())).isEqualTo(Optional.of(car));
    }

    @Test
    void findAll() {
        var car = new Car();
        car.setName("test");
        car.setHistoryOwners(historyOwners);
        carRepository.save(car);
        assertThat(carRepository.findAll()).hasSize(1)
                .isInstanceOf(List.class)
                .containsExactly(car);
    }

    @Test
    void deleteById() {
        var car = new Car();
        car.setName("test");
        car.setHistoryOwners(historyOwners);
        carRepository.save(car);
        carRepository.deleteById(car.getId());
        assertThat(carRepository.findAll()).doesNotContain(car);
    }
}