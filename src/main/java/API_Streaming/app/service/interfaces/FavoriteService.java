package API_Streaming.app.service.interfaces;

import API_Streaming.app.dto.response.MovieResponse;

import java.util.List;

public interface FavoriteService {

    void addFavorite(Long movieId);


    void removeFavorite(Long movieId);


    List<MovieResponse> getMyFavorites();

}
