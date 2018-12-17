package com.denis.niga.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;
import java.util.Optional;

@NoRepositoryBean
public interface AbstractRepository<T, ID> extends JpaRepository<T, ID> {

    public Optional<T> findById(ID id);


}
