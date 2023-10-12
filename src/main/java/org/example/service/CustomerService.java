package org.example.service;

import lombok.Data;
import org.example.domain.Customer;
import org.example.domain.CustomerRequest;
import org.example.domain.Expert;

import java.util.List;
import java.util.Optional;


public interface CustomerService {

    Customer save (Customer customer);
    Customer update (Customer customer);
    Long delete (Customer customer);
    Optional<Customer> findById (Long id);
    Optional<Customer> findByUserName (String userName);

    Optional<Customer> chngePassword (String oldPassword,String userName,String newPassword );

    List<Customer> findAll();
    boolean isValid(Customer customer);


}
