package org.example.repository.impl;

import jakarta.persistence.EntityManager;
import lombok.Data;
import org.example.domain.Wallet;
import org.example.repository.WalletRepository;

import java.util.List;
import java.util.Optional;
@Data
public class WalletRepositoryImpl implements WalletRepository {
    private EntityManager entityManager;

    public WalletRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Wallet save(Wallet wallet) {
        return entityManager.merge(wallet);
    }

    @Override
    public Wallet update(Wallet wallet) {
        return entityManager.merge(wallet);
    }

    @Override
    public Long delete(Wallet wallet) {
                entityManager.remove(wallet);
                return wallet.getId();
    }

    @Override
    public Optional<Wallet> findById(Long id) {
        return Optional.ofNullable(entityManager.find(Wallet.class, id));
    }

    @Override
    public List<Wallet> findAll() {
        return entityManager.createQuery("from Wallet ").getResultList();
    }
}
