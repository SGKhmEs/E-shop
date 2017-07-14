package com.social.eshop.web.rest;

import com.social.eshop.EshopApp;
import com.social.eshop.domain.StaticPages;
import com.social.eshop.repository.StaticPagesRepository;
import com.social.eshop.repository.search.StaticPagesSearchRepository;
import com.social.eshop.service.StaticPagesService;
import com.social.eshop.service.dto.StaticPagesDTO;
import com.social.eshop.service.mapper.StaticPagesMapper;
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
public class StaticPagesResourceTest {

    @Autowired
    private StaticPagesRepository staticPagesRepository;

    @Autowired
    private StaticPagesMapper staticPagesMapper;

    @Autowired
    private StaticPagesService staticPagesService;

    @Autowired
    private StaticPagesSearchRepository staticPagesSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restStaticPagesMockMvc;

    private StaticPages staticPages;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        StaticPagesResource staticPagesResource = new StaticPagesResource(staticPagesService);
        this.restStaticPagesMockMvc = MockMvcBuilders.standaloneSetup(staticPagesResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    public static StaticPages createEntity(EntityManager em) {
        StaticPages staticPages = new StaticPages();
        return staticPages;
    }

    @Before
    public void initTest() {
        staticPagesSearchRepository.deleteAll();
        staticPages = createEntity(em);
    }

    @Test
    @Transactional
    public void createStaticPages() throws Exception {
        int databaseSizeBeforeCreate = staticPagesRepository.findAll().size();

        // Create the StaticPages
        StaticPagesDTO staticPagesDTO = staticPagesMapper.toDto(staticPages);
        restStaticPagesMockMvc.perform(post("/api/static-pages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(staticPagesDTO)))
            .andExpect(status().isCreated());

        // Validate the StaticPages in the database
        List<StaticPages> staticPagesList = staticPagesRepository.findAll();
        assertThat(staticPagesList).hasSize(databaseSizeBeforeCreate + 1);
        StaticPages testStaticPages = staticPagesList.get(staticPagesList.size() - 1);

        // Validate the StaticPages in Elasticsearch
        StaticPages staticPagesEs = staticPagesSearchRepository.findOne(testStaticPages.getId());
        assertThat(staticPagesEs).isEqualToComparingFieldByField(testStaticPages);
    }

    @Test
    @Transactional
    public void getAllStaticPages() throws Exception {
        // Initialize the database
        staticPagesRepository.saveAndFlush(staticPages);

        // Get all the staticPagesList
        restStaticPagesMockMvc.perform(get("/api/static-pages?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(staticPages.getId().intValue())));
    }

    @Test
    @Transactional
    public void getStaticPages() throws Exception {
        // Initialize the database
        staticPagesRepository.saveAndFlush(staticPages);

        // Get the staticPages
        restStaticPagesMockMvc.perform(get("/api/static-pages/{id}", staticPages.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(staticPages.getId().intValue()));
    }

    @Test
    @Transactional
    public void updateStaticPages() throws Exception {
        // Initialize the database
        staticPagesRepository.saveAndFlush(staticPages);
        staticPagesSearchRepository.save(staticPages);
        int databaseSizeBeforeUpdate = staticPagesRepository.findAll().size();

        // Update the staticPages
        StaticPages updatedStaticPages = staticPagesRepository.findOne(staticPages.getId());
        StaticPagesDTO staticPagesDTO = staticPagesMapper.toDto(updatedStaticPages);

        restStaticPagesMockMvc.perform(put("/api/static-pages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(staticPagesDTO)))
            .andExpect(status().isOk());

        // Validate the StaticPages in the database
        List<StaticPages> staticPagesList = staticPagesRepository.findAll();
        assertThat(staticPagesList).hasSize(databaseSizeBeforeUpdate);
        StaticPages testStaticPages = staticPagesList.get(staticPagesList.size() - 1);

        // Validate the StaticPages in Elasticsearch
        StaticPages staticPagesEs = staticPagesSearchRepository.findOne(testStaticPages.getId());
        assertThat(staticPagesEs).isEqualToComparingFieldByField(testStaticPages);
    }

    @Test
    @Transactional
    public void deleteStaticPages() throws Exception {
        // Initialize the database
        staticPagesRepository.saveAndFlush(staticPages);
        staticPagesSearchRepository.save(staticPages);
        int databaseSizeBeforeDelete = staticPagesRepository.findAll().size();

        // Get the staticPages
        restStaticPagesMockMvc.perform(delete("/api/static-pages/{id}", staticPages.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate Elasticsearch is empty
        boolean staticPagesExistsInEs = staticPagesSearchRepository.exists(staticPages.getId());
        assertThat(staticPagesExistsInEs).isFalse();

        // Validate the database is empty
        List<StaticPages> staticPagesList = staticPagesRepository.findAll();
        assertThat(staticPagesList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void searchStaticPages() throws Exception {
        // Initialize the database
        staticPagesRepository.saveAndFlush(staticPages);
        staticPagesSearchRepository.save(staticPages);

        // Search the staticPages
        restStaticPagesMockMvc.perform(get("/api/_search/static-pages?query=id:" + staticPages.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(staticPages.getId().intValue())));
    }
}
