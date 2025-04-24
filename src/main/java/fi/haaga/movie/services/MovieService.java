package fi.haaga.movie.services;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import fi.haaga.movie.convertor.MovieConvertor;
import fi.haaga.movie.entities.Movie;
import fi.haaga.movie.exceptions.MovieAlreadyExist;
import fi.haaga.movie.repositories.MovieRepository;
import fi.haaga.movie.request.MovieRequest;
import fi.haaga.movie.exceptions.MovieNotFound;

@Service
public class MovieService {
	
	@Autowired
	private MovieRepository movieRepository;
	
	public String addMovie(MovieRequest movieRequest) {
		Movie movieByName = movieRepository.findByMovieName(movieRequest.getMovieName());
		
		if (movieByName != null && movieByName.getLanguage().equals(movieRequest.getLanguage())) {
			throw new MovieAlreadyExist();
		}
		
		Movie movie = MovieConvertor.movieDtoToMovie(movieRequest);
		
		movieRepository.save(movie);
		return "The movie has been added successfully";
	}
	public String deleteMovie(Long movieId) {
      
        Movie movie = movieRepository.findById(movieId).orElse(null);

        if (movie == null) {
            throw new MovieNotFound();
        }

        
        movieRepository.delete(movie);
        return "The movie has been deleted successfully";
    }

	public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

}
