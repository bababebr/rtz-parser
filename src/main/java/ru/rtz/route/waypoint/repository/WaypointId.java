package ru.rtz.route.waypoint.repository;

import lombok.*;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor(staticName = "create")
@Getter
@Setter
@EqualsAndHashCode
public class WaypointId implements Serializable {

    @Column(name = "route_id")
    private long routeId;
    private long id;
}
