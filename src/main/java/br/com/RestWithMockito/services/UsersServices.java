package br.com.RestWithMockito.services;

import br.com.RestWithMockito.domain.Users;

public interface UsersServices {

    Users findById(Integer id);
}
