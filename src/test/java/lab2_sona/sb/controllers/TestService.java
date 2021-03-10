package lab2_sona.sb.controllers;

import lab2_sona.sb.dtos.MovieDto;
import lab2_sona.sb.dtos.MovieName;
import lab2_sona.sb.services.Service;

import java.util.List;
import java.util.Optional;

public class TestService implements Service {
    @Override
    public List<MovieDto> getAllMovies() {
        return List.of(new MovieDto(1, "Test", "Test"), new MovieDto(2, "Test", "Test"));
    }

    @Override
    public Optional<MovieDto> getOne(Long id) {
        if (id == 1)
            return Optional.of(new MovieDto(1, "Test", "Test"));
        return Optional.empty();
    }

    @Override
    public MovieDto createMovie(MovieDto movie) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public MovieDto replace(Long id, MovieDto movieDto) {
        return null;
    }

    @Override
    public MovieDto update(Long id, MovieName movieName) {
        return null;
    }

    @Override
    public List<MovieDto> getAllByName(String name) {
        return null;
    }
}
