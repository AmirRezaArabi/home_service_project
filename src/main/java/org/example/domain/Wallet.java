package org.example.domain;


import jakarta.persistence.*;

@Entity
public class Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id ;

    private Long Balance;

    @OneToOne(cascade = CascadeType.ALL)
    private Customer customer;


}
