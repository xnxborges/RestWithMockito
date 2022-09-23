package br.com.RestWithMockito.services;

import java.util.List;

import br.com.RestWithMockito.domain.Users;

public interface UsersServices {

    Users findById(Integer id);

    List<Users> findAll();
}
