package ru.job4j.cars.repository;

import ru.job4j.cars.model.Post;

import java.util.Collection;

public interface PostRepository {

    Collection<Post> findPostsByToday();

    Collection<Post> findAllWithPhotos();

    Collection<Post> findPostsByCarType(String type);
}
