package com.social.eshop.web.rest;

import com.social.eshop.EshopApp;

import com.social.eshop.domain.HistoryOrder;
import com.social.eshop.repository.HistoryOrderRepository;
import com.social.eshop.service.HistoryOrderService;
import com.social.eshop.repository.search.HistoryOrderSearchRepository;
import com.social.eshop.service.dto.HistoryOrderDTO;
import com.social.eshop.service.mapper.HistoryOrderMapper;
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
 * Test class for the HistoryOrderResource REST controller.
 *
 * @see HistoryOrderResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = EshopApp.class)
public class HistoryOrderResourceIntTest {

    private static final Integer DEFAULT_ORDER_NUMBER = 1;
    private static final Integer UPDATED_ORDER_NUMBER = 2;

    private static final ZonedDateTime DEFAULT_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final Integer DEFAULT_COUNT = 1;
    private static final Integer UPDATED_COUNT = 2;

    private static final Double DEFAULT_SUM = 1D;
    private static final Double UPDATED_SUM = 2D;

    private static final Status DEFAULT_STATUS = Status.SUCCESS;
    private static final Status UPDATED_STATUS = Status.FAILURE;

    private static final String DEFAULT_CONSIGNMENT_NOTE = "AAAAAAAAAA";
    private static final String UPDATED_CONSIGNMENT_NOTE = "BBBBBBBBBB";

    @Autowired
    private HistoryOrderRepository historyOrderRepository;

    @Autowired
    private HistoryOrderMapper historyOrderMapper;

    @Autowired
    private HistoryOrderService historyOrderService;

    @Autowired
    private HistoryOrderSearchRepository historyOrderSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restHistoryOrderMockMvc;

    private HistoryOrder historyOrder;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        HistoryOrderResource historyOrderResource = new HistoryOrderResource(historyOrderService);
        this.restHistoryOrderMockMvc = MockMvcBuilders.standaloneSetup(historyOrderResource)
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
    public static HistoryOrder createEntity(EntityManager em) {
        HistoryOrder historyOrder = new HistoryOrder()
            .orderNumber(DEFAULT_ORDER_NUMBER)
            .date(DEFAULT_DATE)
            .count(DEFAULT_COUNT)
            .sum(DEFAULT_SUM)
            .status(DEFAULT_STATUS)
            .consignmentNote(DEFAULT_CONSIGNMENT_NOTE);
        return historyOrder;
    }

    @Before
    public void initTest() {
        historyOrderSearchRepository.deleteAll();
        historyOrder = createEntity(em);
    }

    @Test
    @Transactional
    public void createHistoryOrder() throws Exception {
        int databaseSizeBeforeCreate = historyOrderRepository.findAll().size();

        // Create the HistoryOrder
        HistoryOrderDTO historyOrderDTO = historyOrderMapper.toDto(historyOrder);
        restHistoryOrderMockMvc.perform(post("/api/history-orders")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(historyOrderDTO)))
            .andExpect(status().isCreated());

        // Validate the HistoryOrder in the database
        List<HistoryOrder> historyOrderList = historyOrderRepository.findAll();
        assertThat(historyOrderList).hasSize(databaseSizeBeforeCreate + 1);
        HistoryOrder testHistoryOrder = historyOrderList.get(historyOrderList.size() - 1);
        assertThat(testHistoryOrder.getOrderNumber()).isEqualTo(DEFAULT_ORDER_NUMBER);
        assertThat(testHistoryOrder.getDate()).isEqualTo(DEFAULT_DATE);
        assertThat(testHistoryOrder.getCount()).isEqualTo(DEFAULT_COUNT);
        assertThat(testHistoryOrder.getSum()).isEqualTo(DEFAULT_SUM);
        assertThat(testHistoryOrder.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testHistoryOrder.getConsignmentNote()).isEqualTo(DEFAULT_CONSIGNMENT_NOTE);

