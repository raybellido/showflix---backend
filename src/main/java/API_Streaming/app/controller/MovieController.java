package API_Streaming.app.controller;

import API_Streaming.app.dto.request.MovieRequest;
import API_Streaming.app.dto.response.MovieResponse;
import API_Streaming.app.service.interfaces.MovieService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/movies")
@RequiredArgsConstructor
@Tag(name = "Movies", description = "Endpoints para administrar películas")

public class MovieController {

    private final MovieService movieService;

    @GetMapping
    public Page<MovieResponse> getMovies(@RequestParam(required = false) String title, @RequestParam(required = false) Long genreId, Pageable pageable) {

        boolean hasTitle = title != null && !title.isBlank();
        boolean hasGenre = genreId != null;

        if (hasTitle && hasGenre) {return movieService.searchByTitleAndGenre(title, genreId, pageable);}

        if (hasTitle) {return movieService.searchByTitle(title, pageable);}

        if (hasGenre) {return movieService.findByGenre(genreId, pageable);}

        return movieService.findAll(pageable);
    }

    @GetMapping("/{id}")
    public MovieResponse findById(@PathVariable Long id) {
        return movieService.findById(id);
    }


    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MovieResponse create(@Valid @RequestBody MovieRequest request) {

        return movieService.create(request);

    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public MovieResponse update(@Valid @PathVariable Long id, @RequestBody MovieRequest request) {

        return movieService.update(id, request);

    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {

        movieService.delete(id);

    }

}
