package com.social.eshop.service;

import com.social.eshop.repository.ProductsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProductsDTOService {

    private final Logger log = LoggerFactory.getLogger(ProductsDTOService.class);
    private final ProductsRepository productsRepository;

    public ProductsDTOService(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }
}
