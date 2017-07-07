package com.social.eshop.web.rest;

import com.social.eshop.EshopApp;

import com.social.eshop.domain.Bucket;
import com.social.eshop.repository.BucketRepository;
import com.social.eshop.service.BucketService;
import com.social.eshop.repository.search.BucketSearchRepository;
<<<<<<< HEAD
=======
import com.social.eshop.service.dto.BucketDTO;
import com.social.eshop.service.mapper.BucketMapper;
>>>>>>> with_entities
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
<<<<<<< HEAD
import java.math.BigDecimal;
=======
>>>>>>> with_entities
import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.ZoneOffset;
import java.time.ZoneId;
import java.util.List;

import static com.social.eshop.web.rest.TestUtil.sameInstant;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

<<<<<<< HEAD
import com.social.eshop.domain.enumeration.Status;
=======
>>>>>>> with_entities
/**
 * Test class for the BucketResource REST controller.
 *
 * @see BucketResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = EshopApp.class)
public class BucketResourceIntTest {

<<<<<<< HEAD
    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_DATA = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATA = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final BigDecimal DEFAULT_SUM = new BigDecimal(1);
    private static final BigDecimal UPDATED_SUM = new BigDecimal(2);

    private static final Integer DEFAULT_ORDER_NUMBER = 1;
    private static final Integer UPDATED_ORDER_NUMBER = 2;

    private static final Integer DEFAULT_COUNT = 1;
    private static final Integer UPDATED_COUNT = 2;

    private static final Status DEFAULT_STATUS = Status.WAIT;
    private static final Status UPDATED_STATUS = Status.PURCHASED;

    private static final String DEFAULT_CONSIGNMENT_NOTE = "AAAAAAAAAA";
    private static final String UPDATED_CONSIGNMENT_NOTE = "BBBBBBBBBB";

=======
    private static final ZonedDateTime DEFAULT_DATA = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATA = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

>>>>>>> with_entities
    @Autowired
    private BucketRepository bucketRepository;

    @Autowired
<<<<<<< HEAD
=======
    private BucketMapper bucketMapper;

    @Autowired
>>>>>>> with_entities
    private BucketService bucketService;

    @Autowired
    private BucketSearchRepository bucketSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restBucketMockMvc;

    private Bucket bucket;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        BucketResource bucketResource = new BucketResource(bucketService);
        this.restBucketMockMvc = MockMvcBuilders.standaloneSetup(bucketResource)
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
    public static Bucket createEntity(EntityManager em) {
        Bucket bucket = new Bucket()
<<<<<<< HEAD
            .name(DEFAULT_NAME)
            .data(DEFAULT_DATA)
            .sum(DEFAULT_SUM)
            .orderNumber(DEFAULT_ORDER_NUMBER)
            .count(DEFAULT_COUNT)
            .status(DEFAULT_STATUS)
            .consignmentNote(DEFAULT_CONSIGNMENT_NOTE);
=======
            .data(DEFAULT_DATA);
>>>>>>> with_entities
        return bucket;
    }

    @Before
    public void initTest() {
        bucketSearchRepository.deleteAll();
        bucket = createEntity(em);
    }

    @Test
    @Transactional
    public void createBucket() throws Exception {
        int databaseSizeBeforeCreate = bucketRepository.findAll().size();

        // Create the Bucket
<<<<<<< HEAD
        restBucketMockMvc.perform(post("/api/buckets")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(bucket)))
=======
        BucketDTO bucketDTO = bucketMapper.toDto(bucket);
        restBucketMockMvc.perform(post("/api/buckets")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(bucketDTO)))
>>>>>>> with_entities
            .andExpect(status().isCreated());

        // Validate the Bucket in the database
        List<Bucket> bucketList = bucketRepository.findAll();
        assertThat(bucketList).hasSize(databaseSizeBeforeCreate + 1);
        Bucket testBucket = bucketList.get(bucketList.size() - 1);
<<<<<<< HEAD
        assertThat(testBucket.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testBucket.getData()).isEqualTo(DEFAULT_DATA);
        assertThat(testBucket.getSum()).isEqualTo(DEFAULT_SUM);
        assertThat(testBucket.getOrderNumber()).isEqualTo(DEFAULT_ORDER_NUMBER);
        assertThat(testBucket.getCount()).isEqualTo(DEFAULT_COUNT);
        assertThat(testBucket.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testBucket.getConsignmentNote()).isEqualTo(DEFAULT_CONSIGNMENT_NOTE);
=======
        assertThat(testBucket.getData()).isEqualTo(DEFAULT_DATA);
>>>>>>> with_entities

        // Validate the Bucket in Elasticsearch
        Bucket bucketEs = bucketSearchRepository.findOne(testBucket.getId());
        assertThat(bucketEs).isEqualToComparingFieldByField(testBucket);
    }

    @Test
    @Transactional
    public void createBucketWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = bucketRepository.findAll().size();

        // Create the Bucket with an existing ID
        bucket.setId(1L);
<<<<<<< HEAD
=======
        BucketDTO bucketDTO = bucketMapper.toDto(bucket);
>>>>>>> with_entities

        // An entity with an existing ID cannot be created, so this API call must fail
        restBucketMockMvc.perform(post("/api/buckets")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
<<<<<<< HEAD
            .content(TestUtil.convertObjectToJsonBytes(bucket)))
=======
            .content(TestUtil.convertObjectToJsonBytes(bucketDTO)))
>>>>>>> with_entities
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Bucket> bucketList = bucketRepository.findAll();
        assertThat(bucketList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllBuckets() throws Exception {
        // Initialize the database
        bucketRepository.saveAndFlush(bucket);

        // Get all the bucketList
        restBucketMockMvc.perform(get("/api/buckets?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(bucket.getId().intValue())))
<<<<<<< HEAD
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].data").value(hasItem(sameInstant(DEFAULT_DATA))))
            .andExpect(jsonPath("$.[*].sum").value(hasItem(DEFAULT_SUM.intValue())))
            .andExpect(jsonPath("$.[*].orderNumber").value(hasItem(DEFAULT_ORDER_NUMBER)))
            .andExpect(jsonPath("$.[*].count").value(hasItem(DEFAULT_COUNT)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].consignmentNote").value(hasItem(DEFAULT_CONSIGNMENT_NOTE.toString())));
=======
            .andExpect(jsonPath("$.[*].data").value(hasItem(sameInstant(DEFAULT_DATA))));
>>>>>>> with_entities
    }

    @Test
    @Transactional
    public void getBucket() throws Exception {
        // Initialize the database
        bucketRepository.saveAndFlush(bucket);

        // Get the bucket
        restBucketMockMvc.perform(get("/api/buckets/{id}", bucket.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(bucket.getId().intValue()))
<<<<<<< HEAD
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.data").value(sameInstant(DEFAULT_DATA)))
            .andExpect(jsonPath("$.sum").value(DEFAULT_SUM.intValue()))
            .andExpect(jsonPath("$.orderNumber").value(DEFAULT_ORDER_NUMBER))
            .andExpect(jsonPath("$.count").value(DEFAULT_COUNT))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()))
            .andExpect(jsonPath("$.consignmentNote").value(DEFAULT_CONSIGNMENT_NOTE.toString()));
=======
            .andExpect(jsonPath("$.data").value(sameInstant(DEFAULT_DATA)));
>>>>>>> with_entities
    }

    @Test
    @Transactional
    public void getNonExistingBucket() throws Exception {
        // Get the bucket
        restBucketMockMvc.perform(get("/api/buckets/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateBucket() throws Exception {
        // Initialize the database
<<<<<<< HEAD
        bucketService.save(bucket);

=======
        bucketRepository.saveAndFlush(bucket);
        bucketSearchRepository.save(bucket);
>>>>>>> with_entities
        int databaseSizeBeforeUpdate = bucketRepository.findAll().size();

        // Update the bucket
        Bucket updatedBucket = bucketRepository.findOne(bucket.getId());
        updatedBucket
<<<<<<< HEAD
            .name(UPDATED_NAME)
            .data(UPDATED_DATA)
            .sum(UPDATED_SUM)
            .orderNumber(UPDATED_ORDER_NUMBER)
            .count(UPDATED_COUNT)
            .status(UPDATED_STATUS)
            .consignmentNote(UPDATED_CONSIGNMENT_NOTE);

        restBucketMockMvc.perform(put("/api/buckets")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedBucket)))
=======
            .data(UPDATED_DATA);
        BucketDTO bucketDTO = bucketMapper.toDto(updatedBucket);

        restBucketMockMvc.perform(put("/api/buckets")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(bucketDTO)))
>>>>>>> with_entities
            .andExpect(status().isOk());

        // Validate the Bucket in the database
        List<Bucket> bucketList = bucketRepository.findAll();
        assertThat(bucketList).hasSize(databaseSizeBeforeUpdate);
        Bucket testBucket = bucketList.get(bucketList.size() - 1);
<<<<<<< HEAD
        assertThat(testBucket.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testBucket.getData()).isEqualTo(UPDATED_DATA);
        assertThat(testBucket.getSum()).isEqualTo(UPDATED_SUM);
        assertThat(testBucket.getOrderNumber()).isEqualTo(UPDATED_ORDER_NUMBER);
        assertThat(testBucket.getCount()).isEqualTo(UPDATED_COUNT);
        assertThat(testBucket.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testBucket.getConsignmentNote()).isEqualTo(UPDATED_CONSIGNMENT_NOTE);
=======
        assertThat(testBucket.getData()).isEqualTo(UPDATED_DATA);
>>>>>>> with_entities

        // Validate the Bucket in Elasticsearch
        Bucket bucketEs = bucketSearchRepository.findOne(testBucket.getId());
        assertThat(bucketEs).isEqualToComparingFieldByField(testBucket);
    }

    @Test
    @Transactional
    public void updateNonExistingBucket() throws Exception {
        int databaseSizeBeforeUpdate = bucketRepository.findAll().size();

        // Create the Bucket
<<<<<<< HEAD
=======
        BucketDTO bucketDTO = bucketMapper.toDto(bucket);
>>>>>>> with_entities

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restBucketMockMvc.perform(put("/api/buckets")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
<<<<<<< HEAD
            .content(TestUtil.convertObjectToJsonBytes(bucket)))
=======
            .content(TestUtil.convertObjectToJsonBytes(bucketDTO)))
>>>>>>> with_entities
            .andExpect(status().isCreated());

        // Validate the Bucket in the database
        List<Bucket> bucketList = bucketRepository.findAll();
        assertThat(bucketList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteBucket() throws Exception {
        // Initialize the database
<<<<<<< HEAD
        bucketService.save(bucket);

=======
        bucketRepository.saveAndFlush(bucket);
        bucketSearchRepository.save(bucket);
>>>>>>> with_entities
        int databaseSizeBeforeDelete = bucketRepository.findAll().size();

        // Get the bucket
        restBucketMockMvc.perform(delete("/api/buckets/{id}", bucket.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate Elasticsearch is empty
        boolean bucketExistsInEs = bucketSearchRepository.exists(bucket.getId());
        assertThat(bucketExistsInEs).isFalse();

        // Validate the database is empty
        List<Bucket> bucketList = bucketRepository.findAll();
        assertThat(bucketList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void searchBucket() throws Exception {
        // Initialize the database
<<<<<<< HEAD
        bucketService.save(bucket);
=======
        bucketRepository.saveAndFlush(bucket);
        bucketSearchRepository.save(bucket);
>>>>>>> with_entities

        // Search the bucket
        restBucketMockMvc.perform(get("/api/_search/buckets?query=id:" + bucket.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(bucket.getId().intValue())))
<<<<<<< HEAD
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].data").value(hasItem(sameInstant(DEFAULT_DATA))))
            .andExpect(jsonPath("$.[*].sum").value(hasItem(DEFAULT_SUM.intValue())))
            .andExpect(jsonPath("$.[*].orderNumber").value(hasItem(DEFAULT_ORDER_NUMBER)))
            .andExpect(jsonPath("$.[*].count").value(hasItem(DEFAULT_COUNT)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].consignmentNote").value(hasItem(DEFAULT_CONSIGNMENT_NOTE.toString())));
=======
            .andExpect(jsonPath("$.[*].data").value(hasItem(sameInstant(DEFAULT_DATA))));
>>>>>>> with_entities
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Bucket.class);
        Bucket bucket1 = new Bucket();
        bucket1.setId(1L);
        Bucket bucket2 = new Bucket();
        bucket2.setId(bucket1.getId());
        assertThat(bucket1).isEqualTo(bucket2);
        bucket2.setId(2L);
        assertThat(bucket1).isNotEqualTo(bucket2);
        bucket1.setId(null);
        assertThat(bucket1).isNotEqualTo(bucket2);
    }
<<<<<<< HEAD
=======

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(BucketDTO.class);
        BucketDTO bucketDTO1 = new BucketDTO();
        bucketDTO1.setId(1L);
        BucketDTO bucketDTO2 = new BucketDTO();
        assertThat(bucketDTO1).isNotEqualTo(bucketDTO2);
        bucketDTO2.setId(bucketDTO1.getId());
        assertThat(bucketDTO1).isEqualTo(bucketDTO2);
        bucketDTO2.setId(2L);
        assertThat(bucketDTO1).isNotEqualTo(bucketDTO2);
        bucketDTO1.setId(null);
        assertThat(bucketDTO1).isNotEqualTo(bucketDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(bucketMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(bucketMapper.fromId(null)).isNull();
    }
>>>>>>> with_entities
}
