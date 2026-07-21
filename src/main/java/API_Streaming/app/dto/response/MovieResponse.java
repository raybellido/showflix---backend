package API_Streaming.app.dto.response;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder

public class MovieResponse {

    private Long id;

    private String title;

    private String description;

    private Integer duration;

    private Integer releaseYear;

    private String genre;

    private String imageUrl;

    private String trailerUrl;

    private BigDecimal rating;

}
