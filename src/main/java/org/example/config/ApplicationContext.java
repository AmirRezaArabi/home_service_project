package org.example.config;


import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;


import jakarta.validation.Validator;
import org.example.repository.*;
import org.example.repository.impl.*;
import org.example.service.*;
import org.example.service.impl.*;
import org.example.validation.EntityValidator;


public class ApplicationContext {

    private static Validator entityValidator;
    private static EntityManager entityManager;

    private static CustomerRepository customerRepository;
    private static CustomerService customerService;
    private static CustomerRequestRepository customerRequestRepository;
    private static CustomerRequestService customerRequestService;
    private static ExpertRepository expertRepository;
    private static ExpertService expertService;
    private static OrderRepository orderRepository;
    private static OrderService orderService;
    private static ServiceRepository serviceRepository;
    private static ServiceService serviceService;
    private static SuggestionRepository suggestionRepository;
    private static SuggestionService suggestionService;
    private static UnderServiceRepository underServiceRepository;
    private static UnderServiceService underServiceService;
    private static WalletRepository walletRepository;
    private static WalletService walletService;

    private static AdminAccessService adminAccessService;

    private static CustomerAccessService customerAccessService;


    static {
        entityManager = Persistence.createEntityManagerFactory("test").createEntityManager();

        entityValidator = EntityValidator.validator;

        customerRepository = new CustomerRepositoryImpl(entityManager);
        customerService = new CustomerServiceImpl(customerRepository);
        customerRequestRepository = new CustomerRequestRepositoryImpl(entityManager);
        customerRequestService = new CustomerRequestServiceImpl(customerRequestRepository);
        expertRepository = new ExpertRepositoryImpl(entityManager);
        expertService = new ExpertServiceImpl(expertRepository);
        orderRepository = new OrderRepositoryImpl(entityManager);
        orderService = new OrderServiceImpl(orderRepository);
        serviceRepository = new ServiceRepositoryImpl(entityManager);
        serviceService = new ServiceServiceImpl(serviceRepository);
        suggestionRepository = new SuggestionRepositoryImpl(entityManager);
        suggestionService = new SuggestionServiceImpl(suggestionRepository);
        underServiceRepository = new UnderServiceRepositoryImpl(entityManager);
        underServiceService = new UnderServiceServiceImpl(underServiceRepository);
        walletRepository = new WalletRepositoryImpl(entityManager);
        walletService = new WalletServiceImpl(walletRepository);
        adminAccessService = new AdminAccessServiceImpl(expertService, customerService, serviceService, underServiceService);
        customerAccessService = new CustomerAccessServiceImpl(customerService,underServiceService,customerRequestService);



    }

    public static EntityManager getEntityManager() {
        return entityManager;
    }

    public static Validator getEntityValidator() {
        return entityValidator;
    }

    public static CustomerService getCustomerService() {
        return customerService;
    }

    public static CustomerRequestService getCustomerRequestService() {
        return customerRequestService;
    }

    public static ExpertService getExpertService() {
        return expertService;
    }

    public static OrderService getOrderService() {
        return orderService;
    }

    public static ServiceService getServiceService() {
        return serviceService;
    }

    public static SuggestionService getSuggestionService() {
        return suggestionService;
    }

    public static UnderServiceService getUnderServiceService() {
        return underServiceService;
    }

    public static WalletService getWalletService() {
        return walletService;
    }

    public static AdminAccessService getAdminAccessService() {
        return adminAccessService;
    }

    public static CustomerAccessService getCustomerAccessService() {
        return customerAccessService;
    }
}
