package org.example.service.impl;

import org.example.domain.Customer;
import org.example.domain.CustomerRequest;
import org.example.domain.Service;
import org.example.domain.UnderService;
import org.example.domain.enums.REQUEST_STATUS;
import org.example.exception.TheSuggestedPriceIsLowerThanBasePriceExveption;
import org.example.service.CustomerAccessService;
import org.example.service.CustomerRequestService;
import org.example.service.CustomerService;
import org.example.service.UnderServiceService;

import java.time.LocalDate;

public class CustomerAccessServiceImpl implements CustomerAccessService {
    private CustomerService customerService;
    private UnderServiceService underServiceService;
    private CustomerRequestService customerRequestService;

    public CustomerAccessServiceImpl(CustomerService customerService, UnderServiceService underServiceService, CustomerRequestService customerRequestService) {
        this.customerService = customerService;
        this.underServiceService = underServiceService;
        this.customerRequestService = customerRequestService;
    }

    @Override
    public boolean placeAnOrder(String underServiceName, String customerUserName, LocalDate time, Long suggestionPrice, String description, String address) {

        Customer customer = customerService.findByUserName(customerUserName).get();
        UnderService underService = underServiceService.findByName(underServiceName).get();
        if (suggestionPrice<underService.getBaseprice())
            throw new TheSuggestedPriceIsLowerThanBasePriceExveption("The suggested price is lower than the base price");
        Service service = underService.getService();
        CustomerRequest customerRequest = CustomerRequest.builder().underService(underService)
                .customer(customer).startDay(time).suggestionPrice(suggestionPrice)
                .description(description).address(address).request_status(REQUEST_STATUS.WAITING_FOR_SUGGESTION)
                .service(service).build();
        return customerRequestService.save(customerRequest)!=null;
    }
}
