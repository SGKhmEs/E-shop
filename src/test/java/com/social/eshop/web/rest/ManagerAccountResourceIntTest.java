package com.social.eshop.web.rest;

import com.social.eshop.EshopApp;

import com.social.eshop.domain.ManagerAccount;
import com.social.eshop.repository.ManagerAccountRepository;
import com.social.eshop.service.ManagerAccountService;
import com.social.eshop.repository.search.ManagerAccountSearchRepository;
import com.social.eshop.service.dto.ManagerAccountDTO;
import com.social.eshop.service.mapper.ManagerAccountMapper;
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
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the ManagerAccountResource REST controller.
 *
 * @see ManagerAccountResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = EshopApp.class)
public class ManagerAccountResourceIntTest {

    private static final LocalDate DEFAULT_CREATED_AT = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CREATED_AT = LocalDate.now(ZoneId.systemDefault());

    @Autowired
    private ManagerAccountRepository managerAccountRepository;

    @Autowired
    private ManagerAccountMapper managerAccountMapper;

    @Autowired
    private ManagerAccountService managerAccountService;

    @Autowired
    private ManagerAccountSearchRepository managerAccountSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restManagerAccountMockMvc;

    private ManagerAccount managerAccount;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        ManagerAccountResource managerAccountResource = new ManagerAccountResource(managerAccountService);
        this.restManagerAccountMockMvc = MockMvcBuilders.standaloneSetup(managerAccountResource)
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
    public static ManagerAccount createEntity(EntityManager em) {
        ManagerAccount managerAccount = new ManagerAccount()
            .createdAt(DEFAULT_CREATED_AT);
        return managerAccount;
    }

    @Before
    public void initTest() {
        managerAccountSearchRepository.deleteAll();
        managerAccount = createEntity(em);
    }

