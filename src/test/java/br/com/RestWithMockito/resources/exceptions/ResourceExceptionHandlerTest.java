package br.com.RestWithMockito.resources.exceptions;

import br.com.RestWithMockito.services.exceptions.ObjectNotFoundExceptions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class ResourceExceptionHandlerTest {


    public static final String ObjectNotFound = "Objeto não encontrado";
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

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(StandardErrors.class, response.getBody().getClass());
        assertEquals(ObjectNotFound, response.getBody().getError());
        assertEquals(404, response.getBody().getStatus());
    }

    @Test
    void dataIntegratyViolationException() {
    }
}