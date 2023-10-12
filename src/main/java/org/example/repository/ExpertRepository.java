package org.example.repository;

import jakarta.persistence.EntityManager;
import org.example.domain.Customer;
import org.example.domain.Expert;

import java.util.List;
import java.util.Optional;

public interface ExpertRepository {

    Expert save (Expert expert);
    Expert update (Expert expert);
    Long delete (Expert expert);
    Optional<Expert> findById (Long id);
    List<Expert> findAll();
    Optional<Expert> findByUserName (String userName);

}
