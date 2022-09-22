package br.com.RestWithMockito.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.RestWithMockito.domain.Users;
import br.com.RestWithMockito.repositories.UsersRepository;
import br.com.RestWithMockito.services.UsersServices;

@Service
public class UsersServicesImpl implements UsersServices {

    @Autowired
    private UsersRepository repository;

    @Override
    public Users findById(Integer id) {
        Optional<Users> obj = repository.findById(id);
        return obj.orElse(null);
    }
}
