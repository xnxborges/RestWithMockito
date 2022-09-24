package br.com.RestWithMockito.services.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.RestWithMockito.domain.Users;
import br.com.RestWithMockito.domain.dto.UsersDTO;
import br.com.RestWithMockito.repositories.UsersRepository;

@SpringBootTest
class UsersServicesImplTest {

    public static final Integer ID = 1;
    public static final String NAME = "Natan";
    public static final String EMAIL = "email@email.com";
    public static final String PASSWORD = "123";

    @InjectMocks
    private UsersServicesImpl servicesImpl;
    @Mock
    private UsersRepository repository;
    @Mock
    private ModelMapper mapper;

    private Users users;
    private UsersDTO usersDTO;

    private Optional<Users> optionalUsers;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startUsers();
    }

    @Test
    void whenFindByIdThenReturnAnUsersInstance() {
        when(repository.findById(anyInt())).thenReturn(optionalUsers);

        Users response = servicesImpl.findById(ID);

        assertNotNull(response);
        assertEquals(Users.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(NAME, response.getName());
        assertEquals(EMAIL, response.getEmail());
    }

    @Test
    void findAll() {
    }

    @Test
    void create() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    private void startUsers(){
        users = new Users(ID, NAME, EMAIL, PASSWORD);
        usersDTO = new UsersDTO(ID, NAME, EMAIL, PASSWORD);
        optionalUsers = Optional.of(new Users(ID, NAME, EMAIL, PASSWORD));
    }
}