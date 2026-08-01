package API_Streaming.app.controller;

import API_Streaming.app.dto.response.MovieResponse;
import API_Streaming.app.service.interfaces.FavoriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/favorites")
@RequiredArgsConstructor

public class FavoriteController {

    private final FavoriteService favoriteService;


    @PostMapping("/{movieId}")
    public ResponseEntity<Void> addFavorite(@PathVariable Long movieId) {
        favoriteService.addFavorite(movieId);
        return ResponseEntity.ok().build();
    }


    @DeleteMapping("/{movieId}")
    public ResponseEntity<Void> removeFavorite(@PathVariable Long movieId) {
        favoriteService.removeFavorite(movieId);
        return ResponseEntity.noContent().build();
    }


    @GetMapping
    public ResponseEntity<List<MovieResponse>> getFavorites() {
        return ResponseEntity.ok(favoriteService.getMyFavorites());
    }

}
