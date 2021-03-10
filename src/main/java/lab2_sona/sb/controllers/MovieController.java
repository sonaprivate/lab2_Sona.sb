package lab2_sona.sb.controllers;


import lab2_sona.sb.dtos.MovieDto;
import lab2_sona.sb.dtos.MovieName;
import lab2_sona.sb.services.Service;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class MovieController {

    private Service service;

    public MovieController(Service Service) {
        this.service = Service;
    }

    //GET (ALL)
    @GetMapping("/movies")
    public List<MovieDto> all(){
        return service.getAllMovies();
    }

    //GET (ONE)
    @GetMapping("/movies/{id}")
    public MovieDto one(@PathVariable Long id){
        return service.getOne(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Id " + id + " not found."));
    }

    //GETSEARCH
    @GetMapping ("/movies/search/{name}")
    public List<MovieDto> name(@PathVariable String name){
        return service.getAllByName(name);
    }


    //POST
    @PostMapping("/movies")
    @ResponseStatus(HttpStatus.CREATED)
    public MovieDto create(@RequestBody MovieDto movie){
        return service.createMovie(movie);
    }


    //Delete
    @DeleteMapping("/movies/{id}")
    public void delete(@PathVariable Long id){
        service.delete(id);
    }

    //PUT
    @PutMapping("/movies/{id}")
    public MovieDto replace(@RequestBody MovieDto movieDto, @PathVariable Long id) {
        return service.replace(id, movieDto);
    }

    //PATCH
    @PatchMapping ("/movies/{id}")
    public MovieDto update(@RequestBody MovieName movieName, @PathVariable Long id) {
        return service.update(id, movieName);
    }


}
