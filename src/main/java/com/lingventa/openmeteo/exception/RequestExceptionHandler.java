package com.lingventa.openmeteo.exception;

import com.lingventa.api.model.ApiError;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@ControllerAdvice
public class RequestExceptionHandler {

    public static final String PARAMETER = "PARAMETER";

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public final ResponseEntity<ApiError> handleException(MissingServletRequestParameterException ex) {
        return ResponseEntity.ok(new ApiError()
                .errors(List.of(ex.getMessage())));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public final ResponseEntity<ApiError> handleException(ConstraintViolationException ex) {


        return ResponseEntity.ok(new ApiError()
                .errors(ex.getConstraintViolations().stream()
                        .map(error -> String.format("Parametr %s (%s) %s", getParameterName(error), error.getInvalidValue(), error.getMessage()))
                        .collect(Collectors.toList())));
    }

    private String getParameterName(ConstraintViolation<?> error) {
        return StreamSupport.stream(error.getPropertyPath().spliterator(), false)
                .filter(node -> node.getKind().name().equals(PARAMETER))
                .map(Path.Node::getName)
                .findFirst().orElse(StringUtils.EMPTY);
    }
}
