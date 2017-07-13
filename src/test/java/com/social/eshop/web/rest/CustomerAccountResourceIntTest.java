package com.social.eshop.web.rest;

import com.social.eshop.EshopApp;

import com.social.eshop.domain.CustomerAccount;
import com.social.eshop.repository.CustomerAccountRepository;
import com.social.eshop.service.CustomerAccountService;
import com.social.eshop.repository.search.CustomerAccountSearchRepository;
import com.social.eshop.service.dto.CustomerAccountDTO;
import com.social.eshop.service.mapper.CustomerAccountMapper;
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
 * Test class for the CustomerAccountResource REST controller.
 *
 * @see CustomerAccountResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = EshopApp.class)
public class CustomerAccountResourceIntTest {

    private static final LocalDate DEFAULT_CREATED_AT = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CREATED_AT = LocalDate.now(ZoneId.systemDefault());

    @Autowired
    private CustomerAccountRepository customerAccountRepository;

    @Autowired
    private CustomerAccountMapper customerAccountMapper;

    @Autowired
    private CustomerAccountService customerAccountService;

    @Autowired
    private CustomerAccountSearchRepository customerAccountSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restCustomerAccountMockMvc;

    private CustomerAccount customerAccount;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        CustomerAccountResource customerAccountResource = new CustomerAccountResource(customerAccountService);
        this.restCustomerAccountMockMvc = MockMvcBuilders.standaloneSetup(customerAccountResource)
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
    public static CustomerAccount createEntity(EntityManager em) {
        CustomerAccount customerAccount = new CustomerAccount()
            .createdAt(DEFAULT_CREATED_AT);
        return customerAccount;
    }

    @Before
    public void initTest() {
        customerAccountSearchRepository.deleteAll();
        customerAccount = createEntity(em);
    }

