package ru.rtz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.rtz.model.Waypoint;
import ru.rtz.model.WaypointId;

@Repository
public interface WaypointRepository extends JpaRepository<Waypoint, WaypointId> {
}
