package br.com.RestWithMockito.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.RestWithMockito.domain.Users;
import br.com.RestWithMockito.repositories.UsersRepository;

@Configuration
@Profile("local")
public class LocalConfig {

    @Autowired
    private UsersRepository repository;

    @Bean
    public void startDB(){
        Users u1 = new Users(null, "Natan", "email@email.com", "123");
        Users u2 = new Users(null, "Thais", "emailT@email.com", "1234");

        repository.saveAll(List.of(u1, u2));
    }
}
