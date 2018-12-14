package com.denis.niga.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;

@NoRepositoryBean
public interface Repository<T, ID> extends JpaRepository<T, ID> {

    public Optional<T> findById(ID id);

}
