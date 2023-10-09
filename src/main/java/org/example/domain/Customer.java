package org.example.domain;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.domain.base.User;

@Entity
@Data
@NoArgsConstructor
public class Customer extends User {
}