        // Validate the HistoryOrder in Elasticsearch
        HistoryOrder historyOrderEs = historyOrderSearchRepository.findOne(testHistoryOrder.getId());
        assertThat(historyOrderEs).isEqualToComparingFieldByField(testHistoryOrder);
    }

    @Test
    @Transactional
    public void createHistoryOrderWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = historyOrderRepository.findAll().size();

        // Create the HistoryOrder with an existing ID
        historyOrder.setId(1L);
        HistoryOrderDTO historyOrderDTO = historyOrderMapper.toDto(historyOrder);

        // An entity with an existing ID cannot be created, so this API call must fail
        restHistoryOrderMockMvc.perform(post("/api/history-orders")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(historyOrderDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<HistoryOrder> historyOrderList = historyOrderRepository.findAll();
        assertThat(historyOrderList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllHistoryOrders() throws Exception {
        // Initialize the database
        historyOrderRepository.saveAndFlush(historyOrder);

        // Get all the historyOrderList
        restHistoryOrderMockMvc.perform(get("/api/history-orders?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(historyOrder.getId().intValue())))
            .andExpect(jsonPath("$.[*].orderNumber").value(hasItem(DEFAULT_ORDER_NUMBER)))
            .andExpect(jsonPath("$.[*].date").value(hasItem(sameInstant(DEFAULT_DATE))))
            .andExpect(jsonPath("$.[*].count").value(hasItem(DEFAULT_COUNT)))
            .andExpect(jsonPath("$.[*].sum").value(hasItem(DEFAULT_SUM.doubleValue())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].consignmentNote").value(hasItem(DEFAULT_CONSIGNMENT_NOTE.toString())));
    }

    @Test
    @Transactional
    public void getHistoryOrder() throws Exception {
        // Initialize the database
        historyOrderRepository.saveAndFlush(historyOrder);

        // Get the historyOrder
        restHistoryOrderMockMvc.perform(get("/api/history-orders/{id}", historyOrder.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(historyOrder.getId().intValue()))
            .andExpect(jsonPath("$.orderNumber").value(DEFAULT_ORDER_NUMBER))
            .andExpect(jsonPath("$.date").value(sameInstant(DEFAULT_DATE)))
            .andExpect(jsonPath("$.count").value(DEFAULT_COUNT))
            .andExpect(jsonPath("$.sum").value(DEFAULT_SUM.doubleValue()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()))
            .andExpect(jsonPath("$.consignmentNote").value(DEFAULT_CONSIGNMENT_NOTE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingHistoryOrder() throws Exception {
        // Get the historyOrder
        restHistoryOrderMockMvc.perform(get("/api/history-orders/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateHistoryOrder() throws Exception {
        // Initialize the database
        historyOrderRepository.saveAndFlush(historyOrder);
        historyOrderSearchRepository.save(historyOrder);
        int databaseSizeBeforeUpdate = historyOrderRepository.findAll().size();

        // Update the historyOrder
        HistoryOrder updatedHistoryOrder = historyOrderRepository.findOne(historyOrder.getId());
        updatedHistoryOrder
            .orderNumber(UPDATED_ORDER_NUMBER)
            .date(UPDATED_DATE)
            .count(UPDATED_COUNT)
            .sum(UPDATED_SUM)
            .status(UPDATED_STATUS)
            .consignmentNote(UPDATED_CONSIGNMENT_NOTE);
        HistoryOrderDTO historyOrderDTO = historyOrderMapper.toDto(updatedHistoryOrder);

        restHistoryOrderMockMvc.perform(put("/api/history-orders")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(historyOrderDTO)))
            .andExpect(status().isOk());

        // Validate the HistoryOrder in the database
        List<HistoryOrder> historyOrderList = historyOrderRepository.findAll();
        assertThat(historyOrderList).hasSize(databaseSizeBeforeUpdate);
        HistoryOrder testHistoryOrder = historyOrderList.get(historyOrderList.size() - 1);
        assertThat(testHistoryOrder.getOrderNumber()).isEqualTo(UPDATED_ORDER_NUMBER);
        assertThat(testHistoryOrder.getDate()).isEqualTo(UPDATED_DATE);
        assertThat(testHistoryOrder.getCount()).isEqualTo(UPDATED_COUNT);
        assertThat(testHistoryOrder.getSum()).isEqualTo(UPDATED_SUM);
        assertThat(testHistoryOrder.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testHistoryOrder.getConsignmentNote()).isEqualTo(UPDATED_CONSIGNMENT_NOTE);

        // Validate the HistoryOrder in Elasticsearch
        HistoryOrder historyOrderEs = historyOrderSearchRepository.findOne(testHistoryOrder.getId());
        assertThat(historyOrderEs).isEqualToComparingFieldByField(testHistoryOrder);
    }

    @Test
    @Transactional
    public void updateNonExistingHistoryOrder() throws Exception {
        int databaseSizeBeforeUpdate = historyOrderRepository.findAll().size();

        // Create the HistoryOrder
        HistoryOrderDTO historyOrderDTO = historyOrderMapper.toDto(historyOrder);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restHistoryOrderMockMvc.perform(put("/api/history-orders")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(historyOrderDTO)))
            .andExpect(status().isCreated());

        // Validate the HistoryOrder in the database
        List<HistoryOrder> historyOrderList = historyOrderRepository.findAll();
        assertThat(historyOrderList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteHistoryOrder() throws Exception {
        // Initialize the database
        historyOrderRepository.saveAndFlush(historyOrder);
        historyOrderSearchRepository.save(historyOrder);
        int databaseSizeBeforeDelete = historyOrderRepository.findAll().size();

        // Get the historyOrder
        restHistoryOrderMockMvc.perform(delete("/api/history-orders/{id}", historyOrder.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate Elasticsearch is empty
        boolean historyOrderExistsInEs = historyOrderSearchRepository.exists(historyOrder.getId());
        assertThat(historyOrderExistsInEs).isFalse();

        // Validate the database is empty
        List<HistoryOrder> historyOrderList = historyOrderRepository.findAll();
        assertThat(historyOrderList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void searchHistoryOrder() throws Exception {
        // Initialize the database
        historyOrderRepository.saveAndFlush(historyOrder);
        historyOrderSearchRepository.save(historyOrder);

        // Search the historyOrder
        restHistoryOrderMockMvc.perform(get("/api/_search/history-orders?query=id:" + historyOrder.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(historyOrder.getId().intValue())))
            .andExpect(jsonPath("$.[*].orderNumber").value(hasItem(DEFAULT_ORDER_NUMBER)))
            .andExpect(jsonPath("$.[*].date").value(hasItem(sameInstant(DEFAULT_DATE))))
            .andExpect(jsonPath("$.[*].count").value(hasItem(DEFAULT_COUNT)))
            .andExpect(jsonPath("$.[*].sum").value(hasItem(DEFAULT_SUM.doubleValue())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].consignmentNote").value(hasItem(DEFAULT_CONSIGNMENT_NOTE.toString())));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(HistoryOrder.class);
        HistoryOrder historyOrder1 = new HistoryOrder();
        historyOrder1.setId(1L);
        HistoryOrder historyOrder2 = new HistoryOrder();
        historyOrder2.setId(historyOrder1.getId());
        assertThat(historyOrder1).isEqualTo(historyOrder2);
        historyOrder2.setId(2L);
        assertThat(historyOrder1).isNotEqualTo(historyOrder2);
        historyOrder1.setId(null);
        assertThat(historyOrder1).isNotEqualTo(historyOrder2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(HistoryOrderDTO.class);
        HistoryOrderDTO historyOrderDTO1 = new HistoryOrderDTO();
        historyOrderDTO1.setId(1L);
        HistoryOrderDTO historyOrderDTO2 = new HistoryOrderDTO();
        assertThat(historyOrderDTO1).isNotEqualTo(historyOrderDTO2);
        historyOrderDTO2.setId(historyOrderDTO1.getId());
        assertThat(historyOrderDTO1).isEqualTo(historyOrderDTO2);
        historyOrderDTO2.setId(2L);
        assertThat(historyOrderDTO1).isNotEqualTo(historyOrderDTO2);
        historyOrderDTO1.setId(null);
        assertThat(historyOrderDTO1).isNotEqualTo(historyOrderDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(historyOrderMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(historyOrderMapper.fromId(null)).isNull();
    }
}
