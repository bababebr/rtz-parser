package ru.rtz.route.waypoint.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.rtz.route.waypoint.model.Waypoint;

@Repository
public interface WaypointRepository extends JpaRepository<Waypoint, WaypointId> {
}
