package com.social.eshop.web.rest;

import com.social.eshop.EshopApp;

import com.social.eshop.domain.AddressShipping;
import com.social.eshop.repository.AddressShippingRepository;
import com.social.eshop.service.AddressShippingService;
import com.social.eshop.repository.search.AddressShippingSearchRepository;
import com.social.eshop.service.dto.AddressShippingDTO;
import com.social.eshop.service.mapper.AddressShippingMapper;
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
 * Test class for the AddressShippingResource REST controller.
 *
 * @see AddressShippingResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = EshopApp.class)
public class AddressShippingResourceIntTest {

    private static final String DEFAULT_COUNTRY = "AAAAAAAAAA";
    private static final String UPDATED_COUNTRY = "BBBBBBBBBB";

    private static final String DEFAULT_CITY = "AAAAAAAAAA";
    private static final String UPDATED_CITY = "BBBBBBBBBB";

    private static final String DEFAULT_STATE = "AAAAAAAAAA";
    private static final String UPDATED_STATE = "BBBBBBBBBB";

    private static final String DEFAULT_REGION = "AAAAAAAAAA";
    private static final String UPDATED_REGION = "BBBBBBBBBB";

    private static final String DEFAULT_STREET = "AAAAAAAAAA";
    private static final String UPDATED_STREET = "BBBBBBBBBB";

    private static final String DEFAULT_BUILDING = "AAAAAAAAAA";
    private static final String UPDATED_BUILDING = "BBBBBBBBBB";

    private static final String DEFAULT_APPARTMENT = "AAAAAAAAAA";
    private static final String UPDATED_APPARTMENT = "BBBBBBBBBB";

    @Autowired
    private AddressShippingRepository addressShippingRepository;

    @Autowired
    private AddressShippingMapper addressShippingMapper;

    @Autowired
    private AddressShippingService addressShippingService;

    @Autowired
    private AddressShippingSearchRepository addressShippingSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restAddressShippingMockMvc;

    private AddressShipping addressShipping;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        AddressShippingResource addressShippingResource = new AddressShippingResource(addressShippingService);
        this.restAddressShippingMockMvc = MockMvcBuilders.standaloneSetup(addressShippingResource)
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
    public static AddressShipping createEntity(EntityManager em) {
        AddressShipping addressShipping = new AddressShipping()
            .country(DEFAULT_COUNTRY)
            .city(DEFAULT_CITY)
            .state(DEFAULT_STATE)
            .region(DEFAULT_REGION)
            .street(DEFAULT_STREET)
            .building(DEFAULT_BUILDING)
            .appartment(DEFAULT_APPARTMENT);
        return addressShipping;
    }

    @Before
    public void initTest() {
        addressShippingSearchRepository.deleteAll();
        addressShipping = createEntity(em);
    }

