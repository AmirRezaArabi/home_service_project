package org.example.service;

import org.example.domain.Customer;
import org.example.domain.CustomerRequest;
import org.example.domain.Expert;

import java.util.List;
import java.util.Optional;

public interface ExpertService {

    Expert save (Expert expert);
    Expert update (Expert expert);
    Long delete (Expert expert);
    Optional<Expert> findById (Long id);
    Optional<Expert> findByUserName (String userName);

    Optional<Expert> chngePassword (String oldPassword, String userName, String newPassword );
    List<Expert> findAll();
    boolean isValid(Expert expert);
}
