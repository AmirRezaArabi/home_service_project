package org.example.service;

import org.example.domain.CustomerRequest;
import org.example.domain.UnderService;

import java.util.List;
import java.util.Optional;

public interface UnderServiceService {

    UnderService save(UnderService underService);

    UnderService update(UnderService underService);

    Long delete(UnderService underService);

    Optional<UnderService> findById(Long id);
    Optional<UnderService> findByName(String name);

    List<UnderService> findAll();
    boolean isValid(UnderService underService);
}
