package com.social.eshop.web.rest;

import com.social.eshop.EshopApp;

import com.social.eshop.domain.SessionId;
import com.social.eshop.repository.SessionIdRepository;
import com.social.eshop.service.SessionIdService;
import com.social.eshop.repository.search.SessionIdSearchRepository;
import com.social.eshop.service.dto.SessionIdDTO;
import com.social.eshop.service.mapper.SessionIdMapper;
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
 * Test class for the SessionIdResource REST controller.
 *
 * @see SessionIdResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = EshopApp.class)
public class SessionIdResourceIntTest {

    @Autowired
    private SessionIdRepository sessionIdRepository;

    @Autowired
    private SessionIdMapper sessionIdMapper;

    @Autowired
    private SessionIdService sessionIdService;

    @Autowired
    private SessionIdSearchRepository sessionIdSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restSessionIdMockMvc;

    private SessionId sessionId;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        SessionIdResource sessionIdResource = new SessionIdResource(sessionIdService);
        this.restSessionIdMockMvc = MockMvcBuilders.standaloneSetup(sessionIdResource)
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
    public static SessionId createEntity(EntityManager em) {
        SessionId sessionId = new SessionId();
        return sessionId;
    }

    @Before
    public void initTest() {
        sessionIdSearchRepository.deleteAll();
        sessionId = createEntity(em);
    }

    @Test
    @Transactional
    public void createSessionId() throws Exception {
        int databaseSizeBeforeCreate = sessionIdRepository.findAll().size();

        // Create the SessionId
        SessionIdDTO sessionIdDTO = sessionIdMapper.toDto(sessionId);
        restSessionIdMockMvc.perform(post("/api/session-ids")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sessionIdDTO)))
            .andExpect(status().isCreated());

        // Validate the SessionId in the database
        List<SessionId> sessionIdList = sessionIdRepository.findAll();
        assertThat(sessionIdList).hasSize(databaseSizeBeforeCreate + 1);
        SessionId testSessionId = sessionIdList.get(sessionIdList.size() - 1);

        // Validate the SessionId in Elasticsearch
        SessionId sessionIdEs = sessionIdSearchRepository.findOne(testSessionId.getId());
        assertThat(sessionIdEs).isEqualToComparingFieldByField(testSessionId);
    }

    @Test
    @Transactional
    public void createSessionIdWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = sessionIdRepository.findAll().size();

        // Create the SessionId with an existing ID
        sessionId.setId(1L);
        SessionIdDTO sessionIdDTO = sessionIdMapper.toDto(sessionId);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSessionIdMockMvc.perform(post("/api/session-ids")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sessionIdDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<SessionId> sessionIdList = sessionIdRepository.findAll();
        assertThat(sessionIdList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllSessionIds() throws Exception {
        // Initialize the database
        sessionIdRepository.saveAndFlush(sessionId);

        // Get all the sessionIdList
        restSessionIdMockMvc.perform(get("/api/session-ids?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(sessionId.getId().intValue())));
    }

    @Test
    @Transactional
    public void getSessionId() throws Exception {
        // Initialize the database
        sessionIdRepository.saveAndFlush(sessionId);

        // Get the sessionId
        restSessionIdMockMvc.perform(get("/api/session-ids/{id}", sessionId.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(sessionId.getId().intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingSessionId() throws Exception {
        // Get the sessionId
        restSessionIdMockMvc.perform(get("/api/session-ids/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSessionId() throws Exception {
        // Initialize the database
        sessionIdRepository.saveAndFlush(sessionId);
        sessionIdSearchRepository.save(sessionId);
        int databaseSizeBeforeUpdate = sessionIdRepository.findAll().size();

        // Update the sessionId
        SessionId updatedSessionId = sessionIdRepository.findOne(sessionId.getId());
        SessionIdDTO sessionIdDTO = sessionIdMapper.toDto(updatedSessionId);

        restSessionIdMockMvc.perform(put("/api/session-ids")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sessionIdDTO)))
            .andExpect(status().isOk());

        // Validate the SessionId in the database
        List<SessionId> sessionIdList = sessionIdRepository.findAll();
        assertThat(sessionIdList).hasSize(databaseSizeBeforeUpdate);
        SessionId testSessionId = sessionIdList.get(sessionIdList.size() - 1);

        // Validate the SessionId in Elasticsearch
        SessionId sessionIdEs = sessionIdSearchRepository.findOne(testSessionId.getId());
        assertThat(sessionIdEs).isEqualToComparingFieldByField(testSessionId);
    }

    @Test
    @Transactional
    public void updateNonExistingSessionId() throws Exception {
        int databaseSizeBeforeUpdate = sessionIdRepository.findAll().size();

        // Create the SessionId
        SessionIdDTO sessionIdDTO = sessionIdMapper.toDto(sessionId);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restSessionIdMockMvc.perform(put("/api/session-ids")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sessionIdDTO)))
            .andExpect(status().isCreated());

        // Validate the SessionId in the database
        List<SessionId> sessionIdList = sessionIdRepository.findAll();
        assertThat(sessionIdList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteSessionId() throws Exception {
        // Initialize the database
        sessionIdRepository.saveAndFlush(sessionId);
        sessionIdSearchRepository.save(sessionId);
        int databaseSizeBeforeDelete = sessionIdRepository.findAll().size();

        // Get the sessionId
        restSessionIdMockMvc.perform(delete("/api/session-ids/{id}", sessionId.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate Elasticsearch is empty
        boolean sessionIdExistsInEs = sessionIdSearchRepository.exists(sessionId.getId());
        assertThat(sessionIdExistsInEs).isFalse();

        // Validate the database is empty
        List<SessionId> sessionIdList = sessionIdRepository.findAll();
        assertThat(sessionIdList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void searchSessionId() throws Exception {
        // Initialize the database
        sessionIdRepository.saveAndFlush(sessionId);
        sessionIdSearchRepository.save(sessionId);

        // Search the sessionId
        restSessionIdMockMvc.perform(get("/api/_search/session-ids?query=id:" + sessionId.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(sessionId.getId().intValue())));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SessionId.class);
        SessionId sessionId1 = new SessionId();
        sessionId1.setId(1L);
        SessionId sessionId2 = new SessionId();
        sessionId2.setId(sessionId1.getId());
        assertThat(sessionId1).isEqualTo(sessionId2);
        sessionId2.setId(2L);
        assertThat(sessionId1).isNotEqualTo(sessionId2);
        sessionId1.setId(null);
        assertThat(sessionId1).isNotEqualTo(sessionId2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(SessionIdDTO.class);
        SessionIdDTO sessionIdDTO1 = new SessionIdDTO();
        sessionIdDTO1.setId(1L);
        SessionIdDTO sessionIdDTO2 = new SessionIdDTO();
        assertThat(sessionIdDTO1).isNotEqualTo(sessionIdDTO2);
        sessionIdDTO2.setId(sessionIdDTO1.getId());
        assertThat(sessionIdDTO1).isEqualTo(sessionIdDTO2);
        sessionIdDTO2.setId(2L);
        assertThat(sessionIdDTO1).isNotEqualTo(sessionIdDTO2);
        sessionIdDTO1.setId(null);
        assertThat(sessionIdDTO1).isNotEqualTo(sessionIdDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(sessionIdMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(sessionIdMapper.fromId(null)).isNull();
    }
}
