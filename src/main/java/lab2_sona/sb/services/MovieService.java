package lab2_sona.sb.services;

import lab2_sona.sb.dtos.MovieDto;
import lab2_sona.sb.dtos.MovieName;
import lab2_sona.sb.entities.Movie;
import lab2_sona.sb.mappers.MovieMapper;
import lab2_sona.sb.repositories.MovieRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;


@Service
public class MovieService implements lab2_sona.sb.services.Service {

    private final MovieMapper movieMapper;
    private MovieRepository movieRepository;

    public MovieService (MovieRepository movieRepository, MovieMapper movieMapper) {
        this.movieRepository = movieRepository;
        this.movieMapper = movieMapper;
    }

    @Override
    public List<MovieDto> getAllMovies(){
        return movieMapper.mapp(movieRepository.findAll());
    }


    @Override
    public Optional<MovieDto> getOne(Long id){
        return movieMapper.mapp(movieRepository.findById(id));
    }


    @Override
    public MovieDto createMovie(MovieDto movie){
        if (movie.getName().isEmpty())
            throw new RuntimeException();
        return movieMapper.mapp(movieRepository.save(movieMapper.mapp(movie)));
    }

    @Override
    public void delete(Long id) {
        movieRepository.deleteById(id);
    }

    //Put
    @Override
    public MovieDto replace(Long id, MovieDto movieDto) {
        Optional<Movie> movie = movieRepository.findById(id);
        if (movie.isPresent()){
            Movie updatedMovie = movie.get();
            updatedMovie.setName(movieDto.getName());
            updatedMovie.setDirector(movieDto.getDirector());
            return movieMapper.mapp(movieRepository.save(updatedMovie));
        }
        else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Id " + id + " not found.");
    }

    //Patch
    @Override
    public MovieDto update(Long id, MovieName movieName) {
        Optional<Movie> movie = movieRepository.findById(id);
        if (movie.isPresent()){

            Movie updatedMovie = movie.get();
            if (movieName.name != null);
                updatedMovie.setName(movieName.name);
            return movieMapper.mapp(movieRepository.save(updatedMovie));
        }
        else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Id " + id + " not found.");
    }

    @Override
    public List<MovieDto> getAllByName(String name) {
        return movieMapper.mapp(movieRepository.findByName(name));
            }

}
