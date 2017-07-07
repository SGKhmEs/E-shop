package com.social.eshop.service.impl;

import com.social.eshop.domain.Bucket;
import com.social.eshop.domain.Products;
import com.social.eshop.repository.BucketRepository;
import com.social.eshop.repository.CustomerRepository;
import com.social.eshop.repository.ProductsRepository;
import com.social.eshop.service.BucketDTOService;
import com.social.eshop.service.dto.BucketDTO;
import com.social.eshop.service.dto.CustomerDTO;
import com.social.eshop.service.dto.ProductsDTO;
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
    private CustomerRepository customerRepository;
    private CustomerDTOServiceImpl customerDTOService;
    private BucketRepository bucketRepository;
    private ProductsRepository productsRepository;
    private BucketDTOServiceImpl bucketDTOService;
    private ProductsDTO productsDTO;


    public BucketDTOServiceImpl() {  }

    public BucketDTOServiceImpl(CustomerRepository customerRepository, CustomerDTOServiceImpl customerDTOService,
                                BucketRepository bucketRepository, ProductsRepository productsRepository, BucketDTOServiceImpl bucketDTOService, ProductsDTO productsDTO) {
        this.customerRepository = customerRepository;
        this.customerDTOService = customerDTOService;
        this.bucketRepository = bucketRepository;
        this.productsRepository = productsRepository;
        this.bucketDTOService = bucketDTOService;
        this.productsDTO = productsDTO;
    }

    public BucketDTO findOne(Long id) {
        log.debug("Request to get Buckets : {}", id);
        Bucket bucket = bucketRepository.getOne(id);
        CustomerDTO customerDTO = customerDTOService.findOne(bucketRepository.getOne(id).getCustomer().getCustomerRoom()
                                                            .getPersonalInfo().getId());
        BucketDTO bucketDTO = new BucketDTO();

        try {
            bucketDTO.mappingToDTO(bucketDTO, customerDTO);
        } catch (InvocationTargetException ex) {
            java.util.logging.Logger.getLogger(BucketDTOServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return bucketDTO;
    }

    public List<BucketDTO> findAllByBucket(Long id) {

        List<Bucket> bucketList = bucketRepository.findByProductsId(id);
        List<BucketDTO> bucketDTOList = new ArrayList<>();
        Long dataId = bucketRepository.getOne(id).getCustomer().getCustomerRoom()
            .getPersonalInfo().getId();

        CustomerDTO customerDTO = customerDTOService.findOne(dataId);

        for (Bucket bucket : bucketList) {
            BucketDTO bucketDTO = new BucketDTO();
            try {
                bucketDTO.mappingToDTO(bucket, customerDTO);
            } catch (InvocationTargetException ex) {
                java.util.logging.Logger.getLogger(BucketDTOServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            bucketDTOList.add(bucketDTO);
        }
        return bucketDTOList;
    }


    public List<ProductsDTO> findAllProductsInBucket(Long id) {

        Bucket bucket = bucketRepository.getOne(id);
        List<ProductsDTO> list = new ArrayList<>();
        List<Products> productsList = productsRepository.findByBucketId(id);
        BucketDTO bucketDTO = bucketDTOService.findOne(bucket.getId());

        for (Products product : productsList) {
            ProductsDTO productsDTO = new ProductsDTO();

            try {
                productsDTO.mappingToDTO(bucketDTO,product);
            } catch (InvocationTargetException ex) {
                java.util.logging.Logger.getLogger(BucketDTOServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            list.add(productsDTO);
        }
        return list ;
    }
}
