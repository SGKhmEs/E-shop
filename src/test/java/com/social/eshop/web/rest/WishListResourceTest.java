package com.social.eshop.web.rest;

import com.social.eshop.EshopApp;
import com.social.eshop.domain.WishList;
import com.social.eshop.repository.WishListRepository;
import com.social.eshop.repository.search.WishListSearchRepository;
import com.social.eshop.service.WishListService;
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

@RunWith(SpringRunner.class)
@SpringBootTest(classes = EshopApp.class)
public class WishListResourceTest {

    private static final LocalDate DEFAULT_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE = LocalDate.now(ZoneId.systemDefault());

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

    public static WishList createEntity(EntityManager em) {
        WishList wishList = new WishList()
            .date(DEFAULT_DATE);
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
        assertThat(testWishList.getDate()).isEqualTo(DEFAULT_DATE);

        // Validate the WishList in Elasticsearch
        WishList wishListEs = wishListSearchRepository.findOne(testWishList.getId());
        assertThat(wishListEs).isEqualToComparingFieldByField(testWishList);
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
            .andExpect(jsonPath("$.[*].date").value(hasItem(DEFAULT_DATE.toString())));
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
            .andExpect(jsonPath("$.date").value(DEFAULT_DATE.toString()));
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
            .date(UPDATED_DATE);
        WishListDTO wishListDTO = wishListMapper.toDto(updatedWishList);

        restWishListMockMvc.perform(put("/api/wish-lists")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(wishListDTO)))
            .andExpect(status().isOk());

        // Validate the WishList in the database
        List<WishList> wishListList = wishListRepository.findAll();
        assertThat(wishListList).hasSize(databaseSizeBeforeUpdate);
        WishList testWishList = wishListList.get(wishListList.size() - 1);
        assertThat(testWishList.getDate()).isEqualTo(UPDATED_DATE);

        // Validate the WishList in Elasticsearch
        WishList wishListEs = wishListSearchRepository.findOne(testWishList.getId());
        assertThat(wishListEs).isEqualToComparingFieldByField(testWishList);
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
            .andExpect(jsonPath("$.[*].date").value(hasItem(DEFAULT_DATE.toString())));
    }
}
