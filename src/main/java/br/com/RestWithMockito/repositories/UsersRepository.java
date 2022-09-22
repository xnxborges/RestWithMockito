package br.com.RestWithMockito.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.RestWithMockito.domain.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users, Integer> {
}
