package API_Streaming.app.dto.response;

import API_Streaming.app.entity.Role;
import API_Streaming.app.entity.Status;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserResponse {

    private Long id;

    private String name;

    private String email;

    private Role role;

    private Status status;
}
