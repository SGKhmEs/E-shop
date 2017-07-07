package com.social.eshop.web.rest;

import com.social.eshop.EshopApp;

import com.social.eshop.domain.Confirm;
import com.social.eshop.repository.ConfirmRepository;
import com.social.eshop.service.ConfirmService;
import com.social.eshop.repository.search.ConfirmSearchRepository;
import com.social.eshop.service.dto.ConfirmDTO;
import com.social.eshop.service.mapper.ConfirmMapper;
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

import com.social.eshop.domain.enumeration.Payment;
/**
 * Test class for the ConfirmResource REST controller.
 *
 * @see ConfirmResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = EshopApp.class)
public class ConfirmResourceIntTest {

    private static final Payment DEFAULT_PAY = Payment.CASH;
    private static final Payment UPDATED_PAY = Payment.CREDITCARD;

    @Autowired
    private ConfirmRepository confirmRepository;

    @Autowired
    private ConfirmMapper confirmMapper;

    @Autowired
    private ConfirmService confirmService;

    @Autowired
    private ConfirmSearchRepository confirmSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restConfirmMockMvc;

    private Confirm confirm;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        ConfirmResource confirmResource = new ConfirmResource(confirmService);
        this.restConfirmMockMvc = MockMvcBuilders.standaloneSetup(confirmResource)
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
    public static Confirm createEntity(EntityManager em) {
        Confirm confirm = new Confirm()
            .pay(DEFAULT_PAY);
        return confirm;
    }

    @Before
    public void initTest() {
        confirmSearchRepository.deleteAll();
        confirm = createEntity(em);
    }

    @Test
    @Transactional
    public void createConfirm() throws Exception {
        int databaseSizeBeforeCreate = confirmRepository.findAll().size();

        // Create the Confirm
        ConfirmDTO confirmDTO = confirmMapper.toDto(confirm);
        restConfirmMockMvc.perform(post("/api/confirms")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(confirmDTO)))
            .andExpect(status().isCreated());

        // Validate the Confirm in the database
        List<Confirm> confirmList = confirmRepository.findAll();
        assertThat(confirmList).hasSize(databaseSizeBeforeCreate + 1);
        Confirm testConfirm = confirmList.get(confirmList.size() - 1);
        assertThat(testConfirm.getPay()).isEqualTo(DEFAULT_PAY);

        // Validate the Confirm in Elasticsearch
        Confirm confirmEs = confirmSearchRepository.findOne(testConfirm.getId());
        assertThat(confirmEs).isEqualToComparingFieldByField(testConfirm);
    }

    @Test
    @Transactional
    public void createConfirmWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = confirmRepository.findAll().size();

        // Create the Confirm with an existing ID
        confirm.setId(1L);
        ConfirmDTO confirmDTO = confirmMapper.toDto(confirm);

        // An entity with an existing ID cannot be created, so this API call must fail
        restConfirmMockMvc.perform(post("/api/confirms")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(confirmDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Confirm> confirmList = confirmRepository.findAll();
        assertThat(confirmList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllConfirms() throws Exception {
        // Initialize the database
        confirmRepository.saveAndFlush(confirm);

        // Get all the confirmList
        restConfirmMockMvc.perform(get("/api/confirms?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(confirm.getId().intValue())))
            .andExpect(jsonPath("$.[*].pay").value(hasItem(DEFAULT_PAY.toString())));
    }

    @Test
    @Transactional
    public void getConfirm() throws Exception {
        // Initialize the database
        confirmRepository.saveAndFlush(confirm);

        // Get the confirm
        restConfirmMockMvc.perform(get("/api/confirms/{id}", confirm.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(confirm.getId().intValue()))
            .andExpect(jsonPath("$.pay").value(DEFAULT_PAY.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingConfirm() throws Exception {
        // Get the confirm
        restConfirmMockMvc.perform(get("/api/confirms/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateConfirm() throws Exception {
        // Initialize the database
        confirmRepository.saveAndFlush(confirm);
        confirmSearchRepository.save(confirm);
        int databaseSizeBeforeUpdate = confirmRepository.findAll().size();

        // Update the confirm
        Confirm updatedConfirm = confirmRepository.findOne(confirm.getId());
        updatedConfirm
            .pay(UPDATED_PAY);
        ConfirmDTO confirmDTO = confirmMapper.toDto(updatedConfirm);

        restConfirmMockMvc.perform(put("/api/confirms")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(confirmDTO)))
            .andExpect(status().isOk());

        // Validate the Confirm in the database
        List<Confirm> confirmList = confirmRepository.findAll();
        assertThat(confirmList).hasSize(databaseSizeBeforeUpdate);
        Confirm testConfirm = confirmList.get(confirmList.size() - 1);
        assertThat(testConfirm.getPay()).isEqualTo(UPDATED_PAY);

        // Validate the Confirm in Elasticsearch
        Confirm confirmEs = confirmSearchRepository.findOne(testConfirm.getId());
        assertThat(confirmEs).isEqualToComparingFieldByField(testConfirm);
    }

    @Test
    @Transactional
    public void updateNonExistingConfirm() throws Exception {
        int databaseSizeBeforeUpdate = confirmRepository.findAll().size();

        // Create the Confirm
        ConfirmDTO confirmDTO = confirmMapper.toDto(confirm);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restConfirmMockMvc.perform(put("/api/confirms")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(confirmDTO)))
            .andExpect(status().isCreated());

        // Validate the Confirm in the database
        List<Confirm> confirmList = confirmRepository.findAll();
        assertThat(confirmList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteConfirm() throws Exception {
        // Initialize the database
        confirmRepository.saveAndFlush(confirm);
        confirmSearchRepository.save(confirm);
        int databaseSizeBeforeDelete = confirmRepository.findAll().size();

        // Get the confirm
        restConfirmMockMvc.perform(delete("/api/confirms/{id}", confirm.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate Elasticsearch is empty
        boolean confirmExistsInEs = confirmSearchRepository.exists(confirm.getId());
        assertThat(confirmExistsInEs).isFalse();

        // Validate the database is empty
        List<Confirm> confirmList = confirmRepository.findAll();
        assertThat(confirmList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void searchConfirm() throws Exception {
        // Initialize the database
        confirmRepository.saveAndFlush(confirm);
        confirmSearchRepository.save(confirm);

        // Search the confirm
        restConfirmMockMvc.perform(get("/api/_search/confirms?query=id:" + confirm.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(confirm.getId().intValue())))
            .andExpect(jsonPath("$.[*].pay").value(hasItem(DEFAULT_PAY.toString())));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Confirm.class);
        Confirm confirm1 = new Confirm();
        confirm1.setId(1L);
        Confirm confirm2 = new Confirm();
        confirm2.setId(confirm1.getId());
        assertThat(confirm1).isEqualTo(confirm2);
        confirm2.setId(2L);
        assertThat(confirm1).isNotEqualTo(confirm2);
        confirm1.setId(null);
        assertThat(confirm1).isNotEqualTo(confirm2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ConfirmDTO.class);
        ConfirmDTO confirmDTO1 = new ConfirmDTO();
        confirmDTO1.setId(1L);
        ConfirmDTO confirmDTO2 = new ConfirmDTO();
        assertThat(confirmDTO1).isNotEqualTo(confirmDTO2);
        confirmDTO2.setId(confirmDTO1.getId());
        assertThat(confirmDTO1).isEqualTo(confirmDTO2);
        confirmDTO2.setId(2L);
        assertThat(confirmDTO1).isNotEqualTo(confirmDTO2);
        confirmDTO1.setId(null);
        assertThat(confirmDTO1).isNotEqualTo(confirmDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(confirmMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(confirmMapper.fromId(null)).isNull();
    }
}
