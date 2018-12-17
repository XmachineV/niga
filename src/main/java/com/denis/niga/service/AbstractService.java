package com.denis.niga.service;

import com.denis.niga.repository.AbstractRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public abstract class AbstractService<T, ID> {

    private final AbstractRepository abstractRepository;

    public AbstractService(AbstractRepository abstractRepository) {
        this.abstractRepository = abstractRepository;
    }

    public Page<T> findAll(Pageable pageable) {
        return abstractRepository.findAll(pageable);
    }

    public Optional<T> findById(ID id) {
        return abstractRepository.findById(id);
    }

    public T save(T model) {
        return (T) abstractRepository.save(model);
    }

    public T update(T model) {
        return (T) abstractRepository.save(model);
    }

    public void delete(T model) {
        abstractRepository.delete(model);
    }

    public void deleteById(ID id) {
        abstractRepository.deleteById(id);
    }

}
