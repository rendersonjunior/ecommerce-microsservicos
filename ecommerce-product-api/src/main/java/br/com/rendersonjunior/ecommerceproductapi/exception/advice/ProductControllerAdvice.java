package br.com.rendersonjunior.ecommerceproductapi.exception.advice;

import com.rendersonjunior.dto.ErrorDTO;
import com.rendersonjunior.exception.CategoryNotFoundException;
import com.rendersonjunior.exception.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;

@ControllerAdvice(basePackages = "br.com.rendersonjunior.ecommerceproductapi.controller")
public class ProductControllerAdvice {

    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ProductNotFoundException.class)
    public ErrorDTO handleProductNotFound(final ProductNotFoundException productNotFoundException) {
        return ErrorDTO.builder()
                .status(HttpStatus.NOT_FOUND.value())
                .message("Produto não encontrado.")
                .timestamp(LocalDateTime.now())
                .build();
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(CategoryNotFoundException.class)
    public ErrorDTO handleCategoryNotFound(final CategoryNotFoundException categoryNotFoundException) {
        return ErrorDTO.builder()
                .status(HttpStatus.NOT_FOUND.value())
                .message("Categoria não encontrada.")
                .timestamp(LocalDateTime.now())
                .build();
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorDTO processValidationError(final MethodArgumentNotValidException methodArgumentNotValidException) {
        final var result = methodArgumentNotValidException.getBindingResult();
        final var listFieldErrors = result.getFieldErrors();
        final var messageSb = new StringBuilder("Valor inválido para os campo(s): ");

        for (final var fieldError : listFieldErrors) {
            messageSb.append(" ".concat(fieldError.getField()));
        }

        return ErrorDTO.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .message(messageSb.toString())
                .timestamp(LocalDateTime.now())
                .build();
    }

}
