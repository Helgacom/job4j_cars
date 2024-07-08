package ru.job4j.cars.repository;

import lombok.AllArgsConstructor;
import ru.job4j.cars.model.Post;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.Map;

@AllArgsConstructor
public class HQLPostRepository implements PostRepository {

    private final CrudRepository crudRepository;

    @Override
    public Collection<Post> findPostsByToday() {
        LocalDateTime today = LocalDateTime.now().minusDays(1).truncatedTo(ChronoUnit.DAYS);
        return crudRepository.query("FROM Post p WHERE p.created > :today", Post.class,
                Map.of("today", today));
    }

    @Override
    public Collection<Post> findAllWithPhotos() {
        return crudRepository.query("SELECT FROM Post p WHERE SIZE(p.fileList) > 0 order by p.id", Post.class);
    }

    @Override
    public Collection<Post> findPostsByCarType(String type) {
        return crudRepository.query("FROM Post p WHERE p.carType.name = :fType", Post.class,
                Map.of("fType", type));
    }
}
