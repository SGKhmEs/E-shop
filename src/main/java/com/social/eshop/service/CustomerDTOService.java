package com.social.eshop.service;

import com.social.eshop.domain.Customer;
import com.social.eshop.repository.CustomerRepository;
import com.social.eshop.service.dto.CustomerDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
@Transactional
public class CustomerDTOService {

    private final Logger log = LoggerFactory.getLogger(CustomerDTOService.class);

    private final CustomerRepository customerRepository;
    private final CustomerDTOService customerDTOService;

    public CustomerDTOService(CustomerRepository customerRepository, CustomerDTOService customerDTOService) {
        this.customerRepository = customerRepository;
        this.customerDTOService = customerDTOService;
    }

    @Transactional(readOnly = true)
    public CustomerDTO findOne(Long id) {
        log.debug("Request to get Customer : {}", id);
        CustomerDTO customerDTO = new CustomerDTO();
        Customer customer = customerRepository.findOne(id);
        return customerDTO;
    }
}
