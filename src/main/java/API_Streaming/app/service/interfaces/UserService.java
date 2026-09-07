package API_Streaming.app.service.interfaces;

import API_Streaming.app.dto.response.UserResponse;
import API_Streaming.app.entity.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

    Page<UserResponse> findAll(Status status, Pageable pageable);

    void softDelete(Long id);

    UserResponse restore(Long id);
}
