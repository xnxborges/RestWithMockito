package br.com.RestWithMockito.resources.exceptions;

import br.com.RestWithMockito.services.exceptions.DataIntegratyViolationException;
import br.com.RestWithMockito.services.exceptions.ObjectNotFoundExceptions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ResourceExceptionHandlerTest {


    public static final String ObjectNotFound = "Objeto não encontrado";
    public static final String alreadyRegisteredInTheSystem = "E-mail já cadastrado no sistema";
    @InjectMocks
    private ResourceExceptionHandler exceptionHandler;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void whenObjectNotFoundExceptionThenReturnAnResponseEntity() {
        ResponseEntity<StandardErrors> response = exceptionHandler
                .objectNotFound(new ObjectNotFoundExceptions(ObjectNotFound),
                        new MockHttpServletRequest());

        response.getBody().getPath();

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(StandardErrors.class, response.getBody().getClass());
        assertEquals(ObjectNotFound, response.getBody().getError());
        assertEquals(404, response.getBody().getStatus());
        assertNotEquals("/user/2", response.getBody().getPath());
        assertNotEquals(LocalDateTime.now(), response.getBody().getPath());
    }

    @Test
    void dataIntegrityViolationException() {
        ResponseEntity<StandardErrors> response = exceptionHandler
                .dataIntegrityViolationException(new DataIntegratyViolationException(alreadyRegisteredInTheSystem),
                        new MockHttpServletRequest());

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(StandardErrors.class, response.getBody().getClass());
        assertEquals(alreadyRegisteredInTheSystem, response.getBody().getError());
        assertEquals(400, response.getBody().getStatus());
    }
}