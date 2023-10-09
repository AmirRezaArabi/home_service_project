package org.example.domain;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Suggestion {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id ;

    @OneToOne
    private CustomerRequest customerRequest ;

    private Long suggestionPrice ;

    private LocalDate SuggestionDate;

    private LocalDate startWorkDay ;

    private int duration ;
}
