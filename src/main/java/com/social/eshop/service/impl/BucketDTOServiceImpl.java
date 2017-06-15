package com.social.eshop.service.impl;

import com.social.eshop.domain.Bucket;
import com.social.eshop.repository.BucketRepository;
import com.social.eshop.service.BucketDTOService;
import com.social.eshop.service.dto.BucketDTO;
import com.social.eshop.service.dto.CustomerDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

@Service
@Transactional
public class BucketDTOServiceImpl implements BucketDTOService {

    private final Logger log = LoggerFactory.getLogger(BucketDTOServiceImpl.class);

    private CustomerDTOServiceImpl customerDTOService;
    private BucketRepository bucketRepository;

    public BucketDTOServiceImpl() {  }

    public BucketDTOServiceImpl(CustomerDTOServiceImpl customerDTOService, BucketRepository bucketRepository) {
        this.customerDTOService = customerDTOService;
        this.bucketRepository = bucketRepository;
    }

    public BucketDTO findOne(Long id) {
        log.debug("Request to get Buckets : {}", id);
        Bucket bucket = bucketRepository.getOne(id);
        CustomerDTO customerDTO = customerDTOService.findOne(bucketRepository.getOne(id).getCustomer().getCustomerRoom().getPersonalInfo().getId());
        BucketDTO bucketDTO = new BucketDTO();

        try {
            bucketDTO.mappingToDTO(bucketDTO, customerDTO);
        } catch (InvocationTargetException ex) {
            java.util.logging.Logger.getLogger(BucketDTOServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bucketDTO;
    }

    public List<BucketDTO> findAllByProduct(Long id) {

        List<Bucket> bucketList = bucketRepository.findByProductsId(id);
        List<BucketDTO> bucketDTOList = new ArrayList<>();

        for (Bucket bucket : bucketList) {
            BucketDTO bucketDTO = new BucketDTO();
            CustomerDTO customerDTO = customerDTOService.findOne(bucketRepository.getOne(id).getCustomer().getCustomerRoom().getPersonalInfo().getId());
            try {
                bucketDTO.mappingToDTO(bucket, customerDTO);
            } catch (InvocationTargetException ex) {
                java.util.logging.Logger.getLogger(BucketDTOServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            bucketDTOList.add(bucketDTO);
        }
        return bucketDTOList;
    }
}
