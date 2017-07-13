package com.social.eshop.web.rest;

import com.social.eshop.EshopApp;

import com.social.eshop.domain.ProductInBucket;
import com.social.eshop.repository.ProductInBucketRepository;
import com.social.eshop.service.ProductInBucketService;
import com.social.eshop.repository.search.ProductInBucketSearchRepository;
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

/**
 * Test class for the ProductInBucketResource REST controller.
 *
 * @see ProductInBucketResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = EshopApp.class)
public class ProductInBucketResourceIntTest {

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

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
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
    public void createProductInBucketWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = productInBucketRepository.findAll().size();

        // Create the ProductInBucket with an existing ID
        productInBucket.setId(1L);
        ProductInBucketDTO productInBucketDTO = productInBucketMapper.toDto(productInBucket);

        // An entity with an existing ID cannot be created, so this API call must fail
        restProductInBucketMockMvc.perform(post("/api/product-in-buckets")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(productInBucketDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<ProductInBucket> productInBucketList = productInBucketRepository.findAll();
        assertThat(productInBucketList).hasSize(databaseSizeBeforeCreate);
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
    public void getNonExistingProductInBucket() throws Exception {
        // Get the productInBucket
        restProductInBucketMockMvc.perform(get("/api/product-in-buckets/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
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
    public void updateNonExistingProductInBucket() throws Exception {
        int databaseSizeBeforeUpdate = productInBucketRepository.findAll().size();

        // Create the ProductInBucket
        ProductInBucketDTO productInBucketDTO = productInBucketMapper.toDto(productInBucket);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restProductInBucketMockMvc.perform(put("/api/product-in-buckets")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(productInBucketDTO)))
            .andExpect(status().isCreated());

        // Validate the ProductInBucket in the database
        List<ProductInBucket> productInBucketList = productInBucketRepository.findAll();
        assertThat(productInBucketList).hasSize(databaseSizeBeforeUpdate + 1);
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

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProductInBucket.class);
        ProductInBucket productInBucket1 = new ProductInBucket();
        productInBucket1.setId(1L);
        ProductInBucket productInBucket2 = new ProductInBucket();
        productInBucket2.setId(productInBucket1.getId());
        assertThat(productInBucket1).isEqualTo(productInBucket2);
        productInBucket2.setId(2L);
        assertThat(productInBucket1).isNotEqualTo(productInBucket2);
        productInBucket1.setId(null);
        assertThat(productInBucket1).isNotEqualTo(productInBucket2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProductInBucketDTO.class);
        ProductInBucketDTO productInBucketDTO1 = new ProductInBucketDTO();
        productInBucketDTO1.setId(1L);
        ProductInBucketDTO productInBucketDTO2 = new ProductInBucketDTO();
        assertThat(productInBucketDTO1).isNotEqualTo(productInBucketDTO2);
        productInBucketDTO2.setId(productInBucketDTO1.getId());
        assertThat(productInBucketDTO1).isEqualTo(productInBucketDTO2);
        productInBucketDTO2.setId(2L);
        assertThat(productInBucketDTO1).isNotEqualTo(productInBucketDTO2);
        productInBucketDTO1.setId(null);
        assertThat(productInBucketDTO1).isNotEqualTo(productInBucketDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(productInBucketMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(productInBucketMapper.fromId(null)).isNull();
    }
}
