package org.example.service;

import lombok.Data;
import org.example.domain.CustomerRequest;

import java.util.List;
import java.util.Optional;
public interface CustomerRequestService {

    CustomerRequest save (CustomerRequest customerRequest);
    CustomerRequest update (CustomerRequest customerRequest);
    Long delete (CustomerRequest customerRequest);
    Optional<CustomerRequest> findById (Long id);
    List<CustomerRequest> findAll();
    boolean isValid(CustomerRequest customerRequest);
}
