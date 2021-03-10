package lab2_sona.sb.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MovieControllerTest {



    @Test
    void callingOneWithValidIdReturnsOnePerson(){
        MovieController movieController = new MovieController(new TestService());

        var movie = movieController.one(1L);

        assertThat(movie.getId()).isEqualTo(1);
        assertThat(movie.getName()).isEqualTo("Test");
        assertThat(movie.getDirector()).isEqualTo("Test");
    }

    @Test
    void callingOneWithInvalidIdThrowsExceptionWithResponseStatus404(){
        MovieController movieController = new MovieController(new TestService());

        var movie = movieController.one(1L);

        var exception = assertThrows(ResponseStatusException.class, () -> movieController.one(2L));
        assertThat(exception.getStatus()).isEqualTo(HttpStatus.NOT_FOUND);

    }



}