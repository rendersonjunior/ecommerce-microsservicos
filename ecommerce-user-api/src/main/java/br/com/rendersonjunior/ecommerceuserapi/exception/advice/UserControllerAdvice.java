package br.com.rendersonjunior.ecommerceuserapi.exception.advice;

import com.rendersonjunior.dto.ErrorDTO;
import com.rendersonjunior.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;

@ControllerAdvice(basePackages = "br.com.rendersonjunior.ecommerceuserapi.controller")
public class UserControllerAdvice {

    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(UserNotFoundException.class)
    public ErrorDTO handleUserNotFound(final UserNotFoundException userNotFoundException) {
        return ErrorDTO.builder()
                .status(HttpStatus.NOT_FOUND.value())
                .message("Usuário não encontrado")
                .timestamp(LocalDateTime.now())
                .build();
    }
}
