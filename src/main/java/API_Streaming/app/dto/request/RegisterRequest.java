package API_Streaming.app.dto.request;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RegisterRequest {

    @NotBlank(message = "El nombre es obligatorio")
    private String name;


    @Email(message = "El email debe ser válido")
    @NotBlank(message = "El email es obligatorio")
    private String email;


    @NotBlank(message = "La contraseña es obligatoria")
    private String password;

}
