package com.denis.niga.service;

import com.denis.niga.repository.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public abstract class Service <T,ID> {

    private final Repository repository;

    public Service(Repository repository) {
        this.repository = repository;
    }

   public List<T> findAll(){
       return repository.findAll();
    }

    public Optional<T> findById(ID id){
     return repository.findById(id);
    }





}
