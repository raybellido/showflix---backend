package API_Streaming.app.service.interfaces;

import API_Streaming.app.dto.request.MovieRequest;
import API_Streaming.app.dto.response.MovieResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MovieService {

    Page<MovieResponse> findAll(Pageable pageable);

    MovieResponse findById(Long id);

    MovieResponse create(MovieRequest request);

    MovieResponse update(Long id, MovieRequest request);

    void delete(Long id);

    Page<MovieResponse> searchByTitle(String title, Pageable pageable);

    Page<MovieResponse> findByGenre(Long genreId, Pageable pageable);

    Page<MovieResponse> searchByTitleAndGenre(String title, Long genreId, Pageable pageable);
}
