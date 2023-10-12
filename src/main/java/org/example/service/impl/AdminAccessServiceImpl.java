package org.example.service.impl;

import org.example.domain.Expert;
import org.example.domain.Service;
import org.example.domain.UnderService;
import org.example.domain.enums.EXPERT_STATUS;
import org.example.exception.InvalidServiceNameExctetion;
import org.example.exception.TheInputInformationIsNotValidException;
import org.example.service.*;

public class AdminAccessServiceImpl implements AdminAccessService {
    private ExpertService expertService;
    private CustomerService customerService;
    private ServiceService serviceService;
    private UnderServiceService underServiceService;

    public AdminAccessServiceImpl(ExpertService expertService,
                                  CustomerService customerService,
                                  ServiceService serviceService,
                                  UnderServiceService underServiceService) {
        this.expertService = expertService;
        this.customerService = customerService;
        this.serviceService = serviceService;
        this.underServiceService = underServiceService;
    }

    @Override
    public boolean addService(String serviceName) {
        Service service = Service.builder().name(serviceName).build();
        return serviceService.save(service) != null;

    }

    @Override
    public boolean addUnderService(String serviceName, String underServiceName, String describtion, Long basePrice) {
        if (serviceService.findByName(serviceName).isEmpty())
            throw new InvalidServiceNameExctetion("the input service name is not present");
        Service service = serviceService.findByName(serviceName).get();
        UnderService underService = UnderService.builder().service(service).name(underServiceName)
                .describtion(describtion).baseprice(basePrice).build();
        return underServiceService.save(underService) != null;
    }

    @Override
    public boolean updatePriceOrDescription(String underServiceName, String describtion, Long basePrice) {
        if (underServiceService.findByName(underServiceName).isEmpty())
            throw new InvalidServiceNameExctetion(" the input under service name is not valid");
        UnderService underService = underServiceService.findByName(underServiceName).get();
        underService.setBaseprice(basePrice);
        underService.setDescribtion(describtion);
        return underServiceService.update(underService) != null;
    }

    @Override
    public boolean addExpertToSystem(String expertUserName, String underServiceName) {
        if (expertService.findByUserName(expertUserName).isEmpty() || underServiceService.findByName(underServiceName).isEmpty())
            throw new TheInputInformationIsNotValidException("the input information is not correct ");
        Expert expert = expertService.findByUserName(expertUserName).get();
        UnderService underService = underServiceService.findByName(underServiceName).get();
        expert.getUnderServices().add(underService);
        underService.getExpert().add(expert);
        return (underServiceService.update(underService) != null && expertService.update(expert) != null);

    }

    @Override
    public boolean removeExpertToSystem(String expertUserName, String underServiceName) {
        if (expertService.findByUserName(expertUserName).isEmpty() || underServiceService.findByName(underServiceName).isEmpty())
            throw new TheInputInformationIsNotValidException("the input information is not correct ");
        Expert expert = expertService.findByUserName(expertUserName).get();
        UnderService underService = underServiceService.findByName(underServiceName).get();
        expert.getUnderServices().remove(underService);
        underService.getExpert().remove(expert);
        return (underServiceService.update(underService) != null && expertService.update(expert) != null);
    }

    @Override
    public boolean confirmExpert(String expertUserName) {
        if (expertService.findByUserName(expertUserName).isEmpty())
            throw new TheInputInformationIsNotValidException("the input information is not correct ");
        Expert expert = expertService.findByUserName(expertUserName).get();
        expert.setExpert_status(EXPERT_STATUS.CONFIRMED);
        return ( expertService.update(expert) != null);

    }
}
