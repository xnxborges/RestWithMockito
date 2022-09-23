package br.com.RestWithMockito.services.impl;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.RestWithMockito.domain.Users;
import br.com.RestWithMockito.domain.dto.UsersDTO;
import br.com.RestWithMockito.repositories.UsersRepository;
import br.com.RestWithMockito.services.UsersServices;
import br.com.RestWithMockito.services.exceptions.ObjectNotFoundExceptions;

@Service
public class UsersServicesImpl implements UsersServices {

    @Autowired
    private UsersRepository repository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public Users findById(Integer id) {
        Optional<Users> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundExceptions("Objeto não encontrado"));
    }

    public List<Users> findAll(){
        return repository.findAll();
    }

    @Override
    public Users create(UsersDTO obj) {
        return repository.save(mapper.map(obj, Users.class));
    }
}
