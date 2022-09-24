package br.com.RestWithMockito.services.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.List;
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
import br.com.RestWithMockito.services.exceptions.DataIntegratyViolationException;
import br.com.RestWithMockito.services.exceptions.ObjectNotFoundExceptions;

@SpringBootTest
class UsersServicesImplTest {

    public static final Integer ID = 1;
    public static final String NAME = "Natan";
    public static final String EMAIL = "email@email.com";
    public static final String PASSWORD = "123";
    public static final String NOTFOUNDOBJECT = "Objeto não encontrado";

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
    void whenFindByIdThenReturnAnObjectNotFoundException(){
        when(repository.findById(anyInt())).thenThrow(new ObjectNotFoundExceptions(NOTFOUNDOBJECT));

        try {
            servicesImpl.findById(ID);
        }catch (Exception ex){
            assertEquals(ObjectNotFoundExceptions.class, ex.getClass());
            assertEquals(NOTFOUNDOBJECT, ex.getMessage());
        }
    }

    @Test
    void whenFindAllThenReturnAnListOfUsers() {
        when(repository.findAll()).thenReturn(List.of(users));

        List<Users> response = servicesImpl.findAll();

        assertNotNull(response);
        assertEquals(1, response.size());
        assertEquals(Users.class, response.get(0).getClass());

        assertEquals(ID, response.get(0).getId());
        assertEquals(NAME, response.get(0).getName());
        assertEquals(EMAIL, response.get(0).getEmail());
        assertEquals(PASSWORD, response.get(0).getPassword());
    }

    @Test
    void whenCreateThenReturnSccess() {
        when(repository.save(any())).thenReturn(users);

        Users response = servicesImpl.create(usersDTO);

        assertNotNull(response);
        assertEquals(Users.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(NAME, response.getName());
        assertEquals(EMAIL, response.getEmail());
        assertEquals(PASSWORD, response.getPassword());

    }

    @Test
    void whenCreateThenReturnAnDataIntegrityViolationException() {
        when(repository.findByEmail(anyString())).thenReturn(optionalUsers);

        try {
            optionalUsers.get().setId(2);
            servicesImpl.create(usersDTO);
        }catch (Exception ex){
            assertEquals(DataIntegratyViolationException.class, ex.getClass());
            assertEquals("Email já cadastrado no sistema", ex.getMessage());
        }

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