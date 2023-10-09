package org.example.domain;

import jakarta.persistence.*;

@Entity
public class UnderService {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id ;

    private String name ;
    @ManyToOne
    private Service service;

    private String describtion ;

    private int baseprice ;
}
