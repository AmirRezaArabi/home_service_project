package org.example.service;

public interface AdminAccessService {

    boolean addService(String serviceName) ;

    boolean addUnderService(String serviceName, String underServiceName, String describtion, Long basePrice );

    boolean updatePriceOrDescription(String underServiceName, String describtion, Long basePrice);

    boolean addExpertToSystem(String expertUserName,String UnderService);

    boolean removeExpertToSystem(String expertUserName,String UnderService);

    boolean confirmExpert(String expertUserName);

}
