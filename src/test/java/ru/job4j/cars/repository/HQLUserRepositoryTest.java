package ru.job4j.cars.repository;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.job4j.cars.model.User;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

class HQLUserRepositoryTest {

    private static HQLUserRepository userRepository;
    private static SessionFactory sf;

    @BeforeAll
    public static void initRepository() {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        userRepository = new HQLUserRepository(new CrudRepository(sf));
    }

    @Test
    void whenCreateUser() {
        var user = new User();
        user.setLogin("user");
        user.setPassword("test");
        assertThat(userRepository.create(user)).isEqualTo(user);
        assertThat(userRepository.findById(user.getId())).isEqualTo(Optional.of(user));
    }

    @Test
    void whenDeleteUser() {
        var user = new User();
        user.setLogin("user");
        user.setPassword("test");
        userRepository.create(user);
        userRepository.delete(user.getId());
        assertThat(userRepository.findById(user.getId())).isEmpty();
    }

    @Test
    void findAllOrderById() {
        var user1 = new User();
        user1.setLogin("user1");
        user1.setPassword("test1");
        var user2 = new User();
        user2.setLogin("user2");
        user2.setPassword("test2");
        userRepository.create(user1);
        userRepository.create(user2);
        assertThat(userRepository.findAllOrderById()).contains(user1, user2);
    }

    @Test
    void findById() {
        var user = new User();
        user.setLogin("user");
        user.setPassword("test");
        userRepository.create(user);
        assertThat(userRepository.findById(user.getId())).isEqualTo(Optional.of(user));
    }

    @Test
    void findByLikeLogin() {
        var user = new User();
        user.setLogin("user1");
        user.setPassword("test1");
        userRepository.create(user);
        assertThat(userRepository.findByLikeLogin("user1")).contains(user);
    }

    @Test
    void findByLoginAndPassword() {
        var user = new User("admin", "test");
        userRepository.create(user);
        var rsl = userRepository.findByLoginAndPassword(user.getLogin(), user.getPassword()).get();
        assertThat(rsl).isEqualTo(user);
    }
}