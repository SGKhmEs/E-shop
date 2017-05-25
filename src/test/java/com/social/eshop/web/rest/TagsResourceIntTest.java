package com.social.eshop.web.rest;

import com.social.eshop.EshopApp;

import com.social.eshop.domain.Tags;
import com.social.eshop.repository.TagsRepository;
import com.social.eshop.service.TagsService;
import com.social.eshop.repository.search.TagsSearchRepository;
import com.social.eshop.service.dto.TagsDTO;
import com.social.eshop.service.mapper.TagsMapper;
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
 * Test class for the TagsResource REST controller.
 *
 * @see TagsResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = EshopApp.class)
public class TagsResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    @Autowired
    private TagsRepository tagsRepository;

    @Autowired
    private TagsMapper tagsMapper;

    @Autowired
    private TagsService tagsService;

    @Autowired
    private TagsSearchRepository tagsSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restTagsMockMvc;

    private Tags tags;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        TagsResource tagsResource = new TagsResource(tagsService);
        this.restTagsMockMvc = MockMvcBuilders.standaloneSetup(tagsResource)
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
    public static Tags createEntity(EntityManager em) {
        Tags tags = new Tags()
            .name(DEFAULT_NAME);
        return tags;
    }

    @Before
    public void initTest() {
        tagsSearchRepository.deleteAll();
        tags = createEntity(em);
    }

    @Test
    @Transactional
    public void createTags() throws Exception {
        int databaseSizeBeforeCreate = tagsRepository.findAll().size();

        // Create the Tags
        TagsDTO tagsDTO = tagsMapper.toDto(tags);
        restTagsMockMvc.perform(post("/api/tags")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tagsDTO)))
            .andExpect(status().isCreated());

        // Validate the Tags in the database
        List<Tags> tagsList = tagsRepository.findAll();
        assertThat(tagsList).hasSize(databaseSizeBeforeCreate + 1);
        Tags testTags = tagsList.get(tagsList.size() - 1);
        assertThat(testTags.getName()).isEqualTo(DEFAULT_NAME);

        // Validate the Tags in Elasticsearch
        Tags tagsEs = tagsSearchRepository.findOne(testTags.getId());
        assertThat(tagsEs).isEqualToComparingFieldByField(testTags);
    }

    @Test
    @Transactional
    public void createTagsWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = tagsRepository.findAll().size();

        // Create the Tags with an existing ID
        tags.setId(1L);
        TagsDTO tagsDTO = tagsMapper.toDto(tags);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTagsMockMvc.perform(post("/api/tags")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tagsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Tags> tagsList = tagsRepository.findAll();
        assertThat(tagsList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = tagsRepository.findAll().size();
        // set the field null
        tags.setName(null);

        // Create the Tags, which fails.
        TagsDTO tagsDTO = tagsMapper.toDto(tags);

        restTagsMockMvc.perform(post("/api/tags")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tagsDTO)))
            .andExpect(status().isBadRequest());

        List<Tags> tagsList = tagsRepository.findAll();
        assertThat(tagsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllTags() throws Exception {
        // Initialize the database
        tagsRepository.saveAndFlush(tags);

        // Get all the tagsList
        restTagsMockMvc.perform(get("/api/tags?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tags.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())));
    }

    @Test
    @Transactional
    public void getTags() throws Exception {
        // Initialize the database
        tagsRepository.saveAndFlush(tags);

        // Get the tags
        restTagsMockMvc.perform(get("/api/tags/{id}", tags.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(tags.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingTags() throws Exception {
        // Get the tags
        restTagsMockMvc.perform(get("/api/tags/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTags() throws Exception {
        // Initialize the database
        tagsRepository.saveAndFlush(tags);
        tagsSearchRepository.save(tags);
        int databaseSizeBeforeUpdate = tagsRepository.findAll().size();

        // Update the tags
        Tags updatedTags = tagsRepository.findOne(tags.getId());
        updatedTags
            .name(UPDATED_NAME);
        TagsDTO tagsDTO = tagsMapper.toDto(updatedTags);

        restTagsMockMvc.perform(put("/api/tags")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tagsDTO)))
            .andExpect(status().isOk());

        // Validate the Tags in the database
        List<Tags> tagsList = tagsRepository.findAll();
        assertThat(tagsList).hasSize(databaseSizeBeforeUpdate);
        Tags testTags = tagsList.get(tagsList.size() - 1);
        assertThat(testTags.getName()).isEqualTo(UPDATED_NAME);

        // Validate the Tags in Elasticsearch
        Tags tagsEs = tagsSearchRepository.findOne(testTags.getId());
        assertThat(tagsEs).isEqualToComparingFieldByField(testTags);
    }

    @Test
    @Transactional
    public void updateNonExistingTags() throws Exception {
        int databaseSizeBeforeUpdate = tagsRepository.findAll().size();

        // Create the Tags
        TagsDTO tagsDTO = tagsMapper.toDto(tags);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restTagsMockMvc.perform(put("/api/tags")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tagsDTO)))
            .andExpect(status().isCreated());

        // Validate the Tags in the database
        List<Tags> tagsList = tagsRepository.findAll();
        assertThat(tagsList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteTags() throws Exception {
        // Initialize the database
        tagsRepository.saveAndFlush(tags);
        tagsSearchRepository.save(tags);
        int databaseSizeBeforeDelete = tagsRepository.findAll().size();

        // Get the tags
        restTagsMockMvc.perform(delete("/api/tags/{id}", tags.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate Elasticsearch is empty
        boolean tagsExistsInEs = tagsSearchRepository.exists(tags.getId());
        assertThat(tagsExistsInEs).isFalse();

        // Validate the database is empty
        List<Tags> tagsList = tagsRepository.findAll();
        assertThat(tagsList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void searchTags() throws Exception {
        // Initialize the database
        tagsRepository.saveAndFlush(tags);
        tagsSearchRepository.save(tags);

        // Search the tags
        restTagsMockMvc.perform(get("/api/_search/tags?query=id:" + tags.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tags.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Tags.class);
        Tags tags1 = new Tags();
        tags1.setId(1L);
        Tags tags2 = new Tags();
        tags2.setId(tags1.getId());
        assertThat(tags1).isEqualTo(tags2);
        tags2.setId(2L);
        assertThat(tags1).isNotEqualTo(tags2);
        tags1.setId(null);
        assertThat(tags1).isNotEqualTo(tags2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TagsDTO.class);
        TagsDTO tagsDTO1 = new TagsDTO();
        tagsDTO1.setId(1L);
        TagsDTO tagsDTO2 = new TagsDTO();
        assertThat(tagsDTO1).isNotEqualTo(tagsDTO2);
        tagsDTO2.setId(tagsDTO1.getId());
        assertThat(tagsDTO1).isEqualTo(tagsDTO2);
        tagsDTO2.setId(2L);
        assertThat(tagsDTO1).isNotEqualTo(tagsDTO2);
        tagsDTO1.setId(null);
        assertThat(tagsDTO1).isNotEqualTo(tagsDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(tagsMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(tagsMapper.fromId(null)).isNull();
    }
}
