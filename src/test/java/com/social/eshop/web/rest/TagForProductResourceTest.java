package com.social.eshop.web.rest;

import com.social.eshop.EshopApp;
import com.social.eshop.domain.TagForProduct;
import com.social.eshop.repository.TagForProductRepository;
import com.social.eshop.repository.search.TagForProductSearchRepository;
import com.social.eshop.service.TagForProductService;
import com.social.eshop.service.dto.TagForProductDTO;
import com.social.eshop.service.mapper.TagForProductMapper;
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
public class TagForProductResourceTest {

    @Autowired
    private TagForProductRepository tagForProductRepository;

    @Autowired
    private TagForProductMapper tagForProductMapper;

    @Autowired
    private TagForProductService tagForProductService;

    @Autowired
    private TagForProductSearchRepository tagForProductSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restTagForProductMockMvc;

    private TagForProduct tagForProduct;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        TagForProductResource tagForProductResource = new TagForProductResource(tagForProductService);
        this.restTagForProductMockMvc = MockMvcBuilders.standaloneSetup(tagForProductResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    public static TagForProduct createEntity(EntityManager em) {
        TagForProduct tagForProduct = new TagForProduct();
        return tagForProduct;
    }

    @Before
    public void initTest() {
        tagForProductSearchRepository.deleteAll();
        tagForProduct = createEntity(em);
    }

    @Test
    @Transactional
    public void createTagForProduct() throws Exception {
        int databaseSizeBeforeCreate = tagForProductRepository.findAll().size();

        // Create the TagForProduct
        TagForProductDTO tagForProductDTO = tagForProductMapper.toDto(tagForProduct);
        restTagForProductMockMvc.perform(post("/api/tag-for-products")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tagForProductDTO)))
            .andExpect(status().isCreated());

        // Validate the TagForProduct in the database
        List<TagForProduct> tagForProductList = tagForProductRepository.findAll();
        assertThat(tagForProductList).hasSize(databaseSizeBeforeCreate + 1);
        TagForProduct testTagForProduct = tagForProductList.get(tagForProductList.size() - 1);

        // Validate the TagForProduct in Elasticsearch
        TagForProduct tagForProductEs = tagForProductSearchRepository.findOne(testTagForProduct.getId());
        assertThat(tagForProductEs).isEqualToComparingFieldByField(testTagForProduct);
    }

    @Test
    @Transactional
    public void getAllTagForProducts() throws Exception {
        // Initialize the database
        tagForProductRepository.saveAndFlush(tagForProduct);

        // Get all the tagForProductList
        restTagForProductMockMvc.perform(get("/api/tag-for-products?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tagForProduct.getId().intValue())));
    }

    @Test
    @Transactional
    public void getTagForProduct() throws Exception {
        // Initialize the database
        tagForProductRepository.saveAndFlush(tagForProduct);

        // Get the tagForProduct
        restTagForProductMockMvc.perform(get("/api/tag-for-products/{id}", tagForProduct.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(tagForProduct.getId().intValue()));
    }

    @Test
    @Transactional
    public void updateTagForProduct() throws Exception {
        // Initialize the database
        tagForProductRepository.saveAndFlush(tagForProduct);
        tagForProductSearchRepository.save(tagForProduct);
        int databaseSizeBeforeUpdate = tagForProductRepository.findAll().size();

        // Update the tagForProduct
        TagForProduct updatedTagForProduct = tagForProductRepository.findOne(tagForProduct.getId());
        TagForProductDTO tagForProductDTO = tagForProductMapper.toDto(updatedTagForProduct);

        restTagForProductMockMvc.perform(put("/api/tag-for-products")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tagForProductDTO)))
            .andExpect(status().isOk());

        // Validate the TagForProduct in the database
        List<TagForProduct> tagForProductList = tagForProductRepository.findAll();
        assertThat(tagForProductList).hasSize(databaseSizeBeforeUpdate);
        TagForProduct testTagForProduct = tagForProductList.get(tagForProductList.size() - 1);

        // Validate the TagForProduct in Elasticsearch
        TagForProduct tagForProductEs = tagForProductSearchRepository.findOne(testTagForProduct.getId());
        assertThat(tagForProductEs).isEqualToComparingFieldByField(testTagForProduct);
    }

    @Test
    @Transactional
    public void deleteTagForProduct() throws Exception {
        // Initialize the database
        tagForProductRepository.saveAndFlush(tagForProduct);
        tagForProductSearchRepository.save(tagForProduct);
        int databaseSizeBeforeDelete = tagForProductRepository.findAll().size();

        // Get the tagForProduct
        restTagForProductMockMvc.perform(delete("/api/tag-for-products/{id}", tagForProduct.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate Elasticsearch is empty
        boolean tagForProductExistsInEs = tagForProductSearchRepository.exists(tagForProduct.getId());
        assertThat(tagForProductExistsInEs).isFalse();

        // Validate the database is empty
        List<TagForProduct> tagForProductList = tagForProductRepository.findAll();
        assertThat(tagForProductList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void searchTagForProduct() throws Exception {
        // Initialize the database
        tagForProductRepository.saveAndFlush(tagForProduct);
        tagForProductSearchRepository.save(tagForProduct);

        // Search the tagForProduct
        restTagForProductMockMvc.perform(get("/api/_search/tag-for-products?query=id:" + tagForProduct.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tagForProduct.getId().intValue())));
    }
}
