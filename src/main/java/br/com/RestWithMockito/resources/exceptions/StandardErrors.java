package br.com.RestWithMockito.resources.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class StandardErrors {
    private LocalDateTime timestamp;
    private Integer status;
    private String error;
    private String path;

}
