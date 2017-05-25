package com.social.eshop.web.rest;

import com.social.eshop.EshopApp;

import com.social.eshop.domain.Value;
import com.social.eshop.repository.ValueRepository;
import com.social.eshop.service.ValueService;
import com.social.eshop.repository.search.ValueSearchRepository;
import com.social.eshop.service.dto.ValueDTO;
import com.social.eshop.service.mapper.ValueMapper;
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
 * Test class for the ValueResource REST controller.
 *
 * @see ValueResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = EshopApp.class)
public class ValueResourceIntTest {

    private static final String DEFAULT_DATA = "AAAAAAAAAA";
    private static final String UPDATED_DATA = "BBBBBBBBBB";

    @Autowired
    private ValueRepository valueRepository;

    @Autowired
    private ValueMapper valueMapper;

    @Autowired
    private ValueService valueService;

    @Autowired
    private ValueSearchRepository valueSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restValueMockMvc;

    private Value value;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        ValueResource valueResource = new ValueResource(valueService);
        this.restValueMockMvc = MockMvcBuilders.standaloneSetup(valueResource)
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
    public static Value createEntity(EntityManager em) {
        Value value = new Value()
            .data(DEFAULT_DATA);
        return value;
    }

    @Before
    public void initTest() {
        valueSearchRepository.deleteAll();
        value = createEntity(em);
    }

    @Test
    @Transactional
    public void createValue() throws Exception {
        int databaseSizeBeforeCreate = valueRepository.findAll().size();

        // Create the Value
        ValueDTO valueDTO = valueMapper.toDto(value);
        restValueMockMvc.perform(post("/api/values")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(valueDTO)))
            .andExpect(status().isCreated());

        // Validate the Value in the database
        List<Value> valueList = valueRepository.findAll();
        assertThat(valueList).hasSize(databaseSizeBeforeCreate + 1);
        Value testValue = valueList.get(valueList.size() - 1);
        assertThat(testValue.getData()).isEqualTo(DEFAULT_DATA);

        // Validate the Value in Elasticsearch
        Value valueEs = valueSearchRepository.findOne(testValue.getId());
        assertThat(valueEs).isEqualToComparingFieldByField(testValue);
    }

    @Test
    @Transactional
    public void createValueWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = valueRepository.findAll().size();

        // Create the Value with an existing ID
        value.setId(1L);
        ValueDTO valueDTO = valueMapper.toDto(value);

        // An entity with an existing ID cannot be created, so this API call must fail
        restValueMockMvc.perform(post("/api/values")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(valueDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Value> valueList = valueRepository.findAll();
        assertThat(valueList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkDataIsRequired() throws Exception {
        int databaseSizeBeforeTest = valueRepository.findAll().size();
        // set the field null
        value.setData(null);

        // Create the Value, which fails.
        ValueDTO valueDTO = valueMapper.toDto(value);

        restValueMockMvc.perform(post("/api/values")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(valueDTO)))
            .andExpect(status().isBadRequest());

        List<Value> valueList = valueRepository.findAll();
        assertThat(valueList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllValues() throws Exception {
        // Initialize the database
        valueRepository.saveAndFlush(value);

        // Get all the valueList
        restValueMockMvc.perform(get("/api/values?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(value.getId().intValue())))
            .andExpect(jsonPath("$.[*].data").value(hasItem(DEFAULT_DATA.toString())));
    }

    @Test
    @Transactional
    public void getValue() throws Exception {
        // Initialize the database
        valueRepository.saveAndFlush(value);

        // Get the value
        restValueMockMvc.perform(get("/api/values/{id}", value.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(value.getId().intValue()))
            .andExpect(jsonPath("$.data").value(DEFAULT_DATA.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingValue() throws Exception {
        // Get the value
        restValueMockMvc.perform(get("/api/values/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateValue() throws Exception {
        // Initialize the database
        valueRepository.saveAndFlush(value);
        valueSearchRepository.save(value);
        int databaseSizeBeforeUpdate = valueRepository.findAll().size();

        // Update the value
        Value updatedValue = valueRepository.findOne(value.getId());
        updatedValue
            .data(UPDATED_DATA);
        ValueDTO valueDTO = valueMapper.toDto(updatedValue);

        restValueMockMvc.perform(put("/api/values")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(valueDTO)))
            .andExpect(status().isOk());

        // Validate the Value in the database
        List<Value> valueList = valueRepository.findAll();
        assertThat(valueList).hasSize(databaseSizeBeforeUpdate);
        Value testValue = valueList.get(valueList.size() - 1);
        assertThat(testValue.getData()).isEqualTo(UPDATED_DATA);

        // Validate the Value in Elasticsearch
        Value valueEs = valueSearchRepository.findOne(testValue.getId());
        assertThat(valueEs).isEqualToComparingFieldByField(testValue);
    }

    @Test
    @Transactional
    public void updateNonExistingValue() throws Exception {
        int databaseSizeBeforeUpdate = valueRepository.findAll().size();

        // Create the Value
        ValueDTO valueDTO = valueMapper.toDto(value);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restValueMockMvc.perform(put("/api/values")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(valueDTO)))
            .andExpect(status().isCreated());

        // Validate the Value in the database
        List<Value> valueList = valueRepository.findAll();
        assertThat(valueList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteValue() throws Exception {
        // Initialize the database
        valueRepository.saveAndFlush(value);
        valueSearchRepository.save(value);
        int databaseSizeBeforeDelete = valueRepository.findAll().size();

        // Get the value
        restValueMockMvc.perform(delete("/api/values/{id}", value.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate Elasticsearch is empty
        boolean valueExistsInEs = valueSearchRepository.exists(value.getId());
        assertThat(valueExistsInEs).isFalse();

        // Validate the database is empty
        List<Value> valueList = valueRepository.findAll();
        assertThat(valueList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void searchValue() throws Exception {
        // Initialize the database
        valueRepository.saveAndFlush(value);
        valueSearchRepository.save(value);

        // Search the value
        restValueMockMvc.perform(get("/api/_search/values?query=id:" + value.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(value.getId().intValue())))
            .andExpect(jsonPath("$.[*].data").value(hasItem(DEFAULT_DATA.toString())));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Value.class);
        Value value1 = new Value();
        value1.setId(1L);
        Value value2 = new Value();
        value2.setId(value1.getId());
        assertThat(value1).isEqualTo(value2);
        value2.setId(2L);
        assertThat(value1).isNotEqualTo(value2);
        value1.setId(null);
        assertThat(value1).isNotEqualTo(value2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ValueDTO.class);
        ValueDTO valueDTO1 = new ValueDTO();
        valueDTO1.setId(1L);
        ValueDTO valueDTO2 = new ValueDTO();
        assertThat(valueDTO1).isNotEqualTo(valueDTO2);
        valueDTO2.setId(valueDTO1.getId());
        assertThat(valueDTO1).isEqualTo(valueDTO2);
        valueDTO2.setId(2L);
        assertThat(valueDTO1).isNotEqualTo(valueDTO2);
        valueDTO1.setId(null);
        assertThat(valueDTO1).isNotEqualTo(valueDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(valueMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(valueMapper.fromId(null)).isNull();
    }
}
