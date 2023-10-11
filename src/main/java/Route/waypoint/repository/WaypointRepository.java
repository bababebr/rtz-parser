package route.waypoint.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import route.waypoint.model.Waypoint;

@Repository
public interface WaypointRepository extends JpaRepository<Waypoint, WaypointId> {
}
