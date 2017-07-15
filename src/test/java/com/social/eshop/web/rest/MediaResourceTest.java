package com.social.eshop.web.rest;

import com.social.eshop.EshopApp;
import com.social.eshop.domain.Media;
import com.social.eshop.repository.MediaRepository;
import com.social.eshop.repository.search.MediaSearchRepository;
import com.social.eshop.service.MediaService;
import com.social.eshop.service.dto.MediaDTO;
import com.social.eshop.service.mapper.MediaMapper;
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
public class MediaResourceTest {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_CONTENT_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_CONTENT_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_LOCATION = "AAAAAAAAAA";
    private static final String UPDATED_LOCATION = "BBBBBBBBBB";

    private static final String DEFAULT_SIZE = "AAAAAAAAAA";
    private static final String UPDATED_SIZE = "BBBBBBBBBB";

    @Autowired
    private MediaRepository mediaRepository;

    @Autowired
    private MediaMapper mediaMapper;

    @Autowired
    private MediaService mediaService;

    @Autowired
    private MediaSearchRepository mediaSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restMediaMockMvc;

    private Media media;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        MediaResource mediaResource = new MediaResource(mediaService);
        this.restMediaMockMvc = MockMvcBuilders.standaloneSetup(mediaResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    public static Media createEntity(EntityManager em) {
        Media media = new Media()
            .name(DEFAULT_NAME)
            .type(DEFAULT_TYPE)
            .contentType(DEFAULT_CONTENT_TYPE)
            .location(DEFAULT_LOCATION)
            .size(DEFAULT_SIZE);
        return media;
    }

    @Before
    public void initTest() {
        mediaSearchRepository.deleteAll();
        media = createEntity(em);
    }

    @Test
    @Transactional
    public void createMedia() throws Exception {
        int databaseSizeBeforeCreate = mediaRepository.findAll().size();

        // Create the Media
        MediaDTO mediaDTO = mediaMapper.toDto(media);
        restMediaMockMvc.perform(post("/api/media")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mediaDTO)))
            .andExpect(status().isCreated());

        // Validate the Media in the database
        List<Media> mediaList = mediaRepository.findAll();
        assertThat(mediaList).hasSize(databaseSizeBeforeCreate + 1);
        Media testMedia = mediaList.get(mediaList.size() - 1);
        assertThat(testMedia.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testMedia.getType()).isEqualTo(DEFAULT_TYPE);
        assertThat(testMedia.getContentType()).isEqualTo(DEFAULT_CONTENT_TYPE);
        assertThat(testMedia.getLocation()).isEqualTo(DEFAULT_LOCATION);
        assertThat(testMedia.getSize()).isEqualTo(DEFAULT_SIZE);

        // Validate the Media in Elasticsearch
        Media mediaEs = mediaSearchRepository.findOne(testMedia.getId());
        assertThat(mediaEs).isEqualToComparingFieldByField(testMedia);
    }

    @Test
    @Transactional
    public void getAllMedia() throws Exception {
        // Initialize the database
        mediaRepository.saveAndFlush(media);

        // Get all the mediaList
        restMediaMockMvc.perform(get("/api/media?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(media.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE.toString())))
            .andExpect(jsonPath("$.[*].contentType").value(hasItem(DEFAULT_CONTENT_TYPE.toString())))
            .andExpect(jsonPath("$.[*].location").value(hasItem(DEFAULT_LOCATION.toString())))
            .andExpect(jsonPath("$.[*].size").value(hasItem(DEFAULT_SIZE.toString())));
    }

    @Test
    @Transactional
    public void getMedia() throws Exception {
        // Initialize the database
        mediaRepository.saveAndFlush(media);

        // Get the media
        restMediaMockMvc.perform(get("/api/media/{id}", media.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(media.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE.toString()))
            .andExpect(jsonPath("$.contentType").value(DEFAULT_CONTENT_TYPE.toString()))
            .andExpect(jsonPath("$.location").value(DEFAULT_LOCATION.toString()))
            .andExpect(jsonPath("$.size").value(DEFAULT_SIZE.toString()));
    }

    @Test
    @Transactional
    public void updateMedia() throws Exception {
        // Initialize the database
        mediaRepository.saveAndFlush(media);
        mediaSearchRepository.save(media);
        int databaseSizeBeforeUpdate = mediaRepository.findAll().size();

        // Update the media
        Media updatedMedia = mediaRepository.findOne(media.getId());
        updatedMedia
            .name(UPDATED_NAME)
            .type(UPDATED_TYPE)
            .contentType(UPDATED_CONTENT_TYPE)
            .location(UPDATED_LOCATION)
            .size(UPDATED_SIZE);
        MediaDTO mediaDTO = mediaMapper.toDto(updatedMedia);

        restMediaMockMvc.perform(put("/api/media")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mediaDTO)))
            .andExpect(status().isOk());

        // Validate the Media in the database
        List<Media> mediaList = mediaRepository.findAll();
        assertThat(mediaList).hasSize(databaseSizeBeforeUpdate);
        Media testMedia = mediaList.get(mediaList.size() - 1);
        assertThat(testMedia.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testMedia.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testMedia.getContentType()).isEqualTo(UPDATED_CONTENT_TYPE);
        assertThat(testMedia.getLocation()).isEqualTo(UPDATED_LOCATION);
        assertThat(testMedia.getSize()).isEqualTo(UPDATED_SIZE);

        // Validate the Media in Elasticsearch
        Media mediaEs = mediaSearchRepository.findOne(testMedia.getId());
        assertThat(mediaEs).isEqualToComparingFieldByField(testMedia);
    }

    @Test
    @Transactional
    public void deleteMedia() throws Exception {
        // Initialize the database
        mediaRepository.saveAndFlush(media);
        mediaSearchRepository.save(media);
        int databaseSizeBeforeDelete = mediaRepository.findAll().size();

        // Get the media
        restMediaMockMvc.perform(delete("/api/media/{id}", media.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate Elasticsearch is empty
        boolean mediaExistsInEs = mediaSearchRepository.exists(media.getId());
        assertThat(mediaExistsInEs).isFalse();

        // Validate the database is empty
        List<Media> mediaList = mediaRepository.findAll();
        assertThat(mediaList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void searchMedia() throws Exception {
        // Initialize the database
        mediaRepository.saveAndFlush(media);
        mediaSearchRepository.save(media);

        // Search the media
        restMediaMockMvc.perform(get("/api/_search/media?query=id:" + media.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(media.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE.toString())))
            .andExpect(jsonPath("$.[*].contentType").value(hasItem(DEFAULT_CONTENT_TYPE.toString())))
            .andExpect(jsonPath("$.[*].location").value(hasItem(DEFAULT_LOCATION.toString())))
            .andExpect(jsonPath("$.[*].size").value(hasItem(DEFAULT_SIZE.toString())));
    }
}
