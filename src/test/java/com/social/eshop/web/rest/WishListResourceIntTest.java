package com.social.eshop.web.rest;

import com.social.eshop.EshopApp;

import com.social.eshop.domain.WishList;
import com.social.eshop.repository.WishListRepository;
import com.social.eshop.service.WishListService;
import com.social.eshop.repository.search.WishListSearchRepository;
import com.social.eshop.service.dto.WishListDTO;
import com.social.eshop.service.mapper.WishListMapper;
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
 * Test class for the WishListResource REST controller.
 *
 * @see WishListResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = EshopApp.class)
public class WishListResourceIntTest {

    private static final String DEFAULT_WISHS_NAME = "AAAAAAAAAA";
    private static final String UPDATED_WISHS_NAME = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DATA = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATA = LocalDate.now(ZoneId.systemDefault());

    @Autowired
    private WishListRepository wishListRepository;

    @Autowired
    private WishListMapper wishListMapper;

    @Autowired
    private WishListService wishListService;

    @Autowired
    private WishListSearchRepository wishListSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restWishListMockMvc;

    private WishList wishList;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        WishListResource wishListResource = new WishListResource(wishListService);
        this.restWishListMockMvc = MockMvcBuilders.standaloneSetup(wishListResource)
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
    public static WishList createEntity(EntityManager em) {
        WishList wishList = new WishList()
            .wishsName(DEFAULT_WISHS_NAME)
            .data(DEFAULT_DATA);
        return wishList;
    }

    @Before
    public void initTest() {
        wishListSearchRepository.deleteAll();
        wishList = createEntity(em);
    }

    @Test
    @Transactional
    public void createWishList() throws Exception {
        int databaseSizeBeforeCreate = wishListRepository.findAll().size();

        // Create the WishList
        WishListDTO wishListDTO = wishListMapper.toDto(wishList);
        restWishListMockMvc.perform(post("/api/wish-lists")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(wishListDTO)))
            .andExpect(status().isCreated());

        // Validate the WishList in the database
        List<WishList> wishListList = wishListRepository.findAll();
        assertThat(wishListList).hasSize(databaseSizeBeforeCreate + 1);
        WishList testWishList = wishListList.get(wishListList.size() - 1);
        assertThat(testWishList.getWishsName()).isEqualTo(DEFAULT_WISHS_NAME);
        assertThat(testWishList.getData()).isEqualTo(DEFAULT_DATA);

