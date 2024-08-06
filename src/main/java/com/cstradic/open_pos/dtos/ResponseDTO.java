package com.cstradic.open_pos.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class ResponseDTO<T> {
    private String message;
    private boolean error;
    private T data;

    public ResponseDTO(String message) {
        this.message = message;
        this.error = true;
        this.data = null;
    }

    public ResponseDTO(T data, String message) {
        this.data = data;
        this.message = message;
        this.error = false;
    }
}
