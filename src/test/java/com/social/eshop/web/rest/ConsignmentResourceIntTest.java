package com.social.eshop.web.rest;

import com.social.eshop.EshopApp;

import com.social.eshop.domain.Consignment;
import com.social.eshop.repository.ConsignmentRepository;
import com.social.eshop.service.ConsignmentService;
import com.social.eshop.repository.search.ConsignmentSearchRepository;
import com.social.eshop.service.dto.ConsignmentDTO;
import com.social.eshop.service.mapper.ConsignmentMapper;
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
import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the ConsignmentResource REST controller.
 *
 * @see ConsignmentResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = EshopApp.class)
public class ConsignmentResourceIntTest {

    private static final BigDecimal DEFAULT_PRICE = new BigDecimal(1);
    private static final BigDecimal UPDATED_PRICE = new BigDecimal(2);

    @Autowired
    private ConsignmentRepository consignmentRepository;

    @Autowired
    private ConsignmentMapper consignmentMapper;

    @Autowired
    private ConsignmentService consignmentService;

    @Autowired
    private ConsignmentSearchRepository consignmentSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restConsignmentMockMvc;

    private Consignment consignment;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        ConsignmentResource consignmentResource = new ConsignmentResource(consignmentService);
        this.restConsignmentMockMvc = MockMvcBuilders.standaloneSetup(consignmentResource)
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
    public static Consignment createEntity(EntityManager em) {
        Consignment consignment = new Consignment()
            .price(DEFAULT_PRICE);
        return consignment;
    }

    @Before
    public void initTest() {
        consignmentSearchRepository.deleteAll();
        consignment = createEntity(em);
    }

    @Test
    @Transactional
    public void createConsignment() throws Exception {
        int databaseSizeBeforeCreate = consignmentRepository.findAll().size();

        // Create the Consignment
        ConsignmentDTO consignmentDTO = consignmentMapper.toDto(consignment);
        restConsignmentMockMvc.perform(post("/api/consignments")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(consignmentDTO)))
            .andExpect(status().isCreated());

        // Validate the Consignment in the database
        List<Consignment> consignmentList = consignmentRepository.findAll();
        assertThat(consignmentList).hasSize(databaseSizeBeforeCreate + 1);
        Consignment testConsignment = consignmentList.get(consignmentList.size() - 1);
        assertThat(testConsignment.getPrice()).isEqualTo(DEFAULT_PRICE);

