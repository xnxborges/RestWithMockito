package br.com.RestWithMockito.resources;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.RestWithMockito.domain.User;

@RestController
@RequestMapping("/user")
public class UserResources {

    @GetMapping(value = "/{id}")
    public ResponseEntity<User> findById(@PathVariable Integer id){
        return ResponseEntity.ok().body(new User(1, "Natan", "email@email.com", "123"));
    }


}
