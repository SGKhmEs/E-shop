package com.social.eshop.web.rest;

import com.social.eshop.EshopApp;
import com.social.eshop.domain.Manager;
import com.social.eshop.domain.enumeration.Roles;
import com.social.eshop.repository.ManagerRepository;
import com.social.eshop.repository.search.ManagerSearchRepository;
import com.social.eshop.service.ManagerService;
import com.social.eshop.service.dto.ManagerDTO;
import com.social.eshop.service.mapper.ManagerMapper;
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
public class ManagerResourceTest {

    private static final Roles DEFAULT_ROLES = Roles.SMM;
    private static final Roles UPDATED_ROLES = Roles.MODERATOR;

    @Autowired
    private ManagerRepository managerRepository;

    @Autowired
    private ManagerMapper managerMapper;

    @Autowired
    private ManagerService managerService;

    @Autowired
    private ManagerSearchRepository managerSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restManagerMockMvc;

    private Manager manager;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        ManagerResource managerResource = new ManagerResource(managerService);
        this.restManagerMockMvc = MockMvcBuilders.standaloneSetup(managerResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    public static Manager createEntity(EntityManager em) {
        Manager manager = new Manager()
            .roles(DEFAULT_ROLES);
        return manager;
    }

    @Before
    public void initTest() {
        managerSearchRepository.deleteAll();
        manager = createEntity(em);
    }

    @Test
    @Transactional
    public void createManager() throws Exception {
        int databaseSizeBeforeCreate = managerRepository.findAll().size();

        // Create the Manager
        ManagerDTO managerDTO = managerMapper.toDto(manager);
        restManagerMockMvc.perform(post("/api/managers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(managerDTO)))
            .andExpect(status().isCreated());

        // Validate the Manager in the database
        List<Manager> managerList = managerRepository.findAll();
        assertThat(managerList).hasSize(databaseSizeBeforeCreate + 1);
        Manager testManager = managerList.get(managerList.size() - 1);
        assertThat(testManager.getRoles()).isEqualTo(DEFAULT_ROLES);

        // Validate the Manager in Elasticsearch
        Manager managerEs = managerSearchRepository.findOne(testManager.getId());
        assertThat(managerEs).isEqualToComparingFieldByField(testManager);
    }

    @Test
    @Transactional
    public void getAllManagers() throws Exception {
        // Initialize the database
        managerRepository.saveAndFlush(manager);

        // Get all the managerList
        restManagerMockMvc.perform(get("/api/managers?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(manager.getId().intValue())))
            .andExpect(jsonPath("$.[*].roles").value(hasItem(DEFAULT_ROLES.toString())));
    }

    @Test
    @Transactional
    public void getManager() throws Exception {
        // Initialize the database
        managerRepository.saveAndFlush(manager);

        // Get the manager
        restManagerMockMvc.perform(get("/api/managers/{id}", manager.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(manager.getId().intValue()))
            .andExpect(jsonPath("$.roles").value(DEFAULT_ROLES.toString()));
    }

    @Test
    @Transactional
    public void updateManager() throws Exception {
        // Initialize the database
        managerRepository.saveAndFlush(manager);
        managerSearchRepository.save(manager);
        int databaseSizeBeforeUpdate = managerRepository.findAll().size();

        // Update the manager
        Manager updatedManager = managerRepository.findOne(manager.getId());
        updatedManager
            .roles(UPDATED_ROLES);
        ManagerDTO managerDTO = managerMapper.toDto(updatedManager);

        restManagerMockMvc.perform(put("/api/managers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(managerDTO)))
            .andExpect(status().isOk());

        // Validate the Manager in the database
        List<Manager> managerList = managerRepository.findAll();
        assertThat(managerList).hasSize(databaseSizeBeforeUpdate);
        Manager testManager = managerList.get(managerList.size() - 1);
        assertThat(testManager.getRoles()).isEqualTo(UPDATED_ROLES);

        // Validate the Manager in Elasticsearch
        Manager managerEs = managerSearchRepository.findOne(testManager.getId());
        assertThat(managerEs).isEqualToComparingFieldByField(testManager);
    }

    @Test
    @Transactional
    public void deleteManager() throws Exception {
        // Initialize the database
        managerRepository.saveAndFlush(manager);
        managerSearchRepository.save(manager);
        int databaseSizeBeforeDelete = managerRepository.findAll().size();

        // Get the manager
        restManagerMockMvc.perform(delete("/api/managers/{id}", manager.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate Elasticsearch is empty
        boolean managerExistsInEs = managerSearchRepository.exists(manager.getId());
        assertThat(managerExistsInEs).isFalse();

        // Validate the database is empty
        List<Manager> managerList = managerRepository.findAll();
        assertThat(managerList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void searchManager() throws Exception {
        // Initialize the database
        managerRepository.saveAndFlush(manager);
        managerSearchRepository.save(manager);

        // Search the manager
        restManagerMockMvc.perform(get("/api/_search/managers?query=id:" + manager.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(manager.getId().intValue())))
            .andExpect(jsonPath("$.[*].roles").value(hasItem(DEFAULT_ROLES.toString())));
    }
}
