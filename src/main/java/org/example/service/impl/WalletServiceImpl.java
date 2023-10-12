package org.example.service.impl;

import jakarta.validation.ConstraintViolation;
import org.example.config.ApplicationContext;
import org.example.domain.Customer;
import org.example.domain.CustomerRequest;
import org.example.domain.UnderService;
import org.example.domain.Wallet;
import org.example.exception.TheInputInformationIsNotValidException;
import org.example.repository.WalletRepository;
import org.example.service.WalletService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class WalletServiceImpl implements WalletService {

    private WalletRepository walletRepository;
    private Wallet returnObject;
    private Long returnId;

    public WalletServiceImpl(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
        returnObject = null;
        returnId = -1L;
    }

    @Override
    public Wallet save(Wallet wallet) {
        try {
            if (isValid(wallet)) {
                ApplicationContext.getEntityManager().getTransaction().begin();
                returnObject = walletRepository.save(wallet);
                ApplicationContext.getEntityManager().getTransaction().commit();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            ApplicationContext.getEntityManager().getTransaction().rollback();
        }
        return returnObject;
    }

    @Override
    public Wallet update(Wallet wallet) {
        try {
            if (isValid(wallet)) {
                ApplicationContext.getEntityManager().getTransaction().begin();
                returnObject = walletRepository.save(wallet);
                ApplicationContext.getEntityManager().getTransaction().commit();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            ApplicationContext.getEntityManager().getTransaction().rollback();
        }
        return returnObject;
    }

    @Override
    public Long delete(Wallet wallet) {
        try {
            ApplicationContext.getEntityManager().getTransaction().begin();
            returnId = walletRepository.delete(wallet);
            ApplicationContext.getEntityManager().getTransaction().commit();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            ApplicationContext.getEntityManager().getTransaction().rollback();
        }
        return returnId;
    }

    @Override
    public Optional<Wallet> findById(Long id) {
        Optional<Wallet> byId = Optional.empty();
        try {
            ApplicationContext.getEntityManager().getTransaction().begin();
            byId = walletRepository.findById(id);
            ApplicationContext.getEntityManager().getTransaction().commit();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            ApplicationContext.getEntityManager().getTransaction().rollback();
        }
        return byId;
    }

    @Override
    public List<Wallet> findAll() {
        List<Wallet> all = new ArrayList<>();
        try {
            ApplicationContext.getEntityManager().getTransaction().begin();
            all = walletRepository.findAll();
            ApplicationContext.getEntityManager().getTransaction().commit();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            ApplicationContext.getEntityManager().getTransaction().rollback();
        }
        return all;
    }

    @Override
    public boolean isValid(Wallet wallet) {
        Set<ConstraintViolation<Wallet>> violations = ApplicationContext.getEntityValidator().validate(wallet);
        if (!violations.isEmpty()) {
            for (ConstraintViolation<Wallet> p : violations) {
                System.out.println(p.getMessage());
                throw new TheInputInformationIsNotValidException("the input information is not correct");
            }
            return false;
        }
        return true;
    }
}
