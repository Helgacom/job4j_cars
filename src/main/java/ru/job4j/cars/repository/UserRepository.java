package ru.job4j.cars.repository;

import lombok.AllArgsConstructor;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import ru.job4j.cars.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class UserRepository {

    private final SessionFactory sf;


    public User create(User user) {
        var session = sf.openSession();
        try {
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        }
        return user;
    }

    public void update(User user) {
        var session = sf.openSession();
        try {
            session.beginTransaction();
            session.createQuery(
                            "UPDATE User SET password = :fPassword WHERE id = :fId")
                    .setParameter("fPassword", "new password")
                    .setParameter("fId", user.getId())
                    .executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        }
    }

    public void delete(int userId) {
        var session = sf.openSession();
        try {
            session.beginTransaction();
            session.createQuery(
                            "DELETE User WHERE id = :fId")
                    .setParameter("fId", userId)
                    .executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        }
    }

    public List<User> findAllOrderById() {
        var session = sf.openSession();
        List<User> userList = new ArrayList<>();
        try {
            session.beginTransaction();
            userList = session.createQuery("FROM User", User.class).getResultList();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        }
        return userList;
    }

    public Optional<User> findById(int userId) {
        var session = sf.openSession();
        Optional<User> userOptional = Optional.empty();
        try {
            session.beginTransaction();
            Query<User> query = session.createQuery(
                    "FROM User as u where u.id = :fId", User.class);
            query.setParameter("fId", userId);
            userOptional = Optional.of(query.getSingleResult());
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        }
        return userOptional;
    }

    public List<User> findByLikeLogin(String key) {
        var session = sf.openSession();
        List<User> userList = new ArrayList<>();
        try {
            session.beginTransaction();
            Query<User> query = session.createQuery(
                    "FROM User as u where u.login LIKE :fKey", User.class);
            query.setParameter("fKey", "%" + key + "%");
            userList = query.getResultList();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        }
        return userList;
    }

    public Optional<User> findByLogin(String login) {
        var session = sf.openSession();
        try {
            Query<User> query = session.createQuery(
                    "FROM User as u where u.login = :fLogin", User.class);
            query.setParameter("fLogin", login);
            return Optional.ofNullable(query.getSingleResult());
        } catch (Exception e) {
            session.getTransaction().rollback();
        }
        return Optional.empty();
    }
}