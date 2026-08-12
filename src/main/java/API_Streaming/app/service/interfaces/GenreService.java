package API_Streaming.app.service.interfaces;

import API_Streaming.app.dto.response.GenreResponse;


import java.util.List;

public interface GenreService {
    List<GenreResponse> findAll();
}
