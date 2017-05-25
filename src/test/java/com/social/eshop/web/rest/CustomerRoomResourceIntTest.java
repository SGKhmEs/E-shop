package com.social.eshop.web.rest;

import com.social.eshop.EshopApp;

import com.social.eshop.domain.CustomerRoom;
import com.social.eshop.repository.CustomerRoomRepository;
import com.social.eshop.service.CustomerRoomService;
import com.social.eshop.repository.search.CustomerRoomSearchRepository;
import com.social.eshop.service.dto.CustomerRoomDTO;
import com.social.eshop.service.mapper.CustomerRoomMapper;
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

import com.social.eshop.domain.enumeration.SocialConnect;
/**
 * Test class for the CustomerRoomResource REST controller.
 *
 * @see CustomerRoomResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = EshopApp.class)
public class CustomerRoomResourceIntTest {

    private static final Boolean DEFAULT_SUB_SCRIPTION = false;
    private static final Boolean UPDATED_SUB_SCRIPTION = true;

    private static final SocialConnect DEFAULT_SOSIAL_CONNECT = SocialConnect.DEFAULT;
    private static final SocialConnect UPDATED_SOSIAL_CONNECT = SocialConnect.GOOGLE;

    @Autowired
    private CustomerRoomRepository customerRoomRepository;

    @Autowired
    private CustomerRoomMapper customerRoomMapper;

    @Autowired
    private CustomerRoomService customerRoomService;

    @Autowired
    private CustomerRoomSearchRepository customerRoomSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restCustomerRoomMockMvc;

    private CustomerRoom customerRoom;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        CustomerRoomResource customerRoomResource = new CustomerRoomResource(customerRoomService);
        this.restCustomerRoomMockMvc = MockMvcBuilders.standaloneSetup(customerRoomResource)
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
    public static CustomerRoom createEntity(EntityManager em) {
        CustomerRoom customerRoom = new CustomerRoom()
            .subScription(DEFAULT_SUB_SCRIPTION)
            .sosialConnect(DEFAULT_SOSIAL_CONNECT);
        return customerRoom;
    }

    @Before
    public void initTest() {
        customerRoomSearchRepository.deleteAll();
        customerRoom = createEntity(em);
    }

    @Test
    @Transactional
    public void createCustomerRoom() throws Exception {
        int databaseSizeBeforeCreate = customerRoomRepository.findAll().size();

        // Create the CustomerRoom
        CustomerRoomDTO customerRoomDTO = customerRoomMapper.toDto(customerRoom);
        restCustomerRoomMockMvc.perform(post("/api/customer-rooms")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(customerRoomDTO)))
            .andExpect(status().isCreated());

        // Validate the CustomerRoom in the database
        List<CustomerRoom> customerRoomList = customerRoomRepository.findAll();
        assertThat(customerRoomList).hasSize(databaseSizeBeforeCreate + 1);
        CustomerRoom testCustomerRoom = customerRoomList.get(customerRoomList.size() - 1);
        assertThat(testCustomerRoom.isSubScription()).isEqualTo(DEFAULT_SUB_SCRIPTION);
        assertThat(testCustomerRoom.getSosialConnect()).isEqualTo(DEFAULT_SOSIAL_CONNECT);

        // Validate the CustomerRoom in Elasticsearch
        CustomerRoom customerRoomEs = customerRoomSearchRepository.findOne(testCustomerRoom.getId());
        assertThat(customerRoomEs).isEqualToComparingFieldByField(testCustomerRoom);
    }

    @Test
    @Transactional
    public void createCustomerRoomWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = customerRoomRepository.findAll().size();

        // Create the CustomerRoom with an existing ID
        customerRoom.setId(1L);
        CustomerRoomDTO customerRoomDTO = customerRoomMapper.toDto(customerRoom);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCustomerRoomMockMvc.perform(post("/api/customer-rooms")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(customerRoomDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<CustomerRoom> customerRoomList = customerRoomRepository.findAll();
        assertThat(customerRoomList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllCustomerRooms() throws Exception {
        // Initialize the database
        customerRoomRepository.saveAndFlush(customerRoom);

        // Get all the customerRoomList
        restCustomerRoomMockMvc.perform(get("/api/customer-rooms?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(customerRoom.getId().intValue())))
            .andExpect(jsonPath("$.[*].subScription").value(hasItem(DEFAULT_SUB_SCRIPTION.booleanValue())))
            .andExpect(jsonPath("$.[*].sosialConnect").value(hasItem(DEFAULT_SOSIAL_CONNECT.toString())));
    }

    @Test
    @Transactional
    public void getCustomerRoom() throws Exception {
        // Initialize the database
        customerRoomRepository.saveAndFlush(customerRoom);

        // Get the customerRoom
        restCustomerRoomMockMvc.perform(get("/api/customer-rooms/{id}", customerRoom.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(customerRoom.getId().intValue()))
            .andExpect(jsonPath("$.subScription").value(DEFAULT_SUB_SCRIPTION.booleanValue()))
            .andExpect(jsonPath("$.sosialConnect").value(DEFAULT_SOSIAL_CONNECT.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingCustomerRoom() throws Exception {
        // Get the customerRoom
        restCustomerRoomMockMvc.perform(get("/api/customer-rooms/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCustomerRoom() throws Exception {
        // Initialize the database
        customerRoomRepository.saveAndFlush(customerRoom);
        customerRoomSearchRepository.save(customerRoom);
        int databaseSizeBeforeUpdate = customerRoomRepository.findAll().size();

        // Update the customerRoom
        CustomerRoom updatedCustomerRoom = customerRoomRepository.findOne(customerRoom.getId());
        updatedCustomerRoom
            .subScription(UPDATED_SUB_SCRIPTION)
            .sosialConnect(UPDATED_SOSIAL_CONNECT);
        CustomerRoomDTO customerRoomDTO = customerRoomMapper.toDto(updatedCustomerRoom);

        restCustomerRoomMockMvc.perform(put("/api/customer-rooms")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(customerRoomDTO)))
            .andExpect(status().isOk());

        // Validate the CustomerRoom in the database
        List<CustomerRoom> customerRoomList = customerRoomRepository.findAll();
        assertThat(customerRoomList).hasSize(databaseSizeBeforeUpdate);
        CustomerRoom testCustomerRoom = customerRoomList.get(customerRoomList.size() - 1);
        assertThat(testCustomerRoom.isSubScription()).isEqualTo(UPDATED_SUB_SCRIPTION);
        assertThat(testCustomerRoom.getSosialConnect()).isEqualTo(UPDATED_SOSIAL_CONNECT);

        // Validate the CustomerRoom in Elasticsearch
        CustomerRoom customerRoomEs = customerRoomSearchRepository.findOne(testCustomerRoom.getId());
        assertThat(customerRoomEs).isEqualToComparingFieldByField(testCustomerRoom);
    }

    @Test
    @Transactional
    public void updateNonExistingCustomerRoom() throws Exception {
        int databaseSizeBeforeUpdate = customerRoomRepository.findAll().size();

        // Create the CustomerRoom
        CustomerRoomDTO customerRoomDTO = customerRoomMapper.toDto(customerRoom);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restCustomerRoomMockMvc.perform(put("/api/customer-rooms")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(customerRoomDTO)))
            .andExpect(status().isCreated());

        // Validate the CustomerRoom in the database
        List<CustomerRoom> customerRoomList = customerRoomRepository.findAll();
        assertThat(customerRoomList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteCustomerRoom() throws Exception {
        // Initialize the database
        customerRoomRepository.saveAndFlush(customerRoom);
        customerRoomSearchRepository.save(customerRoom);
        int databaseSizeBeforeDelete = customerRoomRepository.findAll().size();

        // Get the customerRoom
        restCustomerRoomMockMvc.perform(delete("/api/customer-rooms/{id}", customerRoom.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate Elasticsearch is empty
        boolean customerRoomExistsInEs = customerRoomSearchRepository.exists(customerRoom.getId());
        assertThat(customerRoomExistsInEs).isFalse();

        // Validate the database is empty
        List<CustomerRoom> customerRoomList = customerRoomRepository.findAll();
        assertThat(customerRoomList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void searchCustomerRoom() throws Exception {
        // Initialize the database
        customerRoomRepository.saveAndFlush(customerRoom);
        customerRoomSearchRepository.save(customerRoom);

        // Search the customerRoom
        restCustomerRoomMockMvc.perform(get("/api/_search/customer-rooms?query=id:" + customerRoom.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(customerRoom.getId().intValue())))
            .andExpect(jsonPath("$.[*].subScription").value(hasItem(DEFAULT_SUB_SCRIPTION.booleanValue())))
            .andExpect(jsonPath("$.[*].sosialConnect").value(hasItem(DEFAULT_SOSIAL_CONNECT.toString())));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CustomerRoom.class);
        CustomerRoom customerRoom1 = new CustomerRoom();
        customerRoom1.setId(1L);
        CustomerRoom customerRoom2 = new CustomerRoom();
        customerRoom2.setId(customerRoom1.getId());
        assertThat(customerRoom1).isEqualTo(customerRoom2);
        customerRoom2.setId(2L);
        assertThat(customerRoom1).isNotEqualTo(customerRoom2);
        customerRoom1.setId(null);
        assertThat(customerRoom1).isNotEqualTo(customerRoom2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CustomerRoomDTO.class);
        CustomerRoomDTO customerRoomDTO1 = new CustomerRoomDTO();
        customerRoomDTO1.setId(1L);
        CustomerRoomDTO customerRoomDTO2 = new CustomerRoomDTO();
        assertThat(customerRoomDTO1).isNotEqualTo(customerRoomDTO2);
        customerRoomDTO2.setId(customerRoomDTO1.getId());
        assertThat(customerRoomDTO1).isEqualTo(customerRoomDTO2);
        customerRoomDTO2.setId(2L);
        assertThat(customerRoomDTO1).isNotEqualTo(customerRoomDTO2);
        customerRoomDTO1.setId(null);
        assertThat(customerRoomDTO1).isNotEqualTo(customerRoomDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(customerRoomMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(customerRoomMapper.fromId(null)).isNull();
    }
}
