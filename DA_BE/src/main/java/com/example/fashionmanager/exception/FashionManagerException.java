package com.example.fashionmanager.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Getter
@Setter
@NoArgsConstructor
public class FashionManagerException extends RuntimeException{
    private ErrorResponse errorResponse;
    public FashionManagerException(ErrorResponse errorResponse) {
        this.errorResponse = errorResponse;
    }

}
