package ru.job4j.cars.repository;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.job4j.cars.model.HistoryOwners;
import ru.job4j.cars.model.Owner;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.*;

class HQLOwnerRepositoryTest {

    private static HQLOwnerRepository ownerRepository;
    private static SessionFactory sf;
    private static Set<HistoryOwners> historyOwners = new HashSet<>();

    @BeforeAll
    public static void initRepository() {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        ownerRepository = new HQLOwnerRepository(new CrudRepository(sf));
    }

    @Test
    void save() {
        var owner = new Owner();
        owner.setHistoryOwners(historyOwners);
        assertThat(ownerRepository.save(owner)).isEqualTo(Optional.of(owner));
    }

    @Test
    void findById() {
        var owner = new Owner();
        owner.setHistoryOwners(historyOwners);
        ownerRepository.save(owner);
        assertThat(ownerRepository.findById(owner.getId())).isEqualTo(Optional.of(owner));
    }

    @Test
    void findAll() {
        var owner = new Owner();
        owner.setHistoryOwners(historyOwners);
        ownerRepository.save(owner);
        assertThat(ownerRepository.findAll()).hasSize(1)
                .isInstanceOf(List.class)
                .containsExactly(owner);
    }

    @Test
    void deleteById() {
        var owner = new Owner();
        owner.setHistoryOwners(historyOwners);
        ownerRepository.save(owner);
        ownerRepository.deleteById(owner.getId());
        assertThat(ownerRepository.findAll()).doesNotContain(owner);
    }
}