    @Test
    @Transactional
    public void createCustomerAccount() throws Exception {
        int databaseSizeBeforeCreate = customerAccountRepository.findAll().size();

        // Create the CustomerAccount
        CustomerAccountDTO customerAccountDTO = customerAccountMapper.toDto(customerAccount);
        restCustomerAccountMockMvc.perform(post("/api/customer-accounts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(customerAccountDTO)))
            .andExpect(status().isCreated());

        // Validate the CustomerAccount in the database
        List<CustomerAccount> customerAccountList = customerAccountRepository.findAll();
        assertThat(customerAccountList).hasSize(databaseSizeBeforeCreate + 1);
        CustomerAccount testCustomerAccount = customerAccountList.get(customerAccountList.size() - 1);
        assertThat(testCustomerAccount.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);

        // Validate the CustomerAccount in Elasticsearch
        CustomerAccount customerAccountEs = customerAccountSearchRepository.findOne(testCustomerAccount.getId());
        assertThat(customerAccountEs).isEqualToComparingFieldByField(testCustomerAccount);
    }

    @Test
    @Transactional
    public void createCustomerAccountWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = customerAccountRepository.findAll().size();

        // Create the CustomerAccount with an existing ID
        customerAccount.setId(1L);
        CustomerAccountDTO customerAccountDTO = customerAccountMapper.toDto(customerAccount);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCustomerAccountMockMvc.perform(post("/api/customer-accounts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(customerAccountDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<CustomerAccount> customerAccountList = customerAccountRepository.findAll();
        assertThat(customerAccountList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllCustomerAccounts() throws Exception {
        // Initialize the database
        customerAccountRepository.saveAndFlush(customerAccount);

        // Get all the customerAccountList
        restCustomerAccountMockMvc.perform(get("/api/customer-accounts?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(customerAccount.getId().intValue())))
            .andExpect(jsonPath("$.[*].createdAt").value(hasItem(DEFAULT_CREATED_AT.toString())));
    }

    @Test
    @Transactional
    public void getCustomerAccount() throws Exception {
        // Initialize the database
        customerAccountRepository.saveAndFlush(customerAccount);

        // Get the customerAccount
        restCustomerAccountMockMvc.perform(get("/api/customer-accounts/{id}", customerAccount.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(customerAccount.getId().intValue()))
            .andExpect(jsonPath("$.createdAt").value(DEFAULT_CREATED_AT.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingCustomerAccount() throws Exception {
        // Get the customerAccount
        restCustomerAccountMockMvc.perform(get("/api/customer-accounts/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCustomerAccount() throws Exception {
        // Initialize the database
        customerAccountRepository.saveAndFlush(customerAccount);
        customerAccountSearchRepository.save(customerAccount);
        int databaseSizeBeforeUpdate = customerAccountRepository.findAll().size();

        // Update the customerAccount
        CustomerAccount updatedCustomerAccount = customerAccountRepository.findOne(customerAccount.getId());
        updatedCustomerAccount
            .createdAt(UPDATED_CREATED_AT);
        CustomerAccountDTO customerAccountDTO = customerAccountMapper.toDto(updatedCustomerAccount);

        restCustomerAccountMockMvc.perform(put("/api/customer-accounts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(customerAccountDTO)))
            .andExpect(status().isOk());

        // Validate the CustomerAccount in the database
        List<CustomerAccount> customerAccountList = customerAccountRepository.findAll();
        assertThat(customerAccountList).hasSize(databaseSizeBeforeUpdate);
        CustomerAccount testCustomerAccount = customerAccountList.get(customerAccountList.size() - 1);
        assertThat(testCustomerAccount.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);

        // Validate the CustomerAccount in Elasticsearch
        CustomerAccount customerAccountEs = customerAccountSearchRepository.findOne(testCustomerAccount.getId());
        assertThat(customerAccountEs).isEqualToComparingFieldByField(testCustomerAccount);
    }

    @Test
    @Transactional
    public void updateNonExistingCustomerAccount() throws Exception {
        int databaseSizeBeforeUpdate = customerAccountRepository.findAll().size();

        // Create the CustomerAccount
        CustomerAccountDTO customerAccountDTO = customerAccountMapper.toDto(customerAccount);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restCustomerAccountMockMvc.perform(put("/api/customer-accounts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(customerAccountDTO)))
            .andExpect(status().isCreated());

        // Validate the CustomerAccount in the database
        List<CustomerAccount> customerAccountList = customerAccountRepository.findAll();
        assertThat(customerAccountList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteCustomerAccount() throws Exception {
        // Initialize the database
        customerAccountRepository.saveAndFlush(customerAccount);
        customerAccountSearchRepository.save(customerAccount);
        int databaseSizeBeforeDelete = customerAccountRepository.findAll().size();

        // Get the customerAccount
        restCustomerAccountMockMvc.perform(delete("/api/customer-accounts/{id}", customerAccount.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate Elasticsearch is empty
        boolean customerAccountExistsInEs = customerAccountSearchRepository.exists(customerAccount.getId());
        assertThat(customerAccountExistsInEs).isFalse();

        // Validate the database is empty
        List<CustomerAccount> customerAccountList = customerAccountRepository.findAll();
        assertThat(customerAccountList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void searchCustomerAccount() throws Exception {
        // Initialize the database
        customerAccountRepository.saveAndFlush(customerAccount);
        customerAccountSearchRepository.save(customerAccount);

        // Search the customerAccount
        restCustomerAccountMockMvc.perform(get("/api/_search/customer-accounts?query=id:" + customerAccount.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(customerAccount.getId().intValue())))
            .andExpect(jsonPath("$.[*].createdAt").value(hasItem(DEFAULT_CREATED_AT.toString())));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CustomerAccount.class);
        CustomerAccount customerAccount1 = new CustomerAccount();
        customerAccount1.setId(1L);
        CustomerAccount customerAccount2 = new CustomerAccount();
        customerAccount2.setId(customerAccount1.getId());
        assertThat(customerAccount1).isEqualTo(customerAccount2);
        customerAccount2.setId(2L);
        assertThat(customerAccount1).isNotEqualTo(customerAccount2);
        customerAccount1.setId(null);
        assertThat(customerAccount1).isNotEqualTo(customerAccount2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CustomerAccountDTO.class);
        CustomerAccountDTO customerAccountDTO1 = new CustomerAccountDTO();
        customerAccountDTO1.setId(1L);
        CustomerAccountDTO customerAccountDTO2 = new CustomerAccountDTO();
        assertThat(customerAccountDTO1).isNotEqualTo(customerAccountDTO2);
        customerAccountDTO2.setId(customerAccountDTO1.getId());
        assertThat(customerAccountDTO1).isEqualTo(customerAccountDTO2);
        customerAccountDTO2.setId(2L);
        assertThat(customerAccountDTO1).isNotEqualTo(customerAccountDTO2);
        customerAccountDTO1.setId(null);
        assertThat(customerAccountDTO1).isNotEqualTo(customerAccountDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(customerAccountMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(customerAccountMapper.fromId(null)).isNull();
    }
}
