package com.cstradic.open_pos.exceptions;

import com.cstradic.open_pos.dtos.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ShopAlreadyExistsException.class)
    public ResponseEntity<ResponseDTO<String>> handleShopAlreadyExistsException(ShopAlreadyExistsException e){
        return new ResponseEntity<>(new ResponseDTO<>(e.getMessage()), HttpStatus.CONFLICT);
    }
    @ExceptionHandler(ShopNotExistsException.class)
    public ResponseEntity<ResponseDTO<String>> handleShopNotExistsException(ShopNotExistsException e){
        return new ResponseEntity<>(new ResponseDTO<>(e.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ResponseDTO<String>> handleUserAlreadyExistsException(UserAlreadyExistsException e){
        return new ResponseEntity<>(new ResponseDTO<>(e.getMessage()), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ResponseDTO<String>> handleUserNotFoundException(UserNotFoundException e){
        return new ResponseEntity<>(new ResponseDTO<>(e.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserNotVerifiedException.class)
    public ResponseEntity<ResponseDTO<String>> handleUserNotVerifiedException(UserNotVerifiedException e){
        return new ResponseEntity<>(new ResponseDTO<>(e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ProductCategoryNotFoundException.class)
    public ResponseEntity<ResponseDTO<String>> handleProductCategoryNotFoundException(ProductCategoryNotFoundException e){
        return new ResponseEntity<>(new ResponseDTO<>(e.getMessage()), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(ProductCategoryAlreadyExistsException.class)
    public ResponseEntity<ResponseDTO<String>> handleProductCategoryAlreadyExistsException(ProductCategoryAlreadyExistsException e){
        return new ResponseEntity<>(new ResponseDTO<>(e.getMessage()), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ResponseDTO<String>> handleProductNotFoundException(ProductNotFoundException e){
        return new ResponseEntity<>(new ResponseDTO<>(e.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ProductAlreadyExistsException.class)
    public ResponseEntity<ResponseDTO<String>> handleProductAlreadyExistsException(ProductAlreadyExistsException e){
        return new ResponseEntity<>(new ResponseDTO<>(e.getMessage()), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(UserNotActiveException.class)
    public ResponseEntity<ResponseDTO<String>> handleUserNotActiveException(UserNotActiveException e){
        return new ResponseEntity<>(new ResponseDTO<>(e.getMessage()), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(InvoiceNotFoundException.class)
    public ResponseEntity<ResponseDTO<String>> handleInvoiceNotFoundException(InvoiceNotFoundException e){
        return new ResponseEntity<>(new ResponseDTO<>(e.getMessage()), HttpStatus.NOT_FOUND);
    }
}
