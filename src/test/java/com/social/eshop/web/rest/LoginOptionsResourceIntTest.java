package com.social.eshop.web.rest;

import com.social.eshop.EshopApp;

import com.social.eshop.domain.LoginOptions;
import com.social.eshop.repository.LoginOptionsRepository;
import com.social.eshop.service.LoginOptionsService;
import com.social.eshop.repository.search.LoginOptionsSearchRepository;
import com.social.eshop.service.dto.LoginOptionsDTO;
import com.social.eshop.service.mapper.LoginOptionsMapper;
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
 * Test class for the LoginOptionsResource REST controller.
 *
 * @see LoginOptionsResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = EshopApp.class)
public class LoginOptionsResourceIntTest {

    private static final String DEFAULT_LOGIN = "AAAAAAAAAA";
    private static final String UPDATED_LOGIN = "BBBBBBBBBB";

    private static final String DEFAULT_PASSWORD = "AAAAAAAAAA";
    private static final String UPDATED_PASSWORD = "BBBBBBBBBB";

    @Autowired
    private LoginOptionsRepository loginOptionsRepository;

    @Autowired
    private LoginOptionsMapper loginOptionsMapper;

    @Autowired
    private LoginOptionsService loginOptionsService;

    @Autowired
    private LoginOptionsSearchRepository loginOptionsSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restLoginOptionsMockMvc;

    private LoginOptions loginOptions;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        LoginOptionsResource loginOptionsResource = new LoginOptionsResource(loginOptionsService);
        this.restLoginOptionsMockMvc = MockMvcBuilders.standaloneSetup(loginOptionsResource)
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
    public static LoginOptions createEntity(EntityManager em) {
        LoginOptions loginOptions = new LoginOptions()
            .login(DEFAULT_LOGIN)
            .password(DEFAULT_PASSWORD);
        return loginOptions;
    }

    @Before
    public void initTest() {
        loginOptionsSearchRepository.deleteAll();
        loginOptions = createEntity(em);
    }

    @Test
    @Transactional
    public void createLoginOptions() throws Exception {
        int databaseSizeBeforeCreate = loginOptionsRepository.findAll().size();

        // Create the LoginOptions
        LoginOptionsDTO loginOptionsDTO = loginOptionsMapper.toDto(loginOptions);
        restLoginOptionsMockMvc.perform(post("/api/login-options")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(loginOptionsDTO)))
            .andExpect(status().isCreated());

        // Validate the LoginOptions in the database
        List<LoginOptions> loginOptionsList = loginOptionsRepository.findAll();
        assertThat(loginOptionsList).hasSize(databaseSizeBeforeCreate + 1);
        LoginOptions testLoginOptions = loginOptionsList.get(loginOptionsList.size() - 1);
        assertThat(testLoginOptions.getLogin()).isEqualTo(DEFAULT_LOGIN);
        assertThat(testLoginOptions.getPassword()).isEqualTo(DEFAULT_PASSWORD);

