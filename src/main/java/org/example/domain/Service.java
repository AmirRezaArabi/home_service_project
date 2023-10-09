package org.example.domain;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id ;

    private String Name ;
    @OneToMany(mappedBy = "service" )
    private List<UnderService> underServices ;
}
