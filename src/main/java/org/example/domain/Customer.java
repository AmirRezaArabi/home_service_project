package org.example.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.domain.base.User;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Customer extends User {

    @OneToOne
    private Wallet wallet ;

    @OneToMany
    private List<CustomerRequest> customerRequests ;
    @OneToMany
    private List<Order> orders;
}
