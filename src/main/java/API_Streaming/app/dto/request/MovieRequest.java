package API_Streaming.app.dto.request;

import lombok.Data;

@Data

public class MovieRequest {

    private String title;

    private String description;

    private Integer duration;

    private Integer releaseYear;

    private Long genreId;

    private String imageUrl;

    private String trailerUrl;

    private String videoUrl;


}
