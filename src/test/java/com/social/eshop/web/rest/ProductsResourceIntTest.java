package com.social.eshop.web.rest;

import com.social.eshop.EshopApp;

import com.social.eshop.domain.Products;
import com.social.eshop.repository.ProductsRepository;
import com.social.eshop.service.ProductsService;
import com.social.eshop.repository.search.ProductsSearchRepository;
import com.social.eshop.service.dto.ProductsDTO;
import com.social.eshop.service.mapper.ProductsMapper;
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
 * Test class for the ProductsResource REST controller.
 *
 * @see ProductsResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = EshopApp.class)
public class ProductsResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_SALE = "AAAAAAAAAA";
    private static final String UPDATED_SALE = "BBBBBBBBBB";

    private static final Double DEFAULT_RATING = 1D;
    private static final Double UPDATED_RATING = 2D;

    private static final Boolean DEFAULT_FRESH = false;
    private static final Boolean UPDATED_FRESH = true;

    @Autowired
    private ProductsRepository productsRepository;

    @Autowired
    private ProductsMapper productsMapper;

    @Autowired
    private ProductsService productsService;

    @Autowired
    private ProductsSearchRepository productsSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restProductsMockMvc;

    private Products products;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        ProductsResource productsResource = new ProductsResource(productsService);
        this.restProductsMockMvc = MockMvcBuilders.standaloneSetup(productsResource)
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
    public static Products createEntity(EntityManager em) {
        Products products = new Products()
            .name(DEFAULT_NAME)
            .sale(DEFAULT_SALE)
            .rating(DEFAULT_RATING)
            .fresh(DEFAULT_FRESH);
        return products;
    }

    @Before
    public void initTest() {
        productsSearchRepository.deleteAll();
        products = createEntity(em);
    }

    @Test
    @Transactional
    public void createProducts() throws Exception {
        int databaseSizeBeforeCreate = productsRepository.findAll().size();

        // Create the Products
        ProductsDTO productsDTO = productsMapper.toDto(products);
        restProductsMockMvc.perform(post("/api/products")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(productsDTO)))
            .andExpect(status().isCreated());

        // Validate the Products in the database
        List<Products> productsList = productsRepository.findAll();
        assertThat(productsList).hasSize(databaseSizeBeforeCreate + 1);
        Products testProducts = productsList.get(productsList.size() - 1);
        assertThat(testProducts.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testProducts.getSale()).isEqualTo(DEFAULT_SALE);
        assertThat(testProducts.getRating()).isEqualTo(DEFAULT_RATING);
        assertThat(testProducts.isFresh()).isEqualTo(DEFAULT_FRESH);

        // Validate the Products in Elasticsearch
        Products productsEs = productsSearchRepository.findOne(testProducts.getId());
        assertThat(productsEs).isEqualToComparingFieldByField(testProducts);
    }

    @Test
    @Transactional
    public void createProductsWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = productsRepository.findAll().size();

        // Create the Products with an existing ID
        products.setId(1L);
        ProductsDTO productsDTO = productsMapper.toDto(products);

        // An entity with an existing ID cannot be created, so this API call must fail
        restProductsMockMvc.perform(post("/api/products")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(productsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Products> productsList = productsRepository.findAll();
        assertThat(productsList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = productsRepository.findAll().size();
        // set the field null
        products.setName(null);

        // Create the Products, which fails.
        ProductsDTO productsDTO = productsMapper.toDto(products);

        restProductsMockMvc.perform(post("/api/products")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(productsDTO)))
            .andExpect(status().isBadRequest());

        List<Products> productsList = productsRepository.findAll();
        assertThat(productsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllProducts() throws Exception {
        // Initialize the database
        productsRepository.saveAndFlush(products);

        // Get all the productsList
        restProductsMockMvc.perform(get("/api/products?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(products.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].sale").value(hasItem(DEFAULT_SALE.toString())))
            .andExpect(jsonPath("$.[*].rating").value(hasItem(DEFAULT_RATING.doubleValue())))
            .andExpect(jsonPath("$.[*].fresh").value(hasItem(DEFAULT_FRESH.booleanValue())));
    }

    @Test
    @Transactional
    public void getProducts() throws Exception {
        // Initialize the database
        productsRepository.saveAndFlush(products);

        // Get the products
        restProductsMockMvc.perform(get("/api/products/{id}", products.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(products.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.sale").value(DEFAULT_SALE.toString()))
            .andExpect(jsonPath("$.rating").value(DEFAULT_RATING.doubleValue()))
            .andExpect(jsonPath("$.fresh").value(DEFAULT_FRESH.booleanValue()));
    }

    @Test
    @Transactional
    public void getNonExistingProducts() throws Exception {
        // Get the products
        restProductsMockMvc.perform(get("/api/products/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateProducts() throws Exception {
        // Initialize the database
        productsRepository.saveAndFlush(products);
        productsSearchRepository.save(products);
        int databaseSizeBeforeUpdate = productsRepository.findAll().size();

        // Update the products
        Products updatedProducts = productsRepository.findOne(products.getId());
        updatedProducts
            .name(UPDATED_NAME)
            .sale(UPDATED_SALE)
            .rating(UPDATED_RATING)
            .fresh(UPDATED_FRESH);
        ProductsDTO productsDTO = productsMapper.toDto(updatedProducts);

        restProductsMockMvc.perform(put("/api/products")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(productsDTO)))
            .andExpect(status().isOk());

        // Validate the Products in the database
        List<Products> productsList = productsRepository.findAll();
        assertThat(productsList).hasSize(databaseSizeBeforeUpdate);
        Products testProducts = productsList.get(productsList.size() - 1);
        assertThat(testProducts.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testProducts.getSale()).isEqualTo(UPDATED_SALE);
        assertThat(testProducts.getRating()).isEqualTo(UPDATED_RATING);
        assertThat(testProducts.isFresh()).isEqualTo(UPDATED_FRESH);

        // Validate the Products in Elasticsearch
        Products productsEs = productsSearchRepository.findOne(testProducts.getId());
        assertThat(productsEs).isEqualToComparingFieldByField(testProducts);
    }

    @Test
    @Transactional
    public void updateNonExistingProducts() throws Exception {
        int databaseSizeBeforeUpdate = productsRepository.findAll().size();

        // Create the Products
        ProductsDTO productsDTO = productsMapper.toDto(products);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restProductsMockMvc.perform(put("/api/products")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(productsDTO)))
            .andExpect(status().isCreated());

        // Validate the Products in the database
        List<Products> productsList = productsRepository.findAll();
        assertThat(productsList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteProducts() throws Exception {
        // Initialize the database
        productsRepository.saveAndFlush(products);
        productsSearchRepository.save(products);
        int databaseSizeBeforeDelete = productsRepository.findAll().size();

        // Get the products
        restProductsMockMvc.perform(delete("/api/products/{id}", products.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate Elasticsearch is empty
        boolean productsExistsInEs = productsSearchRepository.exists(products.getId());
        assertThat(productsExistsInEs).isFalse();

        // Validate the database is empty
        List<Products> productsList = productsRepository.findAll();
        assertThat(productsList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void searchProducts() throws Exception {
        // Initialize the database
        productsRepository.saveAndFlush(products);
        productsSearchRepository.save(products);

        // Search the products
        restProductsMockMvc.perform(get("/api/_search/products?query=id:" + products.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(products.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].sale").value(hasItem(DEFAULT_SALE.toString())))
            .andExpect(jsonPath("$.[*].rating").value(hasItem(DEFAULT_RATING.doubleValue())))
            .andExpect(jsonPath("$.[*].fresh").value(hasItem(DEFAULT_FRESH.booleanValue())));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Products.class);
        Products products1 = new Products();
        products1.setId(1L);
        Products products2 = new Products();
        products2.setId(products1.getId());
        assertThat(products1).isEqualTo(products2);
        products2.setId(2L);
        assertThat(products1).isNotEqualTo(products2);
        products1.setId(null);
        assertThat(products1).isNotEqualTo(products2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProductsDTO.class);
        ProductsDTO productsDTO1 = new ProductsDTO();
        productsDTO1.setId(1L);
        ProductsDTO productsDTO2 = new ProductsDTO();
        assertThat(productsDTO1).isNotEqualTo(productsDTO2);
        productsDTO2.setId(productsDTO1.getId());
        assertThat(productsDTO1).isEqualTo(productsDTO2);
        productsDTO2.setId(2L);
        assertThat(productsDTO1).isNotEqualTo(productsDTO2);
        productsDTO1.setId(null);
        assertThat(productsDTO1).isNotEqualTo(productsDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(productsMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(productsMapper.fromId(null)).isNull();
    }
}
