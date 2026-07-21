package API_Streaming.app.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "movies")
@Data // Genera getters, setters, toString, equals y hashCode de forma automática
@NoArgsConstructor // Genera el constructor vacío obligatorio para JPA
@AllArgsConstructor // Genera un constructor con todos los atributos
@Builder

public class Movie extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(length = 3000)
    private String description;

    private Integer duration;

    private Integer releaseYear;

    private LocalDate releaseDate;

    private String imageUrl;

    private String trailerUrl;

    private String videoUrl;

    private BigDecimal rating;

    @ManyToOne
    @JoinColumn(name = "genre_id")
    private Genre genre;
}

