package API_Streaming.app.service.implementation;

import API_Streaming.app.dto.request.MovieRequest;
import API_Streaming.app.dto.response.MovieResponse;
import API_Streaming.app.entity.Genre;
import API_Streaming.app.entity.Movie;
import API_Streaming.app.entity.Status;
import API_Streaming.app.exception.BusinessException;
import API_Streaming.app.exception.ResourceNotFoundException;
import API_Streaming.app.mapper.MovieMapper;
import API_Streaming.app.repository.GenreRepository;
import API_Streaming.app.repository.MovieRepository;
import API_Streaming.app.service.interfaces.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class MovieServiceImp implements MovieService {

    private final MovieRepository movieRepository;
    private final GenreRepository genreRepository;
    private final MovieMapper movieMapper;


    @Override
    public Page<MovieResponse> findAll(Pageable pageable) {
        return movieRepository.findByStatus(Status.ACTIVE, pageable).map(movieMapper::toResponse);
    }

    @Override
    public MovieResponse findById(Long id) {
        return movieMapper.toResponse(getMovie(id));
    }

    @Override
    public MovieResponse create(MovieRequest request) {

        validateMovie(request);
        Genre genre = getGenre(request.getGenreId());
        Movie movie = movieMapper.toEntity(request, genre);
        Movie savedMovie = movieRepository.save(movie);
        return movieMapper.toResponse(savedMovie);
    }

    @Override
    public MovieResponse update(Long id, MovieRequest request) {
        Movie movie = getMovie(id);
        Genre genre = getGenre(request.getGenreId());
        movieMapper.updateEntity(movie, request, genre);
        Movie updatedMovie = movieRepository.save(movie);
        return movieMapper.toResponse(updatedMovie);
    }

    @Override
    public void delete(Long id) {
        Movie movie = getMovie(id);
        movie.setStatus(Status.INACTIVE);
        movieRepository.save(movie);
    }


    private Genre getGenre(Long genreId) {
        return genreRepository.findById(genreId).orElseThrow(() -> new ResourceNotFoundException("Género no encontrado."));
    }

    private Movie getMovie(Long id) {
        return movieRepository.findByIdAndStatus(id, Status.ACTIVE).orElseThrow(() -> new ResourceNotFoundException("Pelicula no encontrada"));
    }

    private void validateMovie(MovieRequest request) {
        if (movieRepository.existsByTitle(request.getTitle())) {
            throw new BusinessException("Ya existe una película con ese título.");
        }
    }

    @Override
    public Page<MovieResponse> searchByTitle(String title, Pageable pageable) {
        return movieRepository.findByTitleContainingIgnoreCase(title, pageable).map(movieMapper::toResponse);


    }

    @Override
    public Page<MovieResponse> findByGenre(Long genreId, Pageable pageable) {
        return movieRepository.findByGenreIdAndStatus(genreId, Status.ACTIVE, pageable).map(movieMapper::toResponse);
    }

    @Override
    public Page<MovieResponse> searchByTitleAndGenre(String title, Long genreId, Pageable pageable) {
        return movieRepository.findByTitleContainingIgnoreCaseAndGenreIdAndStatus(title, genreId, Status.ACTIVE, pageable).map(movieMapper::toResponse);
    }


}
