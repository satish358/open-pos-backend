package com.cstradic.open_pos.controllers;

import com.cstradic.open_pos.dtos.PingDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Basic Home Controller")
@RestController
@RequestMapping("/")
@CrossOrigin("*")
public class HomeController {
    @GetMapping("welcome")
    public String welcome(){

        return "Hello World ";
    }

    @GetMapping("ping")
    public ResponseEntity<PingDTO> ping() {
        return ResponseEntity.ok(PingDTO.builder().connectable(true).health("GOOD").build());
    }
}
