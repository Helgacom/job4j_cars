package ru.job4j.cars.service;

import ru.job4j.cars.model.Post;

import java.util.Collection;
import java.util.List;

public interface PostService {

    Post save(Post post);

    List<Post> findAll();

    Collection<Post> findPostsByToday();

    Collection<Post> findAllWithPhotos();

    Collection<Post> findPostsByBrand(String brand);
}
