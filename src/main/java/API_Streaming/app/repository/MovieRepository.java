package API_Streaming.app.repository;

import API_Streaming.app.entity.Movie;
import API_Streaming.app.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MovieRepository extends JpaRepository<Movie,Long> {

    List<Movie> findByStatus (Status status);

    Optional<Movie> findByIdAndStatus(
            Long id,
            Status status
    );


    boolean existsByTitle(String title);
}
