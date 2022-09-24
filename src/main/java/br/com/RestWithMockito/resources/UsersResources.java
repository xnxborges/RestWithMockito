package br.com.RestWithMockito.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.RestWithMockito.domain.dto.UsersDTO;
import br.com.RestWithMockito.services.UsersServices;

@RestController
@RequestMapping("/user")
public class UsersResources {

    public static final String ID = "/{id}";
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private UsersServices services;

    @GetMapping(value = ID)
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

    @PostMapping
    public ResponseEntity<UsersDTO> create(@RequestBody UsersDTO obj){
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path(ID).buildAndExpand(services.create(obj)).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = ID)
    public ResponseEntity<UsersDTO> update(@PathVariable Integer id, @RequestBody UsersDTO obj){
        obj.setId(id);
        return ResponseEntity.ok().body(mapper.map(services.update(obj), UsersDTO.class));
    }

    @DeleteMapping(value = ID)
    public ResponseEntity<UsersDTO> delete(@PathVariable Integer id){
        services.delete(id);
        return ResponseEntity.noContent().build();

    }

}
