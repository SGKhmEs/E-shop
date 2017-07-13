package com.social.eshop.web.rest;

import com.social.eshop.EshopApp;
import com.social.eshop.domain.SubCategory;
import com.social.eshop.repository.SubCategoryRepository;
import com.social.eshop.repository.search.SubCategorySearchRepository;
import com.social.eshop.service.SubCategoryService;
import com.social.eshop.service.dto.SubCategoryDTO;
import com.social.eshop.service.mapper.SubCategoryMapper;
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
public class SubCategoryResourceTest {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    @Autowired
    private SubCategoryRepository subCategoryRepository;

    @Autowired
    private SubCategoryMapper subCategoryMapper;

    @Autowired
    private SubCategoryService subCategoryService;

    @Autowired
    private SubCategorySearchRepository subCategorySearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restSubCategoryMockMvc;

    private SubCategory subCategory;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        SubCategoryResource subCategoryResource = new SubCategoryResource(subCategoryService);
        this.restSubCategoryMockMvc = MockMvcBuilders.standaloneSetup(subCategoryResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    public static SubCategory createEntity(EntityManager em) {
        SubCategory subCategory = new SubCategory()
            .name(DEFAULT_NAME);
        return subCategory;
    }

    @Before
    public void initTest() {
        subCategorySearchRepository.deleteAll();
        subCategory = createEntity(em);
    }

    @Test
    @Transactional
    public void createSubCategory() throws Exception {
        int databaseSizeBeforeCreate = subCategoryRepository.findAll().size();

        // Create the SubCategory
        SubCategoryDTO subCategoryDTO = subCategoryMapper.toDto(subCategory);
        restSubCategoryMockMvc.perform(post("/api/sub-categories")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(subCategoryDTO)))
            .andExpect(status().isCreated());

        // Validate the SubCategory in the database
        List<SubCategory> subCategoryList = subCategoryRepository.findAll();
        assertThat(subCategoryList).hasSize(databaseSizeBeforeCreate + 1);
        SubCategory testSubCategory = subCategoryList.get(subCategoryList.size() - 1);
        assertThat(testSubCategory.getName()).isEqualTo(DEFAULT_NAME);

        // Validate the SubCategory in Elasticsearch
        SubCategory subCategoryEs = subCategorySearchRepository.findOne(testSubCategory.getId());
        assertThat(subCategoryEs).isEqualToComparingFieldByField(testSubCategory);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = subCategoryRepository.findAll().size();
        // set the field null
        subCategory.setName(null);

        // Create the SubCategory, which fails.
        SubCategoryDTO subCategoryDTO = subCategoryMapper.toDto(subCategory);

        restSubCategoryMockMvc.perform(post("/api/sub-categories")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(subCategoryDTO)))
            .andExpect(status().isBadRequest());

        List<SubCategory> subCategoryList = subCategoryRepository.findAll();
        assertThat(subCategoryList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllSubCategories() throws Exception {
        // Initialize the database
        subCategoryRepository.saveAndFlush(subCategory);

        // Get all the subCategoryList
        restSubCategoryMockMvc.perform(get("/api/sub-categories?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(subCategory.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())));
    }

    @Test
    @Transactional
    public void getSubCategory() throws Exception {
        // Initialize the database
        subCategoryRepository.saveAndFlush(subCategory);

        // Get the subCategory
        restSubCategoryMockMvc.perform(get("/api/sub-categories/{id}", subCategory.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(subCategory.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()));
    }

    @Test
    @Transactional
    public void updateSubCategory() throws Exception {
        // Initialize the database
        subCategoryRepository.saveAndFlush(subCategory);
        subCategorySearchRepository.save(subCategory);
        int databaseSizeBeforeUpdate = subCategoryRepository.findAll().size();

        // Update the subCategory
        SubCategory updatedSubCategory = subCategoryRepository.findOne(subCategory.getId());
        updatedSubCategory
            .name(UPDATED_NAME);
        SubCategoryDTO subCategoryDTO = subCategoryMapper.toDto(updatedSubCategory);

        restSubCategoryMockMvc.perform(put("/api/sub-categories")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(subCategoryDTO)))
            .andExpect(status().isOk());

        // Validate the SubCategory in the database
        List<SubCategory> subCategoryList = subCategoryRepository.findAll();
        assertThat(subCategoryList).hasSize(databaseSizeBeforeUpdate);
        SubCategory testSubCategory = subCategoryList.get(subCategoryList.size() - 1);
        assertThat(testSubCategory.getName()).isEqualTo(UPDATED_NAME);

        // Validate the SubCategory in Elasticsearch
        SubCategory subCategoryEs = subCategorySearchRepository.findOne(testSubCategory.getId());
        assertThat(subCategoryEs).isEqualToComparingFieldByField(testSubCategory);
    }

    @Test
    @Transactional
    public void deleteSubCategory() throws Exception {
        // Initialize the database
        subCategoryRepository.saveAndFlush(subCategory);
        subCategorySearchRepository.save(subCategory);
        int databaseSizeBeforeDelete = subCategoryRepository.findAll().size();

        // Get the subCategory
        restSubCategoryMockMvc.perform(delete("/api/sub-categories/{id}", subCategory.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate Elasticsearch is empty
        boolean subCategoryExistsInEs = subCategorySearchRepository.exists(subCategory.getId());
        assertThat(subCategoryExistsInEs).isFalse();

        // Validate the database is empty
        List<SubCategory> subCategoryList = subCategoryRepository.findAll();
        assertThat(subCategoryList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void searchSubCategory() throws Exception {
        // Initialize the database
        subCategoryRepository.saveAndFlush(subCategory);
        subCategorySearchRepository.save(subCategory);

        // Search the subCategory
        restSubCategoryMockMvc.perform(get("/api/_search/sub-categories?query=id:" + subCategory.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(subCategory.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())));
    }
}
