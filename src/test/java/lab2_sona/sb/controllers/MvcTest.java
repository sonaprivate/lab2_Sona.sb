package lab2_sona.sb.controllers;


import com.fasterxml.jackson.databind.ObjectMapper;
import lab2_sona.sb.dtos.MovieDto;
import lab2_sona.sb.mappers.MovieMapper;
import lab2_sona.sb.services.Service;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MovieController.class)

public class MvcTest {

    @MockBean
    Service service;

    @Autowired
    private MockMvc mockMvc;


    //GETALL
    @Test
    void callingWithUrlMoviesShouldReturnAllMoviesAsJson() throws Exception {
        when(service.getAllMovies()).thenReturn(List.of(new MovieDto(1,"", "")));

        var result = mockMvc.perform(MockMvcRequestBuilders.get("/movies")
                .accept(MediaType.APPLICATION_JSON)).andReturn();

        assertThat(result.getResponse().getStatus()).isEqualTo(200);

    }

    //GETONE
    @Test
    void callingWithUrlMoviesShouldReturnWithMovieIdAsJson() throws Exception {
        when(service.getOne(1L)).thenReturn(Optional.of(new MovieDto(1L,"", "")));

        var result = mockMvc.perform(MockMvcRequestBuilders.get("/movies/{id}", 1L)
                .accept(MediaType.APPLICATION_JSON)).andReturn();
        assertThat(result.getResponse().getStatus()).isEqualTo(200);

    }

    //GETSEARCH
    @Test
    public void callingWithSearchUrlShouldReturnMovieByName() throws Exception{
        when(service.getAllByName("Name")).thenReturn(List.of(new MovieDto(1L,"Name", "")));
        var result =mockMvc.perform(MockMvcRequestBuilders.get("/movies/search/{name}")
                .accept(MediaType.APPLICATION_JSON))
                .andReturn();
        assertThat(result.getResponse().getStatus()).isEqualTo(200);

    }

    //POST
   @Test
    void callingWithPostUrlMoviesShouldReturnCreatedMoviesAsJson() throws Exception{
        //when(service.createMovie(any(MovieDto.class))).thenReturn(new MovieDto(1L, "",""));

        mockMvc.perform(MockMvcRequestBuilders.post("/movies")
                .content(asJsonString(new MovieDto(1L, "Name","Director")))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    public static String asJsonString(final Object object) {
        try {
            return new ObjectMapper().writeValueAsString(object);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    //PUT
    @Test
    public void callingWithPutUrlShouldReplaceMoviesAsJason() throws Exception{

        mockMvc.perform(MockMvcRequestBuilders.put("/movies/{id}",3)
                .content(asJsonString(new MovieDto(1L, "","")))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

    }


    //PATCH
    @Test
    public void callingWithPatchUrlShouldUpdateMoviesAsJason() throws Exception{

        mockMvc.perform(MockMvcRequestBuilders.patch("/movies/{id}",3)
                .content(asJsonString(new MovieDto(1L, "","")))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    //DELETE
    @Test
    public void callingWithDeleteUrlShouldDeleteMoviesAsJason() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.delete("/movies/{id}", 3))
                .andExpect(status().isOk());

    }





}
