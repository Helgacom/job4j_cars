package ru.job4j.cars.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.cars.model.Post;
import ru.job4j.cars.repository.HQLPostRepository;

import java.util.Collection;
import java.util.List;

@Service
@AllArgsConstructor
public class SimplePostService implements PostService {

    private final HQLPostRepository postRepository;

    @Override
    public Post save(Post post) {
        return postRepository.save(post);
    }

    @Override
    public List<Post> findAll() {
        return postRepository.findAll();
    }

    @Override
    public Collection<Post> findPostsByToday() {
        return postRepository.findPostsByToday();
    }

    @Override
    public Collection<Post> findAllWithPhotos() {
        return postRepository.findAllWithPhotos();
    }

    @Override
    public Collection<Post> findPostsByBrand(String brand) {
        return postRepository.findPostsByBrand(brand);
    }
}
