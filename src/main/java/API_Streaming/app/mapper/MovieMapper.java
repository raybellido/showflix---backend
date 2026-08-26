package API_Streaming.app.mapper;

import API_Streaming.app.dto.request.MovieRequest;
import API_Streaming.app.dto.request.MovieUpdateRequest;
import API_Streaming.app.dto.response.MovieResponse;
import API_Streaming.app.entity.Genre;
import API_Streaming.app.entity.Movie;
import org.springframework.stereotype.Component;

@Component
public class MovieMapper {

    public MovieResponse toResponse(Movie movie) {

        return MovieResponse.builder()

                .id(movie.getId())

                .title(movie.getTitle())

                .description(movie.getDescription())

                .duration(movie.getDuration())

                .releaseYear(movie.getReleaseYear())

                .imageUrl(movie.getImageUrl())

                .trailerUrl(movie.getTrailerUrl())

                .rating(movie.getRating())

                .genre(movie.getGenre() != null ? movie.getGenre().getName() : null)

                .genreId(movie.getGenre() != null ? movie.getGenre().getId() : null)

                .build();
    }

    public Movie toEntity(MovieRequest request, Genre genre) {

        return Movie.builder()

                .title(request.getTitle())

                .description(request.getDescription())

                .duration(request.getDuration())

                .releaseYear(request.getReleaseYear())

                .imageUrl(request.getImageUrl())

                .trailerUrl(request.getTrailerUrl())

                .videoUrl(request.getVideoUrl())

                .genre(genre)

                .build();

    }

    public void updateEntity(Movie movie, MovieUpdateRequest request, Genre genre) {

        movie.setTitle(request.title());

        movie.setDescription(request.description());

        movie.setGenre(genre);
    }


}