        // Validate the LoginOptions in Elasticsearch
        LoginOptions loginOptionsEs = loginOptionsSearchRepository.findOne(testLoginOptions.getId());
        assertThat(loginOptionsEs).isEqualToComparingFieldByField(testLoginOptions);
    }

    @Test
    @Transactional
    public void createLoginOptionsWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = loginOptionsRepository.findAll().size();

        // Create the LoginOptions with an existing ID
        loginOptions.setId(1L);
        LoginOptionsDTO loginOptionsDTO = loginOptionsMapper.toDto(loginOptions);

        // An entity with an existing ID cannot be created, so this API call must fail
        restLoginOptionsMockMvc.perform(post("/api/login-options")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(loginOptionsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<LoginOptions> loginOptionsList = loginOptionsRepository.findAll();
        assertThat(loginOptionsList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkLoginIsRequired() throws Exception {
        int databaseSizeBeforeTest = loginOptionsRepository.findAll().size();
        // set the field null
        loginOptions.setLogin(null);

        // Create the LoginOptions, which fails.
        LoginOptionsDTO loginOptionsDTO = loginOptionsMapper.toDto(loginOptions);

        restLoginOptionsMockMvc.perform(post("/api/login-options")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(loginOptionsDTO)))
            .andExpect(status().isBadRequest());

        List<LoginOptions> loginOptionsList = loginOptionsRepository.findAll();
        assertThat(loginOptionsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPasswordIsRequired() throws Exception {
        int databaseSizeBeforeTest = loginOptionsRepository.findAll().size();
        // set the field null
        loginOptions.setPassword(null);

        // Create the LoginOptions, which fails.
        LoginOptionsDTO loginOptionsDTO = loginOptionsMapper.toDto(loginOptions);

        restLoginOptionsMockMvc.perform(post("/api/login-options")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(loginOptionsDTO)))
            .andExpect(status().isBadRequest());

        List<LoginOptions> loginOptionsList = loginOptionsRepository.findAll();
        assertThat(loginOptionsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllLoginOptions() throws Exception {
        // Initialize the database
        loginOptionsRepository.saveAndFlush(loginOptions);

        // Get all the loginOptionsList
        restLoginOptionsMockMvc.perform(get("/api/login-options?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(loginOptions.getId().intValue())))
            .andExpect(jsonPath("$.[*].login").value(hasItem(DEFAULT_LOGIN.toString())))
            .andExpect(jsonPath("$.[*].password").value(hasItem(DEFAULT_PASSWORD.toString())));
    }

    @Test
    @Transactional
    public void getLoginOptions() throws Exception {
        // Initialize the database
        loginOptionsRepository.saveAndFlush(loginOptions);

        // Get the loginOptions
        restLoginOptionsMockMvc.perform(get("/api/login-options/{id}", loginOptions.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(loginOptions.getId().intValue()))
            .andExpect(jsonPath("$.login").value(DEFAULT_LOGIN.toString()))
            .andExpect(jsonPath("$.password").value(DEFAULT_PASSWORD.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingLoginOptions() throws Exception {
        // Get the loginOptions
        restLoginOptionsMockMvc.perform(get("/api/login-options/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateLoginOptions() throws Exception {
        // Initialize the database
        loginOptionsRepository.saveAndFlush(loginOptions);
        loginOptionsSearchRepository.save(loginOptions);
        int databaseSizeBeforeUpdate = loginOptionsRepository.findAll().size();

        // Update the loginOptions
        LoginOptions updatedLoginOptions = loginOptionsRepository.findOne(loginOptions.getId());
        updatedLoginOptions
            .login(UPDATED_LOGIN)
            .password(UPDATED_PASSWORD);
        LoginOptionsDTO loginOptionsDTO = loginOptionsMapper.toDto(updatedLoginOptions);

        restLoginOptionsMockMvc.perform(put("/api/login-options")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(loginOptionsDTO)))
            .andExpect(status().isOk());

        // Validate the LoginOptions in the database
        List<LoginOptions> loginOptionsList = loginOptionsRepository.findAll();
        assertThat(loginOptionsList).hasSize(databaseSizeBeforeUpdate);
        LoginOptions testLoginOptions = loginOptionsList.get(loginOptionsList.size() - 1);
        assertThat(testLoginOptions.getLogin()).isEqualTo(UPDATED_LOGIN);
        assertThat(testLoginOptions.getPassword()).isEqualTo(UPDATED_PASSWORD);

        // Validate the LoginOptions in Elasticsearch
        LoginOptions loginOptionsEs = loginOptionsSearchRepository.findOne(testLoginOptions.getId());
        assertThat(loginOptionsEs).isEqualToComparingFieldByField(testLoginOptions);
    }

    @Test
    @Transactional
    public void updateNonExistingLoginOptions() throws Exception {
        int databaseSizeBeforeUpdate = loginOptionsRepository.findAll().size();

        // Create the LoginOptions
        LoginOptionsDTO loginOptionsDTO = loginOptionsMapper.toDto(loginOptions);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restLoginOptionsMockMvc.perform(put("/api/login-options")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(loginOptionsDTO)))
            .andExpect(status().isCreated());

        // Validate the LoginOptions in the database
        List<LoginOptions> loginOptionsList = loginOptionsRepository.findAll();
        assertThat(loginOptionsList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteLoginOptions() throws Exception {
        // Initialize the database
        loginOptionsRepository.saveAndFlush(loginOptions);
        loginOptionsSearchRepository.save(loginOptions);
        int databaseSizeBeforeDelete = loginOptionsRepository.findAll().size();

        // Get the loginOptions
        restLoginOptionsMockMvc.perform(delete("/api/login-options/{id}", loginOptions.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate Elasticsearch is empty
        boolean loginOptionsExistsInEs = loginOptionsSearchRepository.exists(loginOptions.getId());
        assertThat(loginOptionsExistsInEs).isFalse();

        // Validate the database is empty
        List<LoginOptions> loginOptionsList = loginOptionsRepository.findAll();
        assertThat(loginOptionsList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void searchLoginOptions() throws Exception {
        // Initialize the database
        loginOptionsRepository.saveAndFlush(loginOptions);
        loginOptionsSearchRepository.save(loginOptions);

        // Search the loginOptions
        restLoginOptionsMockMvc.perform(get("/api/_search/login-options?query=id:" + loginOptions.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(loginOptions.getId().intValue())))
            .andExpect(jsonPath("$.[*].login").value(hasItem(DEFAULT_LOGIN.toString())))
            .andExpect(jsonPath("$.[*].password").value(hasItem(DEFAULT_PASSWORD.toString())));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(LoginOptions.class);
        LoginOptions loginOptions1 = new LoginOptions();
        loginOptions1.setId(1L);
        LoginOptions loginOptions2 = new LoginOptions();
        loginOptions2.setId(loginOptions1.getId());
        assertThat(loginOptions1).isEqualTo(loginOptions2);
        loginOptions2.setId(2L);
        assertThat(loginOptions1).isNotEqualTo(loginOptions2);
        loginOptions1.setId(null);
        assertThat(loginOptions1).isNotEqualTo(loginOptions2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(LoginOptionsDTO.class);
        LoginOptionsDTO loginOptionsDTO1 = new LoginOptionsDTO();
        loginOptionsDTO1.setId(1L);
        LoginOptionsDTO loginOptionsDTO2 = new LoginOptionsDTO();
        assertThat(loginOptionsDTO1).isNotEqualTo(loginOptionsDTO2);
        loginOptionsDTO2.setId(loginOptionsDTO1.getId());
        assertThat(loginOptionsDTO1).isEqualTo(loginOptionsDTO2);
        loginOptionsDTO2.setId(2L);
        assertThat(loginOptionsDTO1).isNotEqualTo(loginOptionsDTO2);
        loginOptionsDTO1.setId(null);
        assertThat(loginOptionsDTO1).isNotEqualTo(loginOptionsDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(loginOptionsMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(loginOptionsMapper.fromId(null)).isNull();
    }
}
