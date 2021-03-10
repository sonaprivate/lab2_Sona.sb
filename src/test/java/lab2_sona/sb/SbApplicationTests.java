package lab2_sona.sb;

import lab2_sona.sb.dtos.MovieDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SbApplicationTests {

    @LocalServerPort
    int port;


    @Autowired
    TestRestTemplate testClient;

    @Test
    void testingGetAllMovies() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", "application/xml");

        var result = testClient.getForEntity("http://localhost:"+port+"/movies", MovieDto[].class);
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(result.getBody().length).isGreaterThan(0);

    }


}
