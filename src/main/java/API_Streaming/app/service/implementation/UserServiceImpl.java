package API_Streaming.app.service.implementation;

import API_Streaming.app.dto.response.UserResponse;
import API_Streaming.app.entity.Role;
import API_Streaming.app.entity.Status;
import API_Streaming.app.entity.User;
import API_Streaming.app.exception.BusinessException;
import API_Streaming.app.exception.ResourceNotFoundException;
import API_Streaming.app.mapper.UserMapper;
import API_Streaming.app.repository.UserRepository;
import API_Streaming.app.service.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public Page<UserResponse> findAll(Status status, Pageable pageable) {
        Page<User> users = (status != null)
                ? userRepository.findByStatus(status, pageable)
                : userRepository.findAll(pageable);
        return users.map(userMapper::toResponse);
    }

    @Override
    public void softDelete(Long id) {
        User user = getActiveUser(id);
        validateDelete(user);
        user.setStatus(Status.INACTIVE);
        userRepository.save(user);
    }

    @Override
    public UserResponse restore(Long id) {
        User user = userRepository.findByIdAndStatus(id, Status.INACTIVE)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado o ya está activo."));
        user.setStatus(Status.ACTIVE);
        User restored = userRepository.save(user);
        return userMapper.toResponse(restored);
    }

    private void validateDelete(User user) {
        String currentUserEmail = SecurityContextHolder.getContext().getAuthentication().getName();

        if (user.getEmail().equals(currentUserEmail)) {
            throw new BusinessException("No puedes eliminarte a ti mismo.");
        }

        if (user.getRole() == Role.ROLE_ADMIN) {
            throw new BusinessException("No puedes eliminar a otro administrador.");
        }
    }

    private User getActiveUser(Long id) {
        return userRepository.findByIdAndStatus(id, Status.ACTIVE)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado."));
    }
}
