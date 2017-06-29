package com.social.eshop.web.rest;

import com.social.eshop.EshopApp;

import com.social.eshop.domain.Bucket;
import com.social.eshop.repository.BucketRepository;
import com.social.eshop.service.BucketService;
import com.social.eshop.repository.search.BucketSearchRepository;
import com.social.eshop.service.dto.BucketDTO;
import com.social.eshop.service.mapper.BucketMapper;
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
import java.math.BigDecimal;
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

import com.social.eshop.domain.enumeration.Status;
/**
 * Test class for the BucketResource REST controller.
 *
 * @see BucketResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = EshopApp.class)
public class BucketResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final BigDecimal DEFAULT_SUM = new BigDecimal(1);
    private static final BigDecimal UPDATED_SUM = new BigDecimal(2);

    private static final Integer DEFAULT_ORDER_NUMBER = 1;
    private static final Integer UPDATED_ORDER_NUMBER = 2;

    private static final Integer DEFAULT_COUNT = 1;
    private static final Integer UPDATED_COUNT = 2;

    private static final Status DEFAULT_STATUS = Status.WAIT;
    private static final Status UPDATED_STATUS = Status.PROCESSING;

    private static final String DEFAULT_CONSIGNMENT_NOTE = "AAAAAAAAAA";
    private static final String UPDATED_CONSIGNMENT_NOTE = "BBBBBBBBBB";

    @Autowired
    private BucketRepository bucketRepository;

    @Autowired
    private BucketMapper bucketMapper;

    @Autowired
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
            .name(DEFAULT_NAME)
            .date(DEFAULT_DATE)
            .sum(DEFAULT_SUM)
            .orderNumber(DEFAULT_ORDER_NUMBER)
            .count(DEFAULT_COUNT)
            .status(DEFAULT_STATUS)
            .consignmentNote(DEFAULT_CONSIGNMENT_NOTE);
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
        BucketDTO bucketDTO = bucketMapper.toDto(bucket);
        restBucketMockMvc.perform(post("/api/buckets")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(bucketDTO)))
            .andExpect(status().isCreated());

        // Validate the Bucket in the database
        List<Bucket> bucketList = bucketRepository.findAll();
        assertThat(bucketList).hasSize(databaseSizeBeforeCreate + 1);
        Bucket testBucket = bucketList.get(bucketList.size() - 1);
        assertThat(testBucket.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testBucket.getDate()).isEqualTo(DEFAULT_DATE);
        assertThat(testBucket.getSum()).isEqualTo(DEFAULT_SUM);
        assertThat(testBucket.getOrderNumber()).isEqualTo(DEFAULT_ORDER_NUMBER);
        assertThat(testBucket.getCount()).isEqualTo(DEFAULT_COUNT);
        assertThat(testBucket.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testBucket.getConsignmentNote()).isEqualTo(DEFAULT_CONSIGNMENT_NOTE);

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
        BucketDTO bucketDTO = bucketMapper.toDto(bucket);

        // An entity with an existing ID cannot be created, so this API call must fail
        restBucketMockMvc.perform(post("/api/buckets")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(bucketDTO)))
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
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].date").value(hasItem(sameInstant(DEFAULT_DATE))))
            .andExpect(jsonPath("$.[*].sum").value(hasItem(DEFAULT_SUM.intValue())))
            .andExpect(jsonPath("$.[*].orderNumber").value(hasItem(DEFAULT_ORDER_NUMBER)))
            .andExpect(jsonPath("$.[*].count").value(hasItem(DEFAULT_COUNT)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].consignmentNote").value(hasItem(DEFAULT_CONSIGNMENT_NOTE.toString())));
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
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.date").value(sameInstant(DEFAULT_DATE)))
            .andExpect(jsonPath("$.sum").value(DEFAULT_SUM.intValue()))
            .andExpect(jsonPath("$.orderNumber").value(DEFAULT_ORDER_NUMBER))
            .andExpect(jsonPath("$.count").value(DEFAULT_COUNT))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()))
            .andExpect(jsonPath("$.consignmentNote").value(DEFAULT_CONSIGNMENT_NOTE.toString()));
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
        bucketRepository.saveAndFlush(bucket);
        bucketSearchRepository.save(bucket);
        int databaseSizeBeforeUpdate = bucketRepository.findAll().size();

        // Update the bucket
        Bucket updatedBucket = bucketRepository.findOne(bucket.getId());
        updatedBucket
            .name(UPDATED_NAME)
            .date(UPDATED_DATE)
            .sum(UPDATED_SUM)
            .orderNumber(UPDATED_ORDER_NUMBER)
            .count(UPDATED_COUNT)
            .status(UPDATED_STATUS)
            .consignmentNote(UPDATED_CONSIGNMENT_NOTE);
        BucketDTO bucketDTO = bucketMapper.toDto(updatedBucket);

        restBucketMockMvc.perform(put("/api/buckets")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(bucketDTO)))
            .andExpect(status().isOk());

        // Validate the Bucket in the database
        List<Bucket> bucketList = bucketRepository.findAll();
        assertThat(bucketList).hasSize(databaseSizeBeforeUpdate);
        Bucket testBucket = bucketList.get(bucketList.size() - 1);
        assertThat(testBucket.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testBucket.getDate()).isEqualTo(UPDATED_DATE);
        assertThat(testBucket.getSum()).isEqualTo(UPDATED_SUM);
        assertThat(testBucket.getOrderNumber()).isEqualTo(UPDATED_ORDER_NUMBER);
        assertThat(testBucket.getCount()).isEqualTo(UPDATED_COUNT);
        assertThat(testBucket.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testBucket.getConsignmentNote()).isEqualTo(UPDATED_CONSIGNMENT_NOTE);

        // Validate the Bucket in Elasticsearch
        Bucket bucketEs = bucketSearchRepository.findOne(testBucket.getId());
        assertThat(bucketEs).isEqualToComparingFieldByField(testBucket);
    }

    @Test
    @Transactional
    public void updateNonExistingBucket() throws Exception {
        int databaseSizeBeforeUpdate = bucketRepository.findAll().size();

        // Create the Bucket
        BucketDTO bucketDTO = bucketMapper.toDto(bucket);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restBucketMockMvc.perform(put("/api/buckets")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(bucketDTO)))
            .andExpect(status().isCreated());

        // Validate the Bucket in the database
        List<Bucket> bucketList = bucketRepository.findAll();
        assertThat(bucketList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteBucket() throws Exception {
        // Initialize the database
        bucketRepository.saveAndFlush(bucket);
        bucketSearchRepository.save(bucket);
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
        bucketRepository.saveAndFlush(bucket);
        bucketSearchRepository.save(bucket);

        // Search the bucket
        restBucketMockMvc.perform(get("/api/_search/buckets?query=id:" + bucket.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(bucket.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].date").value(hasItem(sameInstant(DEFAULT_DATE))))
            .andExpect(jsonPath("$.[*].sum").value(hasItem(DEFAULT_SUM.intValue())))
            .andExpect(jsonPath("$.[*].orderNumber").value(hasItem(DEFAULT_ORDER_NUMBER)))
            .andExpect(jsonPath("$.[*].count").value(hasItem(DEFAULT_COUNT)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].consignmentNote").value(hasItem(DEFAULT_CONSIGNMENT_NOTE.toString())));
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
}
