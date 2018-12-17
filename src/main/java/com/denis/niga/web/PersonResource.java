package com.denis.niga.web;

import com.denis.niga.domain.Person;
import com.denis.niga.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/persons")
public class PersonResource extends AbstractResource<Person, Long> {


    @Autowired
    public PersonResource(PersonService service) {
        super(service);
    }

    @Override
    public Class<Person> getEntityClass() {
        return Person.class;
    }

    @PostMapping
    public ResponseEntity<Person> create(@RequestBody Person model) throws URISyntaxException {
        return super.create(model);
    }

    @PutMapping
    public ResponseEntity<Person> update(@RequestBody Person model) throws URISyntaxException {
        return super.update(model);
    }

    @GetMapping
    public ResponseEntity<List<Person>> getAll(Pageable pageable) {
        return super.findAll(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> findById(@PathVariable Long id) {
        return super.findById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return super.delete(id);
    }

}
