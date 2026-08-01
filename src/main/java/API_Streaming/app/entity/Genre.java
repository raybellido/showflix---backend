package API_Streaming.app.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "genres")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Genre extends BaseEntity {

    @Column(nullable = false, unique = true)
    @JsonIgnore
    private String name;

    @OneToMany(mappedBy = "genre")
    private List<Movie> movies;
}
