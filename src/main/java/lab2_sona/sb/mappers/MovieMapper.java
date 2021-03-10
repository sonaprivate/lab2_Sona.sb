package lab2_sona.sb.mappers;

import lab2_sona.sb.dtos.MovieDto;
import lab2_sona.sb.entities.Movie;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class MovieMapper {
    public MovieMapper() {
    }

    public MovieDto mapp(Movie movie) {
        return new MovieDto(movie.getId(), movie.getName(), movie.getDirector());
    }

    public Movie mapp(MovieDto movieDto) {
        return new Movie(movieDto.getId(), movieDto.getName(), movieDto.getDirector());
    }

    public Optional<MovieDto> mapp(Optional<Movie> optionalMovie) {
        if (optionalMovie.isEmpty())
            return Optional.empty();
        return Optional.of(mapp(optionalMovie.get()));
    }

    public List<MovieDto> mapp(List<Movie> all) {
        return all
                .stream()
                .map(this::mapp)
                .collect(Collectors.toList());
    }
}