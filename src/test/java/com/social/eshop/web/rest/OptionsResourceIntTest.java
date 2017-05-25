package com.social.eshop.web.rest;

import com.social.eshop.EshopApp;

import com.social.eshop.domain.Options;
import com.social.eshop.repository.OptionsRepository;
import com.social.eshop.service.OptionsService;
import com.social.eshop.repository.search.OptionsSearchRepository;
import com.social.eshop.service.dto.OptionsDTO;
import com.social.eshop.service.mapper.OptionsMapper;
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
 * Test class for the OptionsResource REST controller.
 *
 * @see OptionsResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = EshopApp.class)
public class OptionsResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final Integer DEFAULT_LEVEL = 1;
    private static final Integer UPDATED_LEVEL = 2;

    @Autowired
    private OptionsRepository optionsRepository;

    @Autowired
    private OptionsMapper optionsMapper;

    @Autowired
    private OptionsService optionsService;

    @Autowired
    private OptionsSearchRepository optionsSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restOptionsMockMvc;

    private Options options;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        OptionsResource optionsResource = new OptionsResource(optionsService);
        this.restOptionsMockMvc = MockMvcBuilders.standaloneSetup(optionsResource)
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
    public static Options createEntity(EntityManager em) {
        Options options = new Options()
            .name(DEFAULT_NAME)
            .level(DEFAULT_LEVEL);
        return options;
    }

    @Before
    public void initTest() {
        optionsSearchRepository.deleteAll();
        options = createEntity(em);
    }

    @Test
    @Transactional
    public void createOptions() throws Exception {
        int databaseSizeBeforeCreate = optionsRepository.findAll().size();

        // Create the Options
        OptionsDTO optionsDTO = optionsMapper.toDto(options);
        restOptionsMockMvc.perform(post("/api/options")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(optionsDTO)))
            .andExpect(status().isCreated());

        // Validate the Options in the database
        List<Options> optionsList = optionsRepository.findAll();
        assertThat(optionsList).hasSize(databaseSizeBeforeCreate + 1);
        Options testOptions = optionsList.get(optionsList.size() - 1);
        assertThat(testOptions.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testOptions.getLevel()).isEqualTo(DEFAULT_LEVEL);

        // Validate the Options in Elasticsearch
        Options optionsEs = optionsSearchRepository.findOne(testOptions.getId());
        assertThat(optionsEs).isEqualToComparingFieldByField(testOptions);
    }

    @Test
    @Transactional
    public void createOptionsWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = optionsRepository.findAll().size();

        // Create the Options with an existing ID
        options.setId(1L);
        OptionsDTO optionsDTO = optionsMapper.toDto(options);

        // An entity with an existing ID cannot be created, so this API call must fail
        restOptionsMockMvc.perform(post("/api/options")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(optionsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Options> optionsList = optionsRepository.findAll();
        assertThat(optionsList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = optionsRepository.findAll().size();
        // set the field null
        options.setName(null);

        // Create the Options, which fails.
        OptionsDTO optionsDTO = optionsMapper.toDto(options);

        restOptionsMockMvc.perform(post("/api/options")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(optionsDTO)))
            .andExpect(status().isBadRequest());

        List<Options> optionsList = optionsRepository.findAll();
        assertThat(optionsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLevelIsRequired() throws Exception {
        int databaseSizeBeforeTest = optionsRepository.findAll().size();
        // set the field null
        options.setLevel(null);

        // Create the Options, which fails.
        OptionsDTO optionsDTO = optionsMapper.toDto(options);

        restOptionsMockMvc.perform(post("/api/options")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(optionsDTO)))
            .andExpect(status().isBadRequest());

        List<Options> optionsList = optionsRepository.findAll();
        assertThat(optionsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllOptions() throws Exception {
        // Initialize the database
        optionsRepository.saveAndFlush(options);

        // Get all the optionsList
        restOptionsMockMvc.perform(get("/api/options?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(options.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].level").value(hasItem(DEFAULT_LEVEL)));
    }

    @Test
    @Transactional
    public void getOptions() throws Exception {
        // Initialize the database
        optionsRepository.saveAndFlush(options);

        // Get the options
        restOptionsMockMvc.perform(get("/api/options/{id}", options.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(options.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.level").value(DEFAULT_LEVEL));
    }

    @Test
    @Transactional
    public void getNonExistingOptions() throws Exception {
        // Get the options
        restOptionsMockMvc.perform(get("/api/options/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateOptions() throws Exception {
        // Initialize the database
        optionsRepository.saveAndFlush(options);
        optionsSearchRepository.save(options);
        int databaseSizeBeforeUpdate = optionsRepository.findAll().size();

        // Update the options
        Options updatedOptions = optionsRepository.findOne(options.getId());
        updatedOptions
            .name(UPDATED_NAME)
            .level(UPDATED_LEVEL);
        OptionsDTO optionsDTO = optionsMapper.toDto(updatedOptions);

        restOptionsMockMvc.perform(put("/api/options")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(optionsDTO)))
            .andExpect(status().isOk());

        // Validate the Options in the database
        List<Options> optionsList = optionsRepository.findAll();
        assertThat(optionsList).hasSize(databaseSizeBeforeUpdate);
        Options testOptions = optionsList.get(optionsList.size() - 1);
        assertThat(testOptions.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testOptions.getLevel()).isEqualTo(UPDATED_LEVEL);

        // Validate the Options in Elasticsearch
        Options optionsEs = optionsSearchRepository.findOne(testOptions.getId());
        assertThat(optionsEs).isEqualToComparingFieldByField(testOptions);
    }

    @Test
    @Transactional
    public void updateNonExistingOptions() throws Exception {
        int databaseSizeBeforeUpdate = optionsRepository.findAll().size();

        // Create the Options
        OptionsDTO optionsDTO = optionsMapper.toDto(options);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restOptionsMockMvc.perform(put("/api/options")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(optionsDTO)))
            .andExpect(status().isCreated());

        // Validate the Options in the database
        List<Options> optionsList = optionsRepository.findAll();
        assertThat(optionsList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteOptions() throws Exception {
        // Initialize the database
        optionsRepository.saveAndFlush(options);
        optionsSearchRepository.save(options);
        int databaseSizeBeforeDelete = optionsRepository.findAll().size();

        // Get the options
        restOptionsMockMvc.perform(delete("/api/options/{id}", options.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate Elasticsearch is empty
        boolean optionsExistsInEs = optionsSearchRepository.exists(options.getId());
        assertThat(optionsExistsInEs).isFalse();

        // Validate the database is empty
        List<Options> optionsList = optionsRepository.findAll();
        assertThat(optionsList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void searchOptions() throws Exception {
        // Initialize the database
        optionsRepository.saveAndFlush(options);
        optionsSearchRepository.save(options);

        // Search the options
        restOptionsMockMvc.perform(get("/api/_search/options?query=id:" + options.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(options.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].level").value(hasItem(DEFAULT_LEVEL)));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Options.class);
        Options options1 = new Options();
        options1.setId(1L);
        Options options2 = new Options();
        options2.setId(options1.getId());
        assertThat(options1).isEqualTo(options2);
        options2.setId(2L);
        assertThat(options1).isNotEqualTo(options2);
        options1.setId(null);
        assertThat(options1).isNotEqualTo(options2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(OptionsDTO.class);
        OptionsDTO optionsDTO1 = new OptionsDTO();
        optionsDTO1.setId(1L);
        OptionsDTO optionsDTO2 = new OptionsDTO();
        assertThat(optionsDTO1).isNotEqualTo(optionsDTO2);
        optionsDTO2.setId(optionsDTO1.getId());
        assertThat(optionsDTO1).isEqualTo(optionsDTO2);
        optionsDTO2.setId(2L);
        assertThat(optionsDTO1).isNotEqualTo(optionsDTO2);
        optionsDTO1.setId(null);
        assertThat(optionsDTO1).isNotEqualTo(optionsDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(optionsMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(optionsMapper.fromId(null)).isNull();
    }
}
