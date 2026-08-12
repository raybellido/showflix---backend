package API_Streaming.app.repository;

import API_Streaming.app.entity.Movie;
import API_Streaming.app.entity.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MovieRepository extends JpaRepository<Movie, Long> {

    Page<Movie> findByStatus(Status status, Pageable pageable);

    Optional<Movie> findByIdAndStatus(Long id, Status status);

    Page<Movie> findByTitleContainingIgnoreCase(String title, Pageable pageable);

    boolean existsByTitle(String title);

    Page<Movie> findByGenreIdAndStatus(Long genreId, Status status, Pageable pageable);

    Page<Movie> findByTitleContainingIgnoreCaseAndGenreIdAndStatus(String title, Long genreId, Status status, Pageable pageable);

}
