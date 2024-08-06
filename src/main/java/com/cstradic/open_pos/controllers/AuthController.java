package com.cstradic.open_pos.controllers;

import com.cstradic.open_pos.dtos.*;
import com.cstradic.open_pos.models.ShopUser;
import com.cstradic.open_pos.models.User;
import com.cstradic.open_pos.models.UserRole;
import com.cstradic.open_pos.services.ShopUserService;
import com.cstradic.open_pos.services.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Authentication Controller")
@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthController {
    @Autowired
    private UserService userService;
    @Autowired
    ShopUserService  shopUserService;

//    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/register")
    public ResponseEntity<ResponseDTO<User>> register(@RequestBody RegisterRequestDTO registerRequestDTO) {
        var userRes =  userService.register(registerRequestDTO);
        return new ResponseEntity<>(new ResponseDTO<>(userRes, "Register Successful"), HttpStatus.CREATED);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/createShopUser")
    public ResponseEntity<ResponseDTO<ShopUser>> registerWorker(@RequestBody CreateShopUserDTO createShopUserDTO) {
        var userRes =  shopUserService.create(createShopUserDTO);
        return new ResponseEntity<>(new ResponseDTO<>(userRes, "Register Successful"), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseDTO<LoginResponseDTO>> login(@RequestBody @Valid LoginRequestDTO loginRequestDTO) {
        var loginResponse = userService.login(loginRequestDTO);
        return ResponseEntity.ok(new ResponseDTO<>(loginResponse,"Login Successful"));
    }

//    @PreAuthorize("hasAuthority('ADMIN')")
//    @PostMapping("/createRole")
//    public ResponseEntity<ResponseDTO<UserRole>> createRole(@RequestBody @Valid UserRole userRole) {
//        var roleDetails = userService.createUserRole(userRole);
//        return ResponseEntity.ok(new ResponseDTO<>(roleDetails,"Role creation Successful"));
//    }

//    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/findAllRoles")
    public ResponseEntity<ResponseDTO<List<UserRole>>> findAllRoles() {
        return ResponseEntity.ok(new ResponseDTO<>(userService.findAllRoles(),"All User role"));
    }

//    @PreAuthorize("hasAuthority('ADMIN')")
    @PatchMapping("/verifyUser")
    public ResponseEntity<ResponseDTO<User>> verifyUser(@RequestBody @Valid User user) {
        var userDetails = userService.verifyUser(user);
        return ResponseEntity.ok(new ResponseDTO<>(userDetails,"User verification Successful"));
    }

    @PatchMapping("/changePassword")
    public ResponseEntity<ResponseDTO<User>> changePassword(@RequestBody @Valid ChangePasswordDTO changePasswordDTO,  @RequestHeader(name="Authorization", required = false) String token) {
        var userDetails = userService.changePassword(changePasswordDTO, token);
        return ResponseEntity.ok(new ResponseDTO<>(userDetails,"User password updated."));
    }
    //

}
