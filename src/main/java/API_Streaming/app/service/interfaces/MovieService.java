package API_Streaming.app.service.interfaces;

import API_Streaming.app.dto.request.MovieRequest;
import API_Streaming.app.dto.response.MovieResponse;

import java.util.List;

public interface MovieService {

    List<MovieResponse> findAll();

    MovieResponse findById(Long id);

    MovieResponse create(MovieRequest request);

    MovieResponse update(Long id, MovieRequest request);

    void delete(Long id);
}
