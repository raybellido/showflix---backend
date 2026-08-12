package API_Streaming.app.controller;

import API_Streaming.app.dto.response.GenreResponse;
import API_Streaming.app.entity.Genre;
import API_Streaming.app.service.interfaces.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/genres")
public class GenreController {

    private final GenreService genreService;

    @GetMapping
    public List<GenreResponse> getGenres() {
        return genreService.findAll();
    }

}
