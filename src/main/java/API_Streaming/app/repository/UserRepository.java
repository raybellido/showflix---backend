package API_Streaming.app.repository;

import API_Streaming.app.entity.Status;
import API_Streaming.app.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);

    Optional<User> findByIdAndStatus(Long id, Status status);

    Page<User> findByStatus(Status status, Pageable pageable);
}
