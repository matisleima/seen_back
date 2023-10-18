package com.example.seen_back.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LocationRepository extends JpaRepository<Location, Integer> {
    @Query("select l from Location l where l.status = ?1")
    List<Location> findLocationsBy(String status);

    @Query("select l from Location l where l.id = ?1")
    Location getLocationBy(Integer id);
}