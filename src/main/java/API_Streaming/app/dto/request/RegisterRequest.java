package API_Streaming.app.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class RegisterRequest {

    @NotBlank
    private String name;


    @Email
    @NotBlank
    private String email;


    @NotBlank
    private String password;

}
