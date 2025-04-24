package fi.haaga.movie.request;

import lombok.Data;

import java.sql.Date;

import  fi.haaga.movie.enums.Genre;
import  fi.haaga.movie.enums.Language;

@Data
public class MovieRequest {
	private String movieName;
	private Integer duration;
	private Double rating;
	private Date releaseDate;
	private Genre genre;
	private Language language;
}
