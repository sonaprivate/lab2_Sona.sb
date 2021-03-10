package lab2_sona.sb.services;

import lab2_sona.sb.dtos.MovieDto;
import lab2_sona.sb.dtos.MovieName;

import java.util.List;
import java.util.Optional;

public interface Service {
    List<MovieDto> getAllMovies();

    Optional<MovieDto> getOne(Long id);

    MovieDto createMovie(MovieDto movie);

    void delete(Long id);

    //Put
    MovieDto replace(Long id, MovieDto movieDto);

    //Patch
    MovieDto update(Long id, MovieName movieName);

    List<MovieDto> getAllByName(String name);
}