    @Test
    @Transactional
    public void createAddressShipping() throws Exception {
        int databaseSizeBeforeCreate = addressShippingRepository.findAll().size();

        // Create the AddressShipping
        AddressShippingDTO addressShippingDTO = addressShippingMapper.toDto(addressShipping);
        restAddressShippingMockMvc.perform(post("/api/address-shippings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(addressShippingDTO)))
            .andExpect(status().isCreated());

        // Validate the AddressShipping in the database
        List<AddressShipping> addressShippingList = addressShippingRepository.findAll();
        assertThat(addressShippingList).hasSize(databaseSizeBeforeCreate + 1);
        AddressShipping testAddressShipping = addressShippingList.get(addressShippingList.size() - 1);
        assertThat(testAddressShipping.getCountry()).isEqualTo(DEFAULT_COUNTRY);
        assertThat(testAddressShipping.getCity()).isEqualTo(DEFAULT_CITY);
        assertThat(testAddressShipping.getState()).isEqualTo(DEFAULT_STATE);
        assertThat(testAddressShipping.getRegion()).isEqualTo(DEFAULT_REGION);
        assertThat(testAddressShipping.getStreet()).isEqualTo(DEFAULT_STREET);
        assertThat(testAddressShipping.getBuilding()).isEqualTo(DEFAULT_BUILDING);
        assertThat(testAddressShipping.getAppartment()).isEqualTo(DEFAULT_APPARTMENT);

        // Validate the AddressShipping in Elasticsearch
        AddressShipping addressShippingEs = addressShippingSearchRepository.findOne(testAddressShipping.getId());
        assertThat(addressShippingEs).isEqualToComparingFieldByField(testAddressShipping);
    }

    @Test
    @Transactional
    public void createAddressShippingWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = addressShippingRepository.findAll().size();

        // Create the AddressShipping with an existing ID
        addressShipping.setId(1L);
        AddressShippingDTO addressShippingDTO = addressShippingMapper.toDto(addressShipping);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAddressShippingMockMvc.perform(post("/api/address-shippings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(addressShippingDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<AddressShipping> addressShippingList = addressShippingRepository.findAll();
        assertThat(addressShippingList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkCountryIsRequired() throws Exception {
        int databaseSizeBeforeTest = addressShippingRepository.findAll().size();
        // set the field null
        addressShipping.setCountry(null);

        // Create the AddressShipping, which fails.
        AddressShippingDTO addressShippingDTO = addressShippingMapper.toDto(addressShipping);

        restAddressShippingMockMvc.perform(post("/api/address-shippings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(addressShippingDTO)))
            .andExpect(status().isBadRequest());

        List<AddressShipping> addressShippingList = addressShippingRepository.findAll();
        assertThat(addressShippingList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCityIsRequired() throws Exception {
        int databaseSizeBeforeTest = addressShippingRepository.findAll().size();
        // set the field null
        addressShipping.setCity(null);

        // Create the AddressShipping, which fails.
        AddressShippingDTO addressShippingDTO = addressShippingMapper.toDto(addressShipping);

        restAddressShippingMockMvc.perform(post("/api/address-shippings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(addressShippingDTO)))
            .andExpect(status().isBadRequest());

        List<AddressShipping> addressShippingList = addressShippingRepository.findAll();
        assertThat(addressShippingList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStreetIsRequired() throws Exception {
        int databaseSizeBeforeTest = addressShippingRepository.findAll().size();
        // set the field null
        addressShipping.setStreet(null);

        // Create the AddressShipping, which fails.
        AddressShippingDTO addressShippingDTO = addressShippingMapper.toDto(addressShipping);

        restAddressShippingMockMvc.perform(post("/api/address-shippings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(addressShippingDTO)))
            .andExpect(status().isBadRequest());

        List<AddressShipping> addressShippingList = addressShippingRepository.findAll();
        assertThat(addressShippingList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkBuildingIsRequired() throws Exception {
        int databaseSizeBeforeTest = addressShippingRepository.findAll().size();
        // set the field null
        addressShipping.setBuilding(null);

        // Create the AddressShipping, which fails.
        AddressShippingDTO addressShippingDTO = addressShippingMapper.toDto(addressShipping);

        restAddressShippingMockMvc.perform(post("/api/address-shippings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(addressShippingDTO)))
            .andExpect(status().isBadRequest());

        List<AddressShipping> addressShippingList = addressShippingRepository.findAll();
        assertThat(addressShippingList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllAddressShippings() throws Exception {
        // Initialize the database
        addressShippingRepository.saveAndFlush(addressShipping);

        // Get all the addressShippingList
        restAddressShippingMockMvc.perform(get("/api/address-shippings?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(addressShipping.getId().intValue())))
            .andExpect(jsonPath("$.[*].country").value(hasItem(DEFAULT_COUNTRY.toString())))
            .andExpect(jsonPath("$.[*].city").value(hasItem(DEFAULT_CITY.toString())))
            .andExpect(jsonPath("$.[*].state").value(hasItem(DEFAULT_STATE.toString())))
            .andExpect(jsonPath("$.[*].region").value(hasItem(DEFAULT_REGION.toString())))
            .andExpect(jsonPath("$.[*].street").value(hasItem(DEFAULT_STREET.toString())))
            .andExpect(jsonPath("$.[*].building").value(hasItem(DEFAULT_BUILDING.toString())))
            .andExpect(jsonPath("$.[*].appartment").value(hasItem(DEFAULT_APPARTMENT.toString())));
    }

    @Test
    @Transactional
    public void getAddressShipping() throws Exception {
        // Initialize the database
        addressShippingRepository.saveAndFlush(addressShipping);

        // Get the addressShipping
        restAddressShippingMockMvc.perform(get("/api/address-shippings/{id}", addressShipping.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(addressShipping.getId().intValue()))
            .andExpect(jsonPath("$.country").value(DEFAULT_COUNTRY.toString()))
            .andExpect(jsonPath("$.city").value(DEFAULT_CITY.toString()))
            .andExpect(jsonPath("$.state").value(DEFAULT_STATE.toString()))
            .andExpect(jsonPath("$.region").value(DEFAULT_REGION.toString()))
            .andExpect(jsonPath("$.street").value(DEFAULT_STREET.toString()))
            .andExpect(jsonPath("$.building").value(DEFAULT_BUILDING.toString()))
            .andExpect(jsonPath("$.appartment").value(DEFAULT_APPARTMENT.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingAddressShipping() throws Exception {
        // Get the addressShipping
        restAddressShippingMockMvc.perform(get("/api/address-shippings/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAddressShipping() throws Exception {
        // Initialize the database
        addressShippingRepository.saveAndFlush(addressShipping);
        addressShippingSearchRepository.save(addressShipping);
        int databaseSizeBeforeUpdate = addressShippingRepository.findAll().size();

        // Update the addressShipping
        AddressShipping updatedAddressShipping = addressShippingRepository.findOne(addressShipping.getId());
        updatedAddressShipping
            .country(UPDATED_COUNTRY)
            .city(UPDATED_CITY)
            .state(UPDATED_STATE)
            .region(UPDATED_REGION)
            .street(UPDATED_STREET)
            .building(UPDATED_BUILDING)
            .appartment(UPDATED_APPARTMENT);
        AddressShippingDTO addressShippingDTO = addressShippingMapper.toDto(updatedAddressShipping);

        restAddressShippingMockMvc.perform(put("/api/address-shippings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(addressShippingDTO)))
            .andExpect(status().isOk());

        // Validate the AddressShipping in the database
        List<AddressShipping> addressShippingList = addressShippingRepository.findAll();
        assertThat(addressShippingList).hasSize(databaseSizeBeforeUpdate);
        AddressShipping testAddressShipping = addressShippingList.get(addressShippingList.size() - 1);
        assertThat(testAddressShipping.getCountry()).isEqualTo(UPDATED_COUNTRY);
        assertThat(testAddressShipping.getCity()).isEqualTo(UPDATED_CITY);
        assertThat(testAddressShipping.getState()).isEqualTo(UPDATED_STATE);
        assertThat(testAddressShipping.getRegion()).isEqualTo(UPDATED_REGION);
        assertThat(testAddressShipping.getStreet()).isEqualTo(UPDATED_STREET);
        assertThat(testAddressShipping.getBuilding()).isEqualTo(UPDATED_BUILDING);
        assertThat(testAddressShipping.getAppartment()).isEqualTo(UPDATED_APPARTMENT);

        // Validate the AddressShipping in Elasticsearch
        AddressShipping addressShippingEs = addressShippingSearchRepository.findOne(testAddressShipping.getId());
        assertThat(addressShippingEs).isEqualToComparingFieldByField(testAddressShipping);
    }

    @Test
    @Transactional
    public void updateNonExistingAddressShipping() throws Exception {
        int databaseSizeBeforeUpdate = addressShippingRepository.findAll().size();

        // Create the AddressShipping
        AddressShippingDTO addressShippingDTO = addressShippingMapper.toDto(addressShipping);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restAddressShippingMockMvc.perform(put("/api/address-shippings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(addressShippingDTO)))
            .andExpect(status().isCreated());

        // Validate the AddressShipping in the database
        List<AddressShipping> addressShippingList = addressShippingRepository.findAll();
        assertThat(addressShippingList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteAddressShipping() throws Exception {
        // Initialize the database
        addressShippingRepository.saveAndFlush(addressShipping);
        addressShippingSearchRepository.save(addressShipping);
        int databaseSizeBeforeDelete = addressShippingRepository.findAll().size();

        // Get the addressShipping
        restAddressShippingMockMvc.perform(delete("/api/address-shippings/{id}", addressShipping.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate Elasticsearch is empty
        boolean addressShippingExistsInEs = addressShippingSearchRepository.exists(addressShipping.getId());
        assertThat(addressShippingExistsInEs).isFalse();

        // Validate the database is empty
        List<AddressShipping> addressShippingList = addressShippingRepository.findAll();
        assertThat(addressShippingList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void searchAddressShipping() throws Exception {
        // Initialize the database
        addressShippingRepository.saveAndFlush(addressShipping);
        addressShippingSearchRepository.save(addressShipping);

        // Search the addressShipping
        restAddressShippingMockMvc.perform(get("/api/_search/address-shippings?query=id:" + addressShipping.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(addressShipping.getId().intValue())))
            .andExpect(jsonPath("$.[*].country").value(hasItem(DEFAULT_COUNTRY.toString())))
            .andExpect(jsonPath("$.[*].city").value(hasItem(DEFAULT_CITY.toString())))
            .andExpect(jsonPath("$.[*].state").value(hasItem(DEFAULT_STATE.toString())))
            .andExpect(jsonPath("$.[*].region").value(hasItem(DEFAULT_REGION.toString())))
            .andExpect(jsonPath("$.[*].street").value(hasItem(DEFAULT_STREET.toString())))
            .andExpect(jsonPath("$.[*].building").value(hasItem(DEFAULT_BUILDING.toString())))
            .andExpect(jsonPath("$.[*].appartment").value(hasItem(DEFAULT_APPARTMENT.toString())));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AddressShipping.class);
        AddressShipping addressShipping1 = new AddressShipping();
        addressShipping1.setId(1L);
        AddressShipping addressShipping2 = new AddressShipping();
        addressShipping2.setId(addressShipping1.getId());
        assertThat(addressShipping1).isEqualTo(addressShipping2);
        addressShipping2.setId(2L);
        assertThat(addressShipping1).isNotEqualTo(addressShipping2);
        addressShipping1.setId(null);
        assertThat(addressShipping1).isNotEqualTo(addressShipping2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AddressShippingDTO.class);
        AddressShippingDTO addressShippingDTO1 = new AddressShippingDTO();
        addressShippingDTO1.setId(1L);
        AddressShippingDTO addressShippingDTO2 = new AddressShippingDTO();
        assertThat(addressShippingDTO1).isNotEqualTo(addressShippingDTO2);
        addressShippingDTO2.setId(addressShippingDTO1.getId());
        assertThat(addressShippingDTO1).isEqualTo(addressShippingDTO2);
        addressShippingDTO2.setId(2L);
        assertThat(addressShippingDTO1).isNotEqualTo(addressShippingDTO2);
        addressShippingDTO1.setId(null);
        assertThat(addressShippingDTO1).isNotEqualTo(addressShippingDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(addressShippingMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(addressShippingMapper.fromId(null)).isNull();
    }
}