        // Validate the WishList in Elasticsearch
        WishList wishListEs = wishListSearchRepository.findOne(testWishList.getId());
        assertThat(wishListEs).isEqualToComparingFieldByField(testWishList);
    }

    @Test
    @Transactional
    public void createWishListWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = wishListRepository.findAll().size();

        // Create the WishList with an existing ID
        wishList.setId(1L);
        WishListDTO wishListDTO = wishListMapper.toDto(wishList);

        // An entity with an existing ID cannot be created, so this API call must fail
        restWishListMockMvc.perform(post("/api/wish-lists")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(wishListDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<WishList> wishListList = wishListRepository.findAll();
        assertThat(wishListList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllWishLists() throws Exception {
        // Initialize the database
        wishListRepository.saveAndFlush(wishList);

        // Get all the wishListList
        restWishListMockMvc.perform(get("/api/wish-lists?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(wishList.getId().intValue())))
            .andExpect(jsonPath("$.[*].wishsName").value(hasItem(DEFAULT_WISHS_NAME.toString())))
            .andExpect(jsonPath("$.[*].data").value(hasItem(DEFAULT_DATA.toString())));
    }

    @Test
    @Transactional
    public void getWishList() throws Exception {
        // Initialize the database
        wishListRepository.saveAndFlush(wishList);

        // Get the wishList
        restWishListMockMvc.perform(get("/api/wish-lists/{id}", wishList.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(wishList.getId().intValue()))
            .andExpect(jsonPath("$.wishsName").value(DEFAULT_WISHS_NAME.toString()))
            .andExpect(jsonPath("$.data").value(DEFAULT_DATA.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingWishList() throws Exception {
        // Get the wishList
        restWishListMockMvc.perform(get("/api/wish-lists/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateWishList() throws Exception {
        // Initialize the database
        wishListRepository.saveAndFlush(wishList);
        wishListSearchRepository.save(wishList);
        int databaseSizeBeforeUpdate = wishListRepository.findAll().size();

        // Update the wishList
        WishList updatedWishList = wishListRepository.findOne(wishList.getId());
        updatedWishList
            .wishsName(UPDATED_WISHS_NAME)
            .data(UPDATED_DATA);
        WishListDTO wishListDTO = wishListMapper.toDto(updatedWishList);

        restWishListMockMvc.perform(put("/api/wish-lists")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(wishListDTO)))
            .andExpect(status().isOk());

        // Validate the WishList in the database
        List<WishList> wishListList = wishListRepository.findAll();
        assertThat(wishListList).hasSize(databaseSizeBeforeUpdate);
        WishList testWishList = wishListList.get(wishListList.size() - 1);
        assertThat(testWishList.getWishsName()).isEqualTo(UPDATED_WISHS_NAME);
        assertThat(testWishList.getData()).isEqualTo(UPDATED_DATA);

        // Validate the WishList in Elasticsearch
        WishList wishListEs = wishListSearchRepository.findOne(testWishList.getId());
        assertThat(wishListEs).isEqualToComparingFieldByField(testWishList);
    }

    @Test
    @Transactional
    public void updateNonExistingWishList() throws Exception {
        int databaseSizeBeforeUpdate = wishListRepository.findAll().size();

        // Create the WishList
        WishListDTO wishListDTO = wishListMapper.toDto(wishList);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restWishListMockMvc.perform(put("/api/wish-lists")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(wishListDTO)))
            .andExpect(status().isCreated());

        // Validate the WishList in the database
        List<WishList> wishListList = wishListRepository.findAll();
        assertThat(wishListList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteWishList() throws Exception {
        // Initialize the database
        wishListRepository.saveAndFlush(wishList);
        wishListSearchRepository.save(wishList);
        int databaseSizeBeforeDelete = wishListRepository.findAll().size();

        // Get the wishList
        restWishListMockMvc.perform(delete("/api/wish-lists/{id}", wishList.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate Elasticsearch is empty
        boolean wishListExistsInEs = wishListSearchRepository.exists(wishList.getId());
        assertThat(wishListExistsInEs).isFalse();

        // Validate the database is empty
        List<WishList> wishListList = wishListRepository.findAll();
        assertThat(wishListList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void searchWishList() throws Exception {
        // Initialize the database
        wishListRepository.saveAndFlush(wishList);
        wishListSearchRepository.save(wishList);

        // Search the wishList
        restWishListMockMvc.perform(get("/api/_search/wish-lists?query=id:" + wishList.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(wishList.getId().intValue())))
            .andExpect(jsonPath("$.[*].wishsName").value(hasItem(DEFAULT_WISHS_NAME.toString())))
            .andExpect(jsonPath("$.[*].data").value(hasItem(DEFAULT_DATA.toString())));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(WishList.class);
        WishList wishList1 = new WishList();
        wishList1.setId(1L);
        WishList wishList2 = new WishList();
        wishList2.setId(wishList1.getId());
        assertThat(wishList1).isEqualTo(wishList2);
        wishList2.setId(2L);
        assertThat(wishList1).isNotEqualTo(wishList2);
        wishList1.setId(null);
        assertThat(wishList1).isNotEqualTo(wishList2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(WishListDTO.class);
        WishListDTO wishListDTO1 = new WishListDTO();
        wishListDTO1.setId(1L);
        WishListDTO wishListDTO2 = new WishListDTO();
        assertThat(wishListDTO1).isNotEqualTo(wishListDTO2);
        wishListDTO2.setId(wishListDTO1.getId());
        assertThat(wishListDTO1).isEqualTo(wishListDTO2);
        wishListDTO2.setId(2L);
        assertThat(wishListDTO1).isNotEqualTo(wishListDTO2);
        wishListDTO1.setId(null);
        assertThat(wishListDTO1).isNotEqualTo(wishListDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(wishListMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(wishListMapper.fromId(null)).isNull();
    }
}
