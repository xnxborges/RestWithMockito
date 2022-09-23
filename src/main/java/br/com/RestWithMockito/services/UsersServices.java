package br.com.RestWithMockito.services;

import java.util.List;

import br.com.RestWithMockito.domain.Users;
import br.com.RestWithMockito.domain.dto.UsersDTO;

public interface UsersServices {

    Users findById(Integer id);

    List<Users> findAll();

    Users create(UsersDTO obj);

    Users update(UsersDTO obj);
}
