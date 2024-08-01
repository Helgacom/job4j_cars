package ru.job4j.cars.timezone;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TimeZone;

@Data
@Component
public class TimeZones {

    private List<String> zones;

    public TimeZones() {
        this.zones = new ArrayList<>();
        load();
    }

    public void load() {
        Arrays.stream(TimeZone.getAvailableIDs()).distinct()
                .forEach(id -> zones.add(id));
    }
}
