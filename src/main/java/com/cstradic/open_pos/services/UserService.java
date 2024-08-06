package com.cstradic.open_pos.services;

import com.cstradic.open_pos.dtos.ChangePasswordDTO;
import com.cstradic.open_pos.dtos.LoginRequestDTO;
import com.cstradic.open_pos.dtos.LoginResponseDTO;
import com.cstradic.open_pos.dtos.RegisterRequestDTO;
import com.cstradic.open_pos.models.User;
import com.cstradic.open_pos.models.UserRole;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService {
    User register(RegisterRequestDTO registerRequestDTO);
    LoginResponseDTO login(LoginRequestDTO loginRequestDTO);
    UserRole createUserRole(UserRole role);
    List<UserRole> findAllRoles();
    User verifyUser(User user);
    User changePassword(ChangePasswordDTO changePasswordDTO, String token);
    Optional<User> getCurrentUser();

}
