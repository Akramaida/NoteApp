package com.gorbakov.note.exception;

import com.gorbakov.note.dto.ErrorDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Slf4j
@RequiredArgsConstructor
public class GlobalProjectException extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ProjectException.class)
    public ResponseEntity<ErrorDto> handler(ProjectException exception){
        ErrorDto errorDto = ErrorDto.builder()
                .message(exception.getMessage())
                .build();
        log.error(exception.getMessage());
        return new ResponseEntity<>(errorDto, HttpStatus.NOT_FOUND);
    }
}
