package API_Streaming.app.service.implementation;

import API_Streaming.app.dto.response.MovieResponse;
import API_Streaming.app.entity.Favorite;
import API_Streaming.app.entity.Movie;
import API_Streaming.app.entity.User;
import API_Streaming.app.repository.FavoriteRepository;
import API_Streaming.app.repository.MovieRepository;
import API_Streaming.app.repository.UserRepository;
import API_Streaming.app.service.interfaces.FavoriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class FavoriteServiceImpl implements FavoriteService {

    private final FavoriteRepository favoriteRepository;
    private final MovieRepository movieRepository;
    private final UserRepository userRepository;


    @Override
    public void addFavorite(Long movieId) {

        User user = getAuthenticatedUser();
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new RuntimeException("Movie not found"));
        boolean exists = favoriteRepository.existsByUserIdAndMovieId(user.getId(), movieId);

        if (exists) {throw new RuntimeException("Movie already in favorites");}

        Favorite favorite = Favorite.builder().user(user).movie(movie).build();

        favoriteRepository.save(favorite);

    }


    @Override
    public void removeFavorite(Long movieId) {

        User user = getAuthenticatedUser();
        Favorite favorite = favoriteRepository.findByUserIdAndMovieId(user.getId(), movieId).orElseThrow(() -> new RuntimeException("Favorite not found"));
        favoriteRepository.delete(favorite);

    }

    @Override
    public List<MovieResponse> getMyFavorites() {
        User user = getAuthenticatedUser();


        return favoriteRepository.findByUserId(user.getId()).stream().map(favorite -> {

            Movie movie = favorite.getMovie();

            return MovieResponse.builder()
                    .id(movie.getId())
                    .title(movie.getTitle())
                    .description(movie.getDescription())
                    .duration(movie.getDuration())
                    .releaseYear(movie.getReleaseYear())
                    .genre(movie.getGenre().getName())
                    .imageUrl(movie.getImageUrl())
                    .trailerUrl(movie.getTrailerUrl())
                    .rating(movie.getRating()).build();
        }).toList();
    }


    private User getAuthenticatedUser() {

        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        return userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
    }
}
