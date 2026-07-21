package API_Streaming.app.mapper;

import API_Streaming.app.dto.request.MovieRequest;
import API_Streaming.app.dto.response.MovieResponse;
import API_Streaming.app.entity.Genre;
import API_Streaming.app.entity.Movie;
import org.springframework.stereotype.Component;

@Component
public class MovieMapper {

    public  MovieResponse toResponse(Movie movie){

        return MovieResponse.builder()

                .id(movie.getId())

                .title(movie.getTitle())

                .description(movie.getDescription())

                .duration(movie.getDuration())

                .releaseYear(movie.getReleaseYear())

                .imageUrl(movie.getImageUrl())

                .trailerUrl(movie.getTrailerUrl())

                .rating(movie.getRating())

                .genre(
                        movie.getGenre() != null
                                ? movie.getGenre().getName()
                                : null
                )

                .build();
    }

    public Movie toEntity(MovieRequest request, Genre genre){

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

    public void updateEntity(Movie movie,
                             MovieRequest request,
                             Genre genre) {


        movie.setTitle(request.getTitle());

        movie.setDescription(request.getDescription());

        movie.setDuration(request.getDuration());

        movie.setReleaseYear(request.getReleaseYear());

        movie.setImageUrl(request.getImageUrl());

        movie.setTrailerUrl(request.getTrailerUrl());

        movie.setVideoUrl(request.getVideoUrl());

        movie.setGenre(genre);

    }



}
