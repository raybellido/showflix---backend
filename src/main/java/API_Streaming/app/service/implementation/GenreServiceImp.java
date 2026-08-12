package API_Streaming.app.service.implementation;

import API_Streaming.app.dto.response.GenreResponse;
import API_Streaming.app.entity.Genre;
import API_Streaming.app.repository.GenreRepository;
import API_Streaming.app.service.interfaces.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GenreServiceImp implements GenreService {

    private final GenreRepository genreRepository;

    @Override
    public List<GenreResponse> findAll() {
        return genreRepository.findAll()
                .stream()
                .map(genre -> new GenreResponse
                        (genre.getId(), genre.getName())).toList();
    }

}
