package route.route.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import route.route.model.Route;

@Repository
public interface RouteRepository extends JpaRepository<Route, Long> {
}
