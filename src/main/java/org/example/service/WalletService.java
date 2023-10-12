package org.example.service;

import org.example.domain.CustomerRequest;
import org.example.domain.Wallet;

import java.util.List;
import java.util.Optional;

public interface WalletService {
    Wallet save(Wallet wallet);

    Wallet update(Wallet wallet);

    Long delete(Wallet wallet);

    Optional<Wallet> findById(Long id);

    List<Wallet> findAll();
    boolean isValid(Wallet wallet);
}