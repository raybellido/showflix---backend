package API_Streaming.app.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data

public class MovieRequest {

    @NotBlank(message = "El título es obligatorio")
    private String title;

    @NotBlank(message = "La descripción es obligatoria")
    private String description;

    @NotNull(message = "La duración es obligatoria")
    @Positive(message = "La duración debe ser mayor que 0")
    private Integer duration;

    @NotNull(message = "El año de estreno es obligatorio")
    @Min(value = 1900, message = "El año no es válido")
    @Max(value = 2100, message = "El año no es válido")
    private Integer releaseYear;

    @NotNull(message = "Debe seleccionar un género")
    private Long genreId;

    @NotBlank(message = "La imagen es obligatoria")
    private String imageUrl;

    @NotBlank(message = "El trailer es obligatorio")
    private String trailerUrl;

    @NotBlank(message = "La url del video es obligatoria")
    private String videoUrl;

}
