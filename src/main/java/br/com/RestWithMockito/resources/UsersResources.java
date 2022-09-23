package br.com.RestWithMockito.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.RestWithMockito.domain.dto.UsersDTO;
import br.com.RestWithMockito.services.UsersServices;

@RestController
@RequestMapping("/user")
public class UsersResources {

    @Autowired
    private ModelMapper mapper;
    @Autowired
    private UsersServices services;

    @GetMapping(value = "/{id}")
    public ResponseEntity<UsersDTO> findById(@PathVariable Integer id){
        return ResponseEntity.ok().body(mapper.map(services.findById(id), UsersDTO.class));
    }

    @GetMapping
    public ResponseEntity<List<UsersDTO>> findAll(){
        return ResponseEntity.ok().body(
                services.findAll()
                        .stream().map(x -> mapper.map(x, UsersDTO.class))
                        .collect(Collectors.toList()));
    }


}
