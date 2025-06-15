package ru.rtz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.rtz.model.Route;

@Repository
public interface RouteRepository extends JpaRepository<Route, Long> {
}
