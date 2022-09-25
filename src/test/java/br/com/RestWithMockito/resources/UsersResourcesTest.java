package br.com.RestWithMockito.resources;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.RestWithMockito.domain.Users;
import br.com.RestWithMockito.domain.dto.UsersDTO;
import br.com.RestWithMockito.services.impl.UsersServicesImpl;

@SpringBootTest
class UsersResourcesTest {

    public static final Integer ID = 1;
    public static final String NAME = "Natan";
    public static final String EMAIL = "email@email.com";
    public static final String PASSWORD = "123";
    public static final String NOTFOUNDOBJECT = "Objeto não encontrado";

    private Users users;
    private UsersDTO usersDTO;

    @InjectMocks
    private UsersResources resources;

    @Mock
    private UsersServicesImpl servicesImpl;

    @Mock
    private ModelMapper mapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startUsers();

    }

    @Test
    void findById() {
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
    }
}