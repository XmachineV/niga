package com.denis.niga.service;

import com.denis.niga.repository.AbstractRepository;
import org.springframework.stereotype.Service;

@Service
public class PersonService extends AbstractService {

    public PersonService(AbstractRepository abstractRepository) {
        super(abstractRepository);
    }
}
