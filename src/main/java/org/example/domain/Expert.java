package org.example.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.domain.base.User;
import org.example.domain.enums.EXPERT_STATUS;

import java.util.List;


@Entity
@Data
@NoArgsConstructor
public class Expert extends User {

    private EXPERT_STATUS expert_status;
    private int Score ;
    @OneToOne
    private Service service;
    @ManyToMany(cascade = CascadeType.ALL)
    private List<UnderService> underServices ;


}

