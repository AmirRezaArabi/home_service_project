package org.example.service;

import java.time.LocalDate;

public interface CustomerAccessService {

    boolean placeAnOrder (String underService,String customerUserName , LocalDate time ,Long suggestionPrice,String description,String address );
}
