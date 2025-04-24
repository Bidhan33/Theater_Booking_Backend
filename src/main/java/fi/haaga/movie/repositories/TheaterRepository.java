package fi.haaga.movie.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import fi.haaga.movie.entities.Theater;

public interface TheaterRepository extends JpaRepository<Theater, Integer> {
    Theater findByAddress(String address);
}
