package org.example.domain;

import jakarta.persistence.*;
import org.example.domain.Customer;
import org.example.domain.Expert;
import org.example.domain.Service;
import org.example.domain.UnderService;

@Entity
@Table(name = "order_tb")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id ;

    @OneToOne
    private Service service;
    @OneToOne
    private UnderService underService ;
    @OneToOne
    private Customer customer ;
    @OneToOne
    private Expert expert;

    private Long proposedPrice ;

    private String description ;



}
