package org.example.repository;

import jakarta.persistence.EntityManager;
import org.example.domain.Customer;
import org.example.domain.UnderService;
import org.example.domain.Wallet;

import java.util.List;
import java.util.Optional;

public interface WalletRepository {

    Wallet save(Wallet wallet);

    Wallet update(Wallet wallet);

    Long delete(Wallet wallet);

    Optional<Wallet> findById(Long id);

    List<Wallet> findAll();
}