    @Test
    @Transactional
    public void createManagerAccount() throws Exception {
        int databaseSizeBeforeCreate = managerAccountRepository.findAll().size();

        // Create the ManagerAccount
        ManagerAccountDTO managerAccountDTO = managerAccountMapper.toDto(managerAccount);
        restManagerAccountMockMvc.perform(post("/api/manager-accounts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(managerAccountDTO)))
            .andExpect(status().isCreated());

        // Validate the ManagerAccount in the database
        List<ManagerAccount> managerAccountList = managerAccountRepository.findAll();
        assertThat(managerAccountList).hasSize(databaseSizeBeforeCreate + 1);
        ManagerAccount testManagerAccount = managerAccountList.get(managerAccountList.size() - 1);
        assertThat(testManagerAccount.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);

        // Validate the ManagerAccount in Elasticsearch
        ManagerAccount managerAccountEs = managerAccountSearchRepository.findOne(testManagerAccount.getId());
        assertThat(managerAccountEs).isEqualToComparingFieldByField(testManagerAccount);
    }

    @Test
    @Transactional
    public void createManagerAccountWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = managerAccountRepository.findAll().size();

        // Create the ManagerAccount with an existing ID
        managerAccount.setId(1L);
        ManagerAccountDTO managerAccountDTO = managerAccountMapper.toDto(managerAccount);

        // An entity with an existing ID cannot be created, so this API call must fail
        restManagerAccountMockMvc.perform(post("/api/manager-accounts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(managerAccountDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<ManagerAccount> managerAccountList = managerAccountRepository.findAll();
        assertThat(managerAccountList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllManagerAccounts() throws Exception {
        // Initialize the database
        managerAccountRepository.saveAndFlush(managerAccount);

        // Get all the managerAccountList
        restManagerAccountMockMvc.perform(get("/api/manager-accounts?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(managerAccount.getId().intValue())))
            .andExpect(jsonPath("$.[*].createdAt").value(hasItem(DEFAULT_CREATED_AT.toString())));
    }

    @Test
    @Transactional
    public void getManagerAccount() throws Exception {
        // Initialize the database
        managerAccountRepository.saveAndFlush(managerAccount);

        // Get the managerAccount
        restManagerAccountMockMvc.perform(get("/api/manager-accounts/{id}", managerAccount.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(managerAccount.getId().intValue()))
            .andExpect(jsonPath("$.createdAt").value(DEFAULT_CREATED_AT.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingManagerAccount() throws Exception {
        // Get the managerAccount
        restManagerAccountMockMvc.perform(get("/api/manager-accounts/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateManagerAccount() throws Exception {
        // Initialize the database
        managerAccountRepository.saveAndFlush(managerAccount);
        managerAccountSearchRepository.save(managerAccount);
        int databaseSizeBeforeUpdate = managerAccountRepository.findAll().size();

        // Update the managerAccount
        ManagerAccount updatedManagerAccount = managerAccountRepository.findOne(managerAccount.getId());
        updatedManagerAccount
            .createdAt(UPDATED_CREATED_AT);
        ManagerAccountDTO managerAccountDTO = managerAccountMapper.toDto(updatedManagerAccount);

        restManagerAccountMockMvc.perform(put("/api/manager-accounts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(managerAccountDTO)))
            .andExpect(status().isOk());

        // Validate the ManagerAccount in the database
        List<ManagerAccount> managerAccountList = managerAccountRepository.findAll();
        assertThat(managerAccountList).hasSize(databaseSizeBeforeUpdate);
        ManagerAccount testManagerAccount = managerAccountList.get(managerAccountList.size() - 1);
        assertThat(testManagerAccount.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);

        // Validate the ManagerAccount in Elasticsearch
        ManagerAccount managerAccountEs = managerAccountSearchRepository.findOne(testManagerAccount.getId());
        assertThat(managerAccountEs).isEqualToComparingFieldByField(testManagerAccount);
    }

    @Test
    @Transactional
    public void updateNonExistingManagerAccount() throws Exception {
        int databaseSizeBeforeUpdate = managerAccountRepository.findAll().size();

        // Create the ManagerAccount
        ManagerAccountDTO managerAccountDTO = managerAccountMapper.toDto(managerAccount);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restManagerAccountMockMvc.perform(put("/api/manager-accounts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(managerAccountDTO)))
            .andExpect(status().isCreated());

        // Validate the ManagerAccount in the database
        List<ManagerAccount> managerAccountList = managerAccountRepository.findAll();
        assertThat(managerAccountList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteManagerAccount() throws Exception {
        // Initialize the database
        managerAccountRepository.saveAndFlush(managerAccount);
        managerAccountSearchRepository.save(managerAccount);
        int databaseSizeBeforeDelete = managerAccountRepository.findAll().size();

        // Get the managerAccount
        restManagerAccountMockMvc.perform(delete("/api/manager-accounts/{id}", managerAccount.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate Elasticsearch is empty
        boolean managerAccountExistsInEs = managerAccountSearchRepository.exists(managerAccount.getId());
        assertThat(managerAccountExistsInEs).isFalse();

        // Validate the database is empty
        List<ManagerAccount> managerAccountList = managerAccountRepository.findAll();
        assertThat(managerAccountList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void searchManagerAccount() throws Exception {
        // Initialize the database
        managerAccountRepository.saveAndFlush(managerAccount);
        managerAccountSearchRepository.save(managerAccount);

        // Search the managerAccount
        restManagerAccountMockMvc.perform(get("/api/_search/manager-accounts?query=id:" + managerAccount.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(managerAccount.getId().intValue())))
            .andExpect(jsonPath("$.[*].createdAt").value(hasItem(DEFAULT_CREATED_AT.toString())));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ManagerAccount.class);
        ManagerAccount managerAccount1 = new ManagerAccount();
        managerAccount1.setId(1L);
        ManagerAccount managerAccount2 = new ManagerAccount();
        managerAccount2.setId(managerAccount1.getId());
        assertThat(managerAccount1).isEqualTo(managerAccount2);
        managerAccount2.setId(2L);
        assertThat(managerAccount1).isNotEqualTo(managerAccount2);
        managerAccount1.setId(null);
        assertThat(managerAccount1).isNotEqualTo(managerAccount2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ManagerAccountDTO.class);
        ManagerAccountDTO managerAccountDTO1 = new ManagerAccountDTO();
        managerAccountDTO1.setId(1L);
        ManagerAccountDTO managerAccountDTO2 = new ManagerAccountDTO();
        assertThat(managerAccountDTO1).isNotEqualTo(managerAccountDTO2);
        managerAccountDTO2.setId(managerAccountDTO1.getId());
        assertThat(managerAccountDTO1).isEqualTo(managerAccountDTO2);
        managerAccountDTO2.setId(2L);
        assertThat(managerAccountDTO1).isNotEqualTo(managerAccountDTO2);
        managerAccountDTO1.setId(null);
        assertThat(managerAccountDTO1).isNotEqualTo(managerAccountDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(managerAccountMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(managerAccountMapper.fromId(null)).isNull();
    }
}
