package ru.rtz.route.waypoint.model;

import lombok.*;
import lombok.experimental.FieldDefaults;
import ru.rtz.units.Coordinates;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor(staticName = "create")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class WaypointDto {
    long id;
    Coordinates pos;
}
