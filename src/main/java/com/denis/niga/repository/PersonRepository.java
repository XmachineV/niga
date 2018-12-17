package com.denis.niga.repository;

import com.denis.niga.domain.Person;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Location entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PersonRepository extends AbstractRepository<Person, Long> {

}
