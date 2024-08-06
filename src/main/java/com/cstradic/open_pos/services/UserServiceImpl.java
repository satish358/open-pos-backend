package com.cstradic.open_pos.services;

import com.cstradic.open_pos.configs.JwtService;
import com.cstradic.open_pos.dtos.ChangePasswordDTO;
import com.cstradic.open_pos.dtos.LoginRequestDTO;
import com.cstradic.open_pos.dtos.LoginResponseDTO;
import com.cstradic.open_pos.dtos.RegisterRequestDTO;
import com.cstradic.open_pos.exceptions.UserAlreadyExistsException;
import com.cstradic.open_pos.exceptions.UserNotActiveException;
import com.cstradic.open_pos.exceptions.UserNotFoundException;
import com.cstradic.open_pos.exceptions.UserNotVerifiedException;
import com.cstradic.open_pos.models.User;
import com.cstradic.open_pos.models.UserRole;
import com.cstradic.open_pos.repositories.UserRepository;
import com.cstradic.open_pos.repositories.UserRoleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtService jwtService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    private UserRoleRepository userRoleRepository;

    private static final Logger logger =  LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    @Override
    public User register(RegisterRequestDTO r) {
        Set<UserRole> userRoles = new HashSet<>();
        r.getRoles().forEach(userRole -> {
            userRoles.add(userRoleRepository.findById(userRole.getId()).orElseThrow(UserNotFoundException::new));
        });
        var user = new User(null, r.getEmail(), passwordEncoder.encode(r.getPassword()), r.getName(), r.getAge(),false, true, userRoles);
        if(userRepository.existsByEmailIgnoreCase(r.getEmail())) throw new UserAlreadyExistsException();
        userRepository.save(user);
        return user;
    }

    @Override
    public LoginResponseDTO login(LoginRequestDTO loginRequestDTO) {



        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequestDTO.getEmail(),
                        loginRequestDTO.getPassword()));

        if(authentication.isAuthenticated()){
            if(userRepository.existsByEmailIgnoreCaseAndVerifiedFalse(loginRequestDTO.getEmail()))
                throw new UserNotVerifiedException();
            if(userRepository.existsByActiveFalseAndEmailIgnoreCase(loginRequestDTO.getEmail()))
                throw new UserNotActiveException();

            User user = userRepository.findByEmail(loginRequestDTO.getEmail())
                    .orElseThrow(UserNotFoundException::new);

            return LoginResponseDTO.builder()
                    .token(jwtService.GenerateToken(loginRequestDTO.getEmail()))
                    .user(user)
                    .build();
        } else {
            throw new UsernameNotFoundException("invalid user request..!!");
        }

    }

    @Override
    public UserRole createUserRole(UserRole role) {
        if(userRoleRepository.existsByNameIgnoreCase(role.getName()))
            throw new RuntimeException("User role already exists.");
        userRoleRepository.save(role);
        return role;
    }

    @Override
    public List<UserRole> findAllRoles() {
        return userRoleRepository.findAll();
    }

    @Override
    public User verifyUser(User user) {
        var dbUser = userRepository.findByEmail(user.getEmail()).orElseThrow(UserNotFoundException::new);
        dbUser.setVerified(true);
        userRepository.save(dbUser);
        return dbUser;
    }

    @Override
    public User changePassword(ChangePasswordDTO changePasswordDTO, String token) {
        var extractedToken =  token.substring(7);
        var userEmail = jwtService.extractUsername(extractedToken);
        var user = userRepository.findByEmail(userEmail).orElseThrow(UserNotFoundException::new);
        user.setPassword(passwordEncoder.encode(changePasswordDTO.getNewPassword()));
        userRepository.save(user);
        return user;
    }

    @Override
    public Optional<User> getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth.isAuthenticated()) {
            var user = userRepository.findByEmail(auth.getName()).orElseThrow(UserNotFoundException::new);
            return Optional.of(user);
        }
        return Optional.empty();
    }
}