        // Validate the Consignment in Elasticsearch
        Consignment consignmentEs = consignmentSearchRepository.findOne(testConsignment.getId());
        assertThat(consignmentEs).isEqualToComparingFieldByField(testConsignment);
    }

    @Test
    @Transactional
    public void createConsignmentWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = consignmentRepository.findAll().size();

        // Create the Consignment with an existing ID
        consignment.setId(1L);
        ConsignmentDTO consignmentDTO = consignmentMapper.toDto(consignment);

        // An entity with an existing ID cannot be created, so this API call must fail
        restConsignmentMockMvc.perform(post("/api/consignments")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(consignmentDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Consignment> consignmentList = consignmentRepository.findAll();
        assertThat(consignmentList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkPriceIsRequired() throws Exception {
        int databaseSizeBeforeTest = consignmentRepository.findAll().size();
        // set the field null
        consignment.setPrice(null);

        // Create the Consignment, which fails.
        ConsignmentDTO consignmentDTO = consignmentMapper.toDto(consignment);

        restConsignmentMockMvc.perform(post("/api/consignments")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(consignmentDTO)))
            .andExpect(status().isBadRequest());

        List<Consignment> consignmentList = consignmentRepository.findAll();
        assertThat(consignmentList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllConsignments() throws Exception {
        // Initialize the database
        consignmentRepository.saveAndFlush(consignment);

        // Get all the consignmentList
        restConsignmentMockMvc.perform(get("/api/consignments?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(consignment.getId().intValue())))
            .andExpect(jsonPath("$.[*].price").value(hasItem(DEFAULT_PRICE.intValue())));
    }

    @Test
    @Transactional
    public void getConsignment() throws Exception {
        // Initialize the database
        consignmentRepository.saveAndFlush(consignment);

        // Get the consignment
        restConsignmentMockMvc.perform(get("/api/consignments/{id}", consignment.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(consignment.getId().intValue()))
            .andExpect(jsonPath("$.price").value(DEFAULT_PRICE.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingConsignment() throws Exception {
        // Get the consignment
        restConsignmentMockMvc.perform(get("/api/consignments/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateConsignment() throws Exception {
        // Initialize the database
        consignmentRepository.saveAndFlush(consignment);
        consignmentSearchRepository.save(consignment);
        int databaseSizeBeforeUpdate = consignmentRepository.findAll().size();

        // Update the consignment
        Consignment updatedConsignment = consignmentRepository.findOne(consignment.getId());
        updatedConsignment
            .price(UPDATED_PRICE);
        ConsignmentDTO consignmentDTO = consignmentMapper.toDto(updatedConsignment);

        restConsignmentMockMvc.perform(put("/api/consignments")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(consignmentDTO)))
            .andExpect(status().isOk());

        // Validate the Consignment in the database
        List<Consignment> consignmentList = consignmentRepository.findAll();
        assertThat(consignmentList).hasSize(databaseSizeBeforeUpdate);
        Consignment testConsignment = consignmentList.get(consignmentList.size() - 1);
        assertThat(testConsignment.getPrice()).isEqualTo(UPDATED_PRICE);

        // Validate the Consignment in Elasticsearch
        Consignment consignmentEs = consignmentSearchRepository.findOne(testConsignment.getId());
        assertThat(consignmentEs).isEqualToComparingFieldByField(testConsignment);
    }

    @Test
    @Transactional
    public void updateNonExistingConsignment() throws Exception {
        int databaseSizeBeforeUpdate = consignmentRepository.findAll().size();

        // Create the Consignment
        ConsignmentDTO consignmentDTO = consignmentMapper.toDto(consignment);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restConsignmentMockMvc.perform(put("/api/consignments")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(consignmentDTO)))
            .andExpect(status().isCreated());

        // Validate the Consignment in the database
        List<Consignment> consignmentList = consignmentRepository.findAll();
        assertThat(consignmentList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteConsignment() throws Exception {
        // Initialize the database
        consignmentRepository.saveAndFlush(consignment);
        consignmentSearchRepository.save(consignment);
        int databaseSizeBeforeDelete = consignmentRepository.findAll().size();

        // Get the consignment
        restConsignmentMockMvc.perform(delete("/api/consignments/{id}", consignment.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate Elasticsearch is empty
        boolean consignmentExistsInEs = consignmentSearchRepository.exists(consignment.getId());
        assertThat(consignmentExistsInEs).isFalse();

        // Validate the database is empty
        List<Consignment> consignmentList = consignmentRepository.findAll();
        assertThat(consignmentList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void searchConsignment() throws Exception {
        // Initialize the database
        consignmentRepository.saveAndFlush(consignment);
        consignmentSearchRepository.save(consignment);

        // Search the consignment
        restConsignmentMockMvc.perform(get("/api/_search/consignments?query=id:" + consignment.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(consignment.getId().intValue())))
            .andExpect(jsonPath("$.[*].price").value(hasItem(DEFAULT_PRICE.intValue())));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Consignment.class);
        Consignment consignment1 = new Consignment();
        consignment1.setId(1L);
        Consignment consignment2 = new Consignment();
        consignment2.setId(consignment1.getId());
        assertThat(consignment1).isEqualTo(consignment2);
        consignment2.setId(2L);
        assertThat(consignment1).isNotEqualTo(consignment2);
        consignment1.setId(null);
        assertThat(consignment1).isNotEqualTo(consignment2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ConsignmentDTO.class);
        ConsignmentDTO consignmentDTO1 = new ConsignmentDTO();
        consignmentDTO1.setId(1L);
        ConsignmentDTO consignmentDTO2 = new ConsignmentDTO();
        assertThat(consignmentDTO1).isNotEqualTo(consignmentDTO2);
        consignmentDTO2.setId(consignmentDTO1.getId());
        assertThat(consignmentDTO1).isEqualTo(consignmentDTO2);
        consignmentDTO2.setId(2L);
        assertThat(consignmentDTO1).isNotEqualTo(consignmentDTO2);
        consignmentDTO1.setId(null);
        assertThat(consignmentDTO1).isNotEqualTo(consignmentDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(consignmentMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(consignmentMapper.fromId(null)).isNull();
    }
}
