package API_Streaming.app.dto.response;
import API_Streaming.app.entity.Role;
import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
public class AuthResponse {
    private String token;

    @Builder.Default
    private String type = "Bearer";

    private String name;

    private String email;

    private Role role;
}
