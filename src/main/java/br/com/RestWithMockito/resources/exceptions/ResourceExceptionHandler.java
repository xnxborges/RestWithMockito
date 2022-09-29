package br.com.RestWithMockito.resources.exceptions;

import br.com.RestWithMockito.services.exceptions.DataIntegratyViolationException;
import br.com.RestWithMockito.services.exceptions.ObjectNotFoundExceptions;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(ObjectNotFoundExceptions.class)
    public ResponseEntity<StandardErrors>objectNotFound(
            ObjectNotFoundExceptions ex, HttpServletRequest request){

        StandardErrors errors = new StandardErrors(LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(), ex.getMessage(), request.getRequestURI());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errors);

    }

    @ExceptionHandler(DataIntegratyViolationException.class)
    public ResponseEntity<StandardErrors>dataIntegrityViolationException(
            DataIntegratyViolationException ex, HttpServletRequest request){

        StandardErrors errors = new StandardErrors(LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(), ex.getMessage(), request.getRequestURI());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);

    }

}
