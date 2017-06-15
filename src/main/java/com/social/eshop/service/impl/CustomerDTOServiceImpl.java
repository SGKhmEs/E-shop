package com.social.eshop.service.impl;

import com.social.eshop.domain.Customer;
import com.social.eshop.repository.CustomerRepository;
import com.social.eshop.repository.PersonalInformationRepository;
import com.social.eshop.service.CustomerDTOService;
import com.social.eshop.service.dto.CustomerDTO;
import com.social.eshop.service.dto.CustomerRoomDTO;
import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;

import com.social.eshop.service.dto.PersonalInformationDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CustomerDTOServiceImpl implements CustomerDTOService {

    private final Logger log = LoggerFactory.getLogger(CustomerDTOServiceImpl.class);
    private CustomerRepository customerRepository;
    public CustomerDTOServiceImpl() {
    }

    public CustomerDTOServiceImpl(CustomerRoomDTO customerRoomDTO, PersonalInformationRepository personalInformationRepository, CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Transactional(readOnly = true)
    public CustomerDTO findOne(Long id) {
        log.debug("Request to get Author : {}", id);
        CustomerDTO customerDTO = new CustomerDTO();

        PersonalInformationDTO personalInformationDTO = new PersonalInformationDTO();
        Customer customer = customerRepository.findOne(id);
        try {
            customerDTO.mappingToDTO(customer, personalInformationDTO);
        } catch (InvocationTargetException ex) {
            java.util.logging.Logger.getLogger(CustomerDTOServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return customerDTO;

    }

}
