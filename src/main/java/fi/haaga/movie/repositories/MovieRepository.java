package fi.haaga.movie.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import fi.haaga.movie.entities.Movie;

public interface MovieRepository extends JpaRepository<Movie, Integer> {
	Movie findByMovieName(String name);
}