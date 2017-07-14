package com.social.eshop.web.rest;

import com.social.eshop.EshopApp;
import com.social.eshop.domain.Producers;
import com.social.eshop.repository.ProducersRepository;
import com.social.eshop.repository.search.ProducersSearchRepository;
import com.social.eshop.service.ProducersService;
import com.social.eshop.service.dto.ProducersDTO;
import com.social.eshop.service.mapper.ProducersMapper;
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
public class ProducersResourceTest {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_COUNTRY = "AAAAAAAAAA";
    private static final String UPDATED_COUNTRY = "BBBBBBBBBB";

    @Autowired
    private ProducersRepository producersRepository;

    @Autowired
    private ProducersMapper producersMapper;

    @Autowired
    private ProducersService producersService;

    @Autowired
    private ProducersSearchRepository producersSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restProducersMockMvc;

    private Producers producers;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        ProducersResource producersResource = new ProducersResource(producersService);
        this.restProducersMockMvc = MockMvcBuilders.standaloneSetup(producersResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    public static Producers createEntity(EntityManager em) {
        Producers producers = new Producers()
            .name(DEFAULT_NAME)
            .country(DEFAULT_COUNTRY);
        return producers;
    }

    @Before
    public void initTest() {
        producersSearchRepository.deleteAll();
        producers = createEntity(em);
    }

    @Test
    @Transactional
    public void createProducers() throws Exception {
        int databaseSizeBeforeCreate = producersRepository.findAll().size();

        // Create the Producers
        ProducersDTO producersDTO = producersMapper.toDto(producers);
        restProducersMockMvc.perform(post("/api/producers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(producersDTO)))
            .andExpect(status().isCreated());

        // Validate the Producers in the database
        List<Producers> producersList = producersRepository.findAll();
        assertThat(producersList).hasSize(databaseSizeBeforeCreate + 1);
        Producers testProducers = producersList.get(producersList.size() - 1);
        assertThat(testProducers.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testProducers.getCountry()).isEqualTo(DEFAULT_COUNTRY);

        // Validate the Producers in Elasticsearch
        Producers producersEs = producersSearchRepository.findOne(testProducers.getId());
        assertThat(producersEs).isEqualToComparingFieldByField(testProducers);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = producersRepository.findAll().size();
        // set the field null
        producers.setName(null);

        // Create the Producers, which fails.
        ProducersDTO producersDTO = producersMapper.toDto(producers);

        restProducersMockMvc.perform(post("/api/producers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(producersDTO)))
            .andExpect(status().isBadRequest());

        List<Producers> producersList = producersRepository.findAll();
        assertThat(producersList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCountryIsRequired() throws Exception {
        int databaseSizeBeforeTest = producersRepository.findAll().size();
        // set the field null
        producers.setCountry(null);

        // Create the Producers, which fails.
        ProducersDTO producersDTO = producersMapper.toDto(producers);

        restProducersMockMvc.perform(post("/api/producers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(producersDTO)))
            .andExpect(status().isBadRequest());

        List<Producers> producersList = producersRepository.findAll();
        assertThat(producersList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllProducers() throws Exception {
        // Initialize the database
        producersRepository.saveAndFlush(producers);

        // Get all the producersList
        restProducersMockMvc.perform(get("/api/producers?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(producers.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].country").value(hasItem(DEFAULT_COUNTRY.toString())));
    }

    @Test
    @Transactional
    public void getProducers() throws Exception {
        // Initialize the database
        producersRepository.saveAndFlush(producers);

        // Get the producers
        restProducersMockMvc.perform(get("/api/producers/{id}", producers.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(producers.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.country").value(DEFAULT_COUNTRY.toString()));
    }

    @Test
    @Transactional
    public void updateProducers() throws Exception {
        // Initialize the database
        producersRepository.saveAndFlush(producers);
        producersSearchRepository.save(producers);
        int databaseSizeBeforeUpdate = producersRepository.findAll().size();

        // Update the producers
        Producers updatedProducers = producersRepository.findOne(producers.getId());
        updatedProducers
            .name(UPDATED_NAME)
            .country(UPDATED_COUNTRY);
        ProducersDTO producersDTO = producersMapper.toDto(updatedProducers);

        restProducersMockMvc.perform(put("/api/producers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(producersDTO)))
            .andExpect(status().isOk());

        // Validate the Producers in the database
        List<Producers> producersList = producersRepository.findAll();
        assertThat(producersList).hasSize(databaseSizeBeforeUpdate);
        Producers testProducers = producersList.get(producersList.size() - 1);
        assertThat(testProducers.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testProducers.getCountry()).isEqualTo(UPDATED_COUNTRY);

        // Validate the Producers in Elasticsearch
        Producers producersEs = producersSearchRepository.findOne(testProducers.getId());
        assertThat(producersEs).isEqualToComparingFieldByField(testProducers);
    }

    @Test
    @Transactional
    public void deleteProducers() throws Exception {
        // Initialize the database
        producersRepository.saveAndFlush(producers);
        producersSearchRepository.save(producers);
        int databaseSizeBeforeDelete = producersRepository.findAll().size();

        // Get the producers
        restProducersMockMvc.perform(delete("/api/producers/{id}", producers.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate Elasticsearch is empty
        boolean producersExistsInEs = producersSearchRepository.exists(producers.getId());
        assertThat(producersExistsInEs).isFalse();

        // Validate the database is empty
        List<Producers> producersList = producersRepository.findAll();
        assertThat(producersList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void searchProducers() throws Exception {
        // Initialize the database
        producersRepository.saveAndFlush(producers);
        producersSearchRepository.save(producers);

        // Search the producers
        restProducersMockMvc.perform(get("/api/_search/producers?query=id:" + producers.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(producers.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].country").value(hasItem(DEFAULT_COUNTRY.toString())));
    }
}
