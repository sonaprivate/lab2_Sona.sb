package lab2_sona.sb.repositories;

import lab2_sona.sb.dtos.MovieDto;
import lab2_sona.sb.entities.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public interface MovieRepository extends JpaRepository<Movie, Long> {
    List<MovieDto> getAllByName(String name);

    List<Movie> findByName(String name);
}
 