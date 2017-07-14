package com.social.eshop.web.rest;

import com.social.eshop.EshopApp;
import com.social.eshop.domain.ProductInBucket;
import com.social.eshop.repository.ProductInBucketRepository;
import com.social.eshop.repository.search.ProductInBucketSearchRepository;
import com.social.eshop.service.ProductInBucketService;
import com.social.eshop.service.dto.ProductInBucketDTO;
import com.social.eshop.service.mapper.ProductInBucketMapper;
import com.social.eshop.web.rest.errors.ExceptionTranslator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = EshopApp.class)
public class ProductInBucketResourceTest {

    @Autowired
    private ProductInBucketRepository productInBucketRepository;

    @Autowired
    private ProductInBucketMapper productInBucketMapper;

    @Autowired
    private ProductInBucketService productInBucketService;

    @Autowired
    private ProductInBucketSearchRepository productInBucketSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restProductInBucketMockMvc;

    private ProductInBucket productInBucket;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        ProductInBucketResource productInBucketResource = new ProductInBucketResource(productInBucketService);
        this.restProductInBucketMockMvc = MockMvcBuilders.standaloneSetup(productInBucketResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    public static ProductInBucket createEntity(EntityManager em) {
        ProductInBucket productInBucket = new ProductInBucket();
        return productInBucket;
    }

    @Before
    public void initTest() {
        productInBucketSearchRepository.deleteAll();
        productInBucket = createEntity(em);
    }

    @Test
    @Transactional
    public void createProductInBucket() throws Exception {
        int databaseSizeBeforeCreate = productInBucketRepository.findAll().size();

        // Create the ProductInBucket
        ProductInBucketDTO productInBucketDTO = productInBucketMapper.toDto(productInBucket);
        restProductInBucketMockMvc.perform(post("/api/product-in-buckets")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(productInBucketDTO)))
            .andExpect(status().isCreated());

        // Validate the ProductInBucket in the database
        List<ProductInBucket> productInBucketList = productInBucketRepository.findAll();
        assertThat(productInBucketList).hasSize(databaseSizeBeforeCreate + 1);
        ProductInBucket testProductInBucket = productInBucketList.get(productInBucketList.size() - 1);

        // Validate the ProductInBucket in Elasticsearch
        ProductInBucket productInBucketEs = productInBucketSearchRepository.findOne(testProductInBucket.getId());
        assertThat(productInBucketEs).isEqualToComparingFieldByField(testProductInBucket);
    }

    @Test
    @Transactional
    public void getAllProductInBuckets() throws Exception {
        // Initialize the database
        productInBucketRepository.saveAndFlush(productInBucket);

        // Get all the productInBucketList
        restProductInBucketMockMvc.perform(get("/api/product-in-buckets?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(productInBucket.getId().intValue())));
    }

    @Test
    @Transactional
    public void getProductInBucket() throws Exception {
        // Initialize the database
        productInBucketRepository.saveAndFlush(productInBucket);

        // Get the productInBucket
        restProductInBucketMockMvc.perform(get("/api/product-in-buckets/{id}", productInBucket.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(productInBucket.getId().intValue()));
    }

    @Test
    @Transactional
    public void updateProductInBucket() throws Exception {
        // Initialize the database
        productInBucketRepository.saveAndFlush(productInBucket);
        productInBucketSearchRepository.save(productInBucket);
        int databaseSizeBeforeUpdate = productInBucketRepository.findAll().size();

        // Update the productInBucket
        ProductInBucket updatedProductInBucket = productInBucketRepository.findOne(productInBucket.getId());
        ProductInBucketDTO productInBucketDTO = productInBucketMapper.toDto(updatedProductInBucket);

        restProductInBucketMockMvc.perform(put("/api/product-in-buckets")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(productInBucketDTO)))
            .andExpect(status().isOk());

        // Validate the ProductInBucket in the database
        List<ProductInBucket> productInBucketList = productInBucketRepository.findAll();
        assertThat(productInBucketList).hasSize(databaseSizeBeforeUpdate);
        ProductInBucket testProductInBucket = productInBucketList.get(productInBucketList.size() - 1);

        // Validate the ProductInBucket in Elasticsearch
        ProductInBucket productInBucketEs = productInBucketSearchRepository.findOne(testProductInBucket.getId());
        assertThat(productInBucketEs).isEqualToComparingFieldByField(testProductInBucket);
    }

    @Test
    @Transactional
    public void deleteProductInBucket() throws Exception {
        // Initialize the database
        productInBucketRepository.saveAndFlush(productInBucket);
        productInBucketSearchRepository.save(productInBucket);
        int databaseSizeBeforeDelete = productInBucketRepository.findAll().size();

        // Get the productInBucket
        restProductInBucketMockMvc.perform(delete("/api/product-in-buckets/{id}", productInBucket.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate Elasticsearch is empty
        boolean productInBucketExistsInEs = productInBucketSearchRepository.exists(productInBucket.getId());
        assertThat(productInBucketExistsInEs).isFalse();

        // Validate the database is empty
        List<ProductInBucket> productInBucketList = productInBucketRepository.findAll();
        assertThat(productInBucketList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void searchProductInBucket() throws Exception {
        // Initialize the database
        productInBucketRepository.saveAndFlush(productInBucket);
        productInBucketSearchRepository.save(productInBucket);

        // Search the productInBucket
        restProductInBucketMockMvc.perform(get("/api/_search/product-in-buckets?query=id:" + productInBucket.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(productInBucket.getId().intValue())));
    }
}
