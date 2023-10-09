package org.example.domain;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class UnderService {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id ;

    private String name ;
    @ManyToOne
    private Service service;

    @ManyToMany(mappedBy = "underServices")
    private List<Expert> expert;

    private String describtion ;

    private int baseprice ;
}
