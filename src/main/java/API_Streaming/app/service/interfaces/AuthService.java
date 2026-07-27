package API_Streaming.app.service.interfaces;

import API_Streaming.app.dto.request.LoginRequest;
import API_Streaming.app.dto.request.RegisterRequest;
import API_Streaming.app.dto.response.AuthResponse;

public interface AuthService {

    AuthResponse register(RegisterRequest request);
    AuthResponse login(LoginRequest request);
}
