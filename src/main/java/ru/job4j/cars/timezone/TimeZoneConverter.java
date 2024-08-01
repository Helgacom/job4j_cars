package ru.job4j.cars.timezone;

import ru.job4j.cars.model.Post;
import ru.job4j.cars.model.User;

import java.time.ZoneId;
import java.util.Collection;

public class TimeZoneConverter {

    public static Collection<Post> convert(User user, Collection<Post> posts) {
        for (Post post : posts) {
            post.setCreated(post.getCreated()
                    .atZone(ZoneId.of("UTC"))
                    .withZoneSameInstant(ZoneId.of(user.getTimezone())).toLocalDateTime());
        }
        return posts;
    }
}
