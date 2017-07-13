package com.social.eshop.web.rest;

import com.social.eshop.EshopApp;
import com.social.eshop.domain.Options;
import com.social.eshop.repository.OptionsRepository;
import com.social.eshop.repository.search.OptionsSearchRepository;
import com.social.eshop.service.OptionsService;
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

@RunWith(SpringRunner.class)
@SpringBootTest(classes = EshopApp.class)
public class OptionsResourceTest {

    private static final String DEFAULT_METAL = "AAAAAAAAAA";
    private static final String UPDATED_METAL = "BBBBBBBBBB";

    private static final String DEFAULT_COLOR = "AAAAAAAAAA";
    private static final String UPDATED_COLOR = "BBBBBBBBBB";

    private static final String DEFAULT_STONE = "AAAAAAAAAA";
    private static final String UPDATED_STONE = "BBBBBBBBBB";

    private static final String DEFAULT_MARKING = "AAAAAAAAAA";
    private static final String UPDATED_MARKING = "BBBBBBBBBB";

    private static final Double DEFAULT_WEIGHT = 1D;
    private static final Double UPDATED_WEIGHT = 2D;

    private static final Double DEFAULT_SIZE = 1D;
    private static final Double UPDATED_SIZE = 2D;

    private static final Integer DEFAULT_LENGTH = 1;
    private static final Integer UPDATED_LENGTH = 2;

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

    public static Options createEntity(EntityManager em) {
        Options options = new Options()
            .metal(DEFAULT_METAL)
            .color(DEFAULT_COLOR)
            .stone(DEFAULT_STONE)
            .marking(DEFAULT_MARKING)
            .weight(DEFAULT_WEIGHT)
            .size(DEFAULT_SIZE)
            .length(DEFAULT_LENGTH);
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
        assertThat(testOptions.getMetal()).isEqualTo(DEFAULT_METAL);
        assertThat(testOptions.getColor()).isEqualTo(DEFAULT_COLOR);
        assertThat(testOptions.getStone()).isEqualTo(DEFAULT_STONE);
        assertThat(testOptions.getMarking()).isEqualTo(DEFAULT_MARKING);
        assertThat(testOptions.getWeight()).isEqualTo(DEFAULT_WEIGHT);
        assertThat(testOptions.getSize()).isEqualTo(DEFAULT_SIZE);
        assertThat(testOptions.getLength()).isEqualTo(DEFAULT_LENGTH);

        // Validate the Options in Elasticsearch
        Options optionsEs = optionsSearchRepository.findOne(testOptions.getId());
        assertThat(optionsEs).isEqualToComparingFieldByField(testOptions);
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
            .andExpect(jsonPath("$.[*].metal").value(hasItem(DEFAULT_METAL.toString())))
            .andExpect(jsonPath("$.[*].color").value(hasItem(DEFAULT_COLOR.toString())))
            .andExpect(jsonPath("$.[*].stone").value(hasItem(DEFAULT_STONE.toString())))
            .andExpect(jsonPath("$.[*].marking").value(hasItem(DEFAULT_MARKING.toString())))
            .andExpect(jsonPath("$.[*].weight").value(hasItem(DEFAULT_WEIGHT.doubleValue())))
            .andExpect(jsonPath("$.[*].size").value(hasItem(DEFAULT_SIZE.doubleValue())))
            .andExpect(jsonPath("$.[*].length").value(hasItem(DEFAULT_LENGTH)));
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
            .andExpect(jsonPath("$.metal").value(DEFAULT_METAL.toString()))
            .andExpect(jsonPath("$.color").value(DEFAULT_COLOR.toString()))
            .andExpect(jsonPath("$.stone").value(DEFAULT_STONE.toString()))
            .andExpect(jsonPath("$.marking").value(DEFAULT_MARKING.toString()))
            .andExpect(jsonPath("$.weight").value(DEFAULT_WEIGHT.doubleValue()))
            .andExpect(jsonPath("$.size").value(DEFAULT_SIZE.doubleValue()))
            .andExpect(jsonPath("$.length").value(DEFAULT_LENGTH));
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
            .metal(UPDATED_METAL)
            .color(UPDATED_COLOR)
            .stone(UPDATED_STONE)
            .marking(UPDATED_MARKING)
            .weight(UPDATED_WEIGHT)
            .size(UPDATED_SIZE)
            .length(UPDATED_LENGTH);
        OptionsDTO optionsDTO = optionsMapper.toDto(updatedOptions);

        restOptionsMockMvc.perform(put("/api/options")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(optionsDTO)))
            .andExpect(status().isOk());

        // Validate the Options in the database
        List<Options> optionsList = optionsRepository.findAll();
        assertThat(optionsList).hasSize(databaseSizeBeforeUpdate);
        Options testOptions = optionsList.get(optionsList.size() - 1);
        assertThat(testOptions.getMetal()).isEqualTo(UPDATED_METAL);
        assertThat(testOptions.getColor()).isEqualTo(UPDATED_COLOR);
        assertThat(testOptions.getStone()).isEqualTo(UPDATED_STONE);
        assertThat(testOptions.getMarking()).isEqualTo(UPDATED_MARKING);
        assertThat(testOptions.getWeight()).isEqualTo(UPDATED_WEIGHT);
        assertThat(testOptions.getSize()).isEqualTo(UPDATED_SIZE);
        assertThat(testOptions.getLength()).isEqualTo(UPDATED_LENGTH);

        // Validate the Options in Elasticsearch
        Options optionsEs = optionsSearchRepository.findOne(testOptions.getId());
        assertThat(optionsEs).isEqualToComparingFieldByField(testOptions);
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
            .andExpect(jsonPath("$.[*].metal").value(hasItem(DEFAULT_METAL.toString())))
            .andExpect(jsonPath("$.[*].color").value(hasItem(DEFAULT_COLOR.toString())))
            .andExpect(jsonPath("$.[*].stone").value(hasItem(DEFAULT_STONE.toString())))
            .andExpect(jsonPath("$.[*].marking").value(hasItem(DEFAULT_MARKING.toString())))
            .andExpect(jsonPath("$.[*].weight").value(hasItem(DEFAULT_WEIGHT.doubleValue())))
            .andExpect(jsonPath("$.[*].size").value(hasItem(DEFAULT_SIZE.doubleValue())))
            .andExpect(jsonPath("$.[*].length").value(hasItem(DEFAULT_LENGTH)));
    }
}
