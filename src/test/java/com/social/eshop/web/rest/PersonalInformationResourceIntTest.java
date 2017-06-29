package com.social.eshop.web.rest;

import com.social.eshop.EshopApp;

import com.social.eshop.domain.PersonalInformation;
import com.social.eshop.repository.PersonalInformationRepository;
import com.social.eshop.service.PersonalInformationService;
import com.social.eshop.repository.search.PersonalInformationSearchRepository;
import com.social.eshop.service.dto.PersonalInformationDTO;
import com.social.eshop.service.mapper.PersonalInformationMapper;
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

import com.social.eshop.domain.enumeration.Sex;
/**
 * Test class for the PersonalInformationResource REST controller.
 *
 * @see PersonalInformationResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = EshopApp.class)
public class PersonalInformationResourceIntTest {

    private static final String DEFAULT_FIRST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_FIRST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_LAST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_LAST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_MIDDLE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_MIDDLE_NAME = "BBBBBBBBBB";

    private static final Sex DEFAULT_SEX = Sex.MAN;
    private static final Sex UPDATED_SEX = Sex.WOMAN;

    private static final String DEFAULT_PHONE = "AAAAAAAAAA";
    private static final String UPDATED_PHONE = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DATE_BIRTH = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_BIRTH = LocalDate.now(ZoneId.systemDefault());

    @Autowired
    private PersonalInformationRepository personalInformationRepository;

    @Autowired
    private PersonalInformationMapper personalInformationMapper;

    @Autowired
    private PersonalInformationService personalInformationService;

    @Autowired
    private PersonalInformationSearchRepository personalInformationSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restPersonalInformationMockMvc;

    private PersonalInformation personalInformation;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        PersonalInformationResource personalInformationResource = new PersonalInformationResource(personalInformationService);
        this.restPersonalInformationMockMvc = MockMvcBuilders.standaloneSetup(personalInformationResource)
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
    public static PersonalInformation createEntity(EntityManager em) {
        PersonalInformation personalInformation = new PersonalInformation()
            .firstName(DEFAULT_FIRST_NAME)
            .lastName(DEFAULT_LAST_NAME)
            .middleName(DEFAULT_MIDDLE_NAME)
            .sex(DEFAULT_SEX)
            .phone(DEFAULT_PHONE)
            .email(DEFAULT_EMAIL)
            .dateBirth(DEFAULT_DATE_BIRTH);
        return personalInformation;
    }

    @Before
    public void initTest() {
        personalInformationSearchRepository.deleteAll();
        personalInformation = createEntity(em);
    }

    @Test
    @Transactional
    public void createPersonalInformation() throws Exception {
        int databaseSizeBeforeCreate = personalInformationRepository.findAll().size();

        // Create the PersonalInformation
        PersonalInformationDTO personalInformationDTO = personalInformationMapper.toDto(personalInformation);
        restPersonalInformationMockMvc.perform(post("/api/personal-informations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(personalInformationDTO)))
            .andExpect(status().isCreated());

        // Validate the PersonalInformation in the database
        List<PersonalInformation> personalInformationList = personalInformationRepository.findAll();
        assertThat(personalInformationList).hasSize(databaseSizeBeforeCreate + 1);
        PersonalInformation testPersonalInformation = personalInformationList.get(personalInformationList.size() - 1);
        assertThat(testPersonalInformation.getFirstName()).isEqualTo(DEFAULT_FIRST_NAME);
        assertThat(testPersonalInformation.getLastName()).isEqualTo(DEFAULT_LAST_NAME);
        assertThat(testPersonalInformation.getMiddleName()).isEqualTo(DEFAULT_MIDDLE_NAME);
        assertThat(testPersonalInformation.getSex()).isEqualTo(DEFAULT_SEX);
        assertThat(testPersonalInformation.getPhone()).isEqualTo(DEFAULT_PHONE);
        assertThat(testPersonalInformation.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testPersonalInformation.getDateBirth()).isEqualTo(DEFAULT_DATE_BIRTH);

        // Validate the PersonalInformation in Elasticsearch
        PersonalInformation personalInformationEs = personalInformationSearchRepository.findOne(testPersonalInformation.getId());
        assertThat(personalInformationEs).isEqualToComparingFieldByField(testPersonalInformation);
    }

    @Test
    @Transactional
    public void createPersonalInformationWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = personalInformationRepository.findAll().size();

        // Create the PersonalInformation with an existing ID
        personalInformation.setId(1L);
        PersonalInformationDTO personalInformationDTO = personalInformationMapper.toDto(personalInformation);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPersonalInformationMockMvc.perform(post("/api/personal-informations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(personalInformationDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<PersonalInformation> personalInformationList = personalInformationRepository.findAll();
        assertThat(personalInformationList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkFirstNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = personalInformationRepository.findAll().size();
        // set the field null
        personalInformation.setFirstName(null);

        // Create the PersonalInformation, which fails.
        PersonalInformationDTO personalInformationDTO = personalInformationMapper.toDto(personalInformation);

        restPersonalInformationMockMvc.perform(post("/api/personal-informations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(personalInformationDTO)))
            .andExpect(status().isBadRequest());

        List<PersonalInformation> personalInformationList = personalInformationRepository.findAll();
        assertThat(personalInformationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLastNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = personalInformationRepository.findAll().size();
        // set the field null
        personalInformation.setLastName(null);

        // Create the PersonalInformation, which fails.
        PersonalInformationDTO personalInformationDTO = personalInformationMapper.toDto(personalInformation);

        restPersonalInformationMockMvc.perform(post("/api/personal-informations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(personalInformationDTO)))
            .andExpect(status().isBadRequest());

        List<PersonalInformation> personalInformationList = personalInformationRepository.findAll();
        assertThat(personalInformationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPhoneIsRequired() throws Exception {
        int databaseSizeBeforeTest = personalInformationRepository.findAll().size();
        // set the field null
        personalInformation.setPhone(null);

        // Create the PersonalInformation, which fails.
        PersonalInformationDTO personalInformationDTO = personalInformationMapper.toDto(personalInformation);

        restPersonalInformationMockMvc.perform(post("/api/personal-informations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(personalInformationDTO)))
            .andExpect(status().isBadRequest());

        List<PersonalInformation> personalInformationList = personalInformationRepository.findAll();
        assertThat(personalInformationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEmailIsRequired() throws Exception {
        int databaseSizeBeforeTest = personalInformationRepository.findAll().size();
        // set the field null
        personalInformation.setEmail(null);

        // Create the PersonalInformation, which fails.
        PersonalInformationDTO personalInformationDTO = personalInformationMapper.toDto(personalInformation);

        restPersonalInformationMockMvc.perform(post("/api/personal-informations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(personalInformationDTO)))
            .andExpect(status().isBadRequest());

        List<PersonalInformation> personalInformationList = personalInformationRepository.findAll();
        assertThat(personalInformationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllPersonalInformations() throws Exception {
        // Initialize the database
        personalInformationRepository.saveAndFlush(personalInformation);

        // Get all the personalInformationList
        restPersonalInformationMockMvc.perform(get("/api/personal-informations?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(personalInformation.getId().intValue())))
            .andExpect(jsonPath("$.[*].firstName").value(hasItem(DEFAULT_FIRST_NAME.toString())))
            .andExpect(jsonPath("$.[*].lastName").value(hasItem(DEFAULT_LAST_NAME.toString())))
            .andExpect(jsonPath("$.[*].middleName").value(hasItem(DEFAULT_MIDDLE_NAME.toString())))
            .andExpect(jsonPath("$.[*].sex").value(hasItem(DEFAULT_SEX.toString())))
            .andExpect(jsonPath("$.[*].phone").value(hasItem(DEFAULT_PHONE.toString())))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL.toString())))
            .andExpect(jsonPath("$.[*].dateBirth").value(hasItem(DEFAULT_DATE_BIRTH.toString())));
    }

    @Test
    @Transactional
    public void getPersonalInformation() throws Exception {
        // Initialize the database
        personalInformationRepository.saveAndFlush(personalInformation);

        // Get the personalInformation
        restPersonalInformationMockMvc.perform(get("/api/personal-informations/{id}", personalInformation.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(personalInformation.getId().intValue()))
            .andExpect(jsonPath("$.firstName").value(DEFAULT_FIRST_NAME.toString()))
            .andExpect(jsonPath("$.lastName").value(DEFAULT_LAST_NAME.toString()))
            .andExpect(jsonPath("$.middleName").value(DEFAULT_MIDDLE_NAME.toString()))
            .andExpect(jsonPath("$.sex").value(DEFAULT_SEX.toString()))
            .andExpect(jsonPath("$.phone").value(DEFAULT_PHONE.toString()))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL.toString()))
            .andExpect(jsonPath("$.dateBirth").value(DEFAULT_DATE_BIRTH.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingPersonalInformation() throws Exception {
        // Get the personalInformation
        restPersonalInformationMockMvc.perform(get("/api/personal-informations/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePersonalInformation() throws Exception {
        // Initialize the database
        personalInformationRepository.saveAndFlush(personalInformation);
        personalInformationSearchRepository.save(personalInformation);
        int databaseSizeBeforeUpdate = personalInformationRepository.findAll().size();

        // Update the personalInformation
        PersonalInformation updatedPersonalInformation = personalInformationRepository.findOne(personalInformation.getId());
        updatedPersonalInformation
            .firstName(UPDATED_FIRST_NAME)
            .lastName(UPDATED_LAST_NAME)
            .middleName(UPDATED_MIDDLE_NAME)
            .sex(UPDATED_SEX)
            .phone(UPDATED_PHONE)
            .email(UPDATED_EMAIL)
            .dateBirth(UPDATED_DATE_BIRTH);
        PersonalInformationDTO personalInformationDTO = personalInformationMapper.toDto(updatedPersonalInformation);

        restPersonalInformationMockMvc.perform(put("/api/personal-informations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(personalInformationDTO)))
            .andExpect(status().isOk());

        // Validate the PersonalInformation in the database
        List<PersonalInformation> personalInformationList = personalInformationRepository.findAll();
        assertThat(personalInformationList).hasSize(databaseSizeBeforeUpdate);
        PersonalInformation testPersonalInformation = personalInformationList.get(personalInformationList.size() - 1);
        assertThat(testPersonalInformation.getFirstName()).isEqualTo(UPDATED_FIRST_NAME);
        assertThat(testPersonalInformation.getLastName()).isEqualTo(UPDATED_LAST_NAME);
        assertThat(testPersonalInformation.getMiddleName()).isEqualTo(UPDATED_MIDDLE_NAME);
        assertThat(testPersonalInformation.getSex()).isEqualTo(UPDATED_SEX);
        assertThat(testPersonalInformation.getPhone()).isEqualTo(UPDATED_PHONE);
        assertThat(testPersonalInformation.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testPersonalInformation.getDateBirth()).isEqualTo(UPDATED_DATE_BIRTH);

        // Validate the PersonalInformation in Elasticsearch
        PersonalInformation personalInformationEs = personalInformationSearchRepository.findOne(testPersonalInformation.getId());
        assertThat(personalInformationEs).isEqualToComparingFieldByField(testPersonalInformation);
    }

    @Test
    @Transactional
    public void updateNonExistingPersonalInformation() throws Exception {
        int databaseSizeBeforeUpdate = personalInformationRepository.findAll().size();

        // Create the PersonalInformation
        PersonalInformationDTO personalInformationDTO = personalInformationMapper.toDto(personalInformation);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restPersonalInformationMockMvc.perform(put("/api/personal-informations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(personalInformationDTO)))
            .andExpect(status().isCreated());

        // Validate the PersonalInformation in the database
        List<PersonalInformation> personalInformationList = personalInformationRepository.findAll();
        assertThat(personalInformationList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deletePersonalInformation() throws Exception {
        // Initialize the database
        personalInformationRepository.saveAndFlush(personalInformation);
        personalInformationSearchRepository.save(personalInformation);
        int databaseSizeBeforeDelete = personalInformationRepository.findAll().size();

        // Get the personalInformation
        restPersonalInformationMockMvc.perform(delete("/api/personal-informations/{id}", personalInformation.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate Elasticsearch is empty
        boolean personalInformationExistsInEs = personalInformationSearchRepository.exists(personalInformation.getId());
        assertThat(personalInformationExistsInEs).isFalse();

        // Validate the database is empty
        List<PersonalInformation> personalInformationList = personalInformationRepository.findAll();
        assertThat(personalInformationList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void searchPersonalInformation() throws Exception {
        // Initialize the database
        personalInformationRepository.saveAndFlush(personalInformation);
        personalInformationSearchRepository.save(personalInformation);

        // Search the personalInformation
        restPersonalInformationMockMvc.perform(get("/api/_search/personal-informations?query=id:" + personalInformation.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(personalInformation.getId().intValue())))
            .andExpect(jsonPath("$.[*].firstName").value(hasItem(DEFAULT_FIRST_NAME.toString())))
            .andExpect(jsonPath("$.[*].lastName").value(hasItem(DEFAULT_LAST_NAME.toString())))
            .andExpect(jsonPath("$.[*].middleName").value(hasItem(DEFAULT_MIDDLE_NAME.toString())))
            .andExpect(jsonPath("$.[*].sex").value(hasItem(DEFAULT_SEX.toString())))
            .andExpect(jsonPath("$.[*].phone").value(hasItem(DEFAULT_PHONE.toString())))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL.toString())))
            .andExpect(jsonPath("$.[*].dateBirth").value(hasItem(DEFAULT_DATE_BIRTH.toString())));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PersonalInformation.class);
        PersonalInformation personalInformation1 = new PersonalInformation();
        personalInformation1.setId(1L);
        PersonalInformation personalInformation2 = new PersonalInformation();
        personalInformation2.setId(personalInformation1.getId());
        assertThat(personalInformation1).isEqualTo(personalInformation2);
        personalInformation2.setId(2L);
        assertThat(personalInformation1).isNotEqualTo(personalInformation2);
        personalInformation1.setId(null);
        assertThat(personalInformation1).isNotEqualTo(personalInformation2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PersonalInformationDTO.class);
        PersonalInformationDTO personalInformationDTO1 = new PersonalInformationDTO();
        personalInformationDTO1.setId(1L);
        PersonalInformationDTO personalInformationDTO2 = new PersonalInformationDTO();
        assertThat(personalInformationDTO1).isNotEqualTo(personalInformationDTO2);
        personalInformationDTO2.setId(personalInformationDTO1.getId());
        assertThat(personalInformationDTO1).isEqualTo(personalInformationDTO2);
        personalInformationDTO2.setId(2L);
        assertThat(personalInformationDTO1).isNotEqualTo(personalInformationDTO2);
        personalInformationDTO1.setId(null);
        assertThat(personalInformationDTO1).isNotEqualTo(personalInformationDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(personalInformationMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(personalInformationMapper.fromId(null)).isNull();
    }
}
