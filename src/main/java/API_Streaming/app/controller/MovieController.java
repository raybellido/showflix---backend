package API_Streaming.app.controller;

import API_Streaming.app.dto.request.MovieRequest;
import API_Streaming.app.dto.response.MovieResponse;
import API_Streaming.app.entity.Movie;
import API_Streaming.app.service.implementation.MovieServiceImp;
import API_Streaming.app.service.interfaces.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/movies")
@RequiredArgsConstructor

public class MovieController {

    private final MovieService movieService;

    @GetMapping
    public List<MovieResponse> findAll() {
        return movieService.findAll();
    }

    @GetMapping("/{id}")
    public MovieResponse findById(@PathVariable Long id) {
        return movieService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MovieResponse create(
            @RequestBody MovieRequest request) {

        return movieService.create(request);

    }

    @PutMapping("/{id}")
    public MovieResponse update(
            @PathVariable Long id,
            @RequestBody MovieRequest request) {

        return movieService.update(id, request);

    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(
            @PathVariable Long id) {

        movieService.delete(id);

    }

}
