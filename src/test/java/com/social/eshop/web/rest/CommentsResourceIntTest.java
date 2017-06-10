package com.social.eshop.web.rest;

import com.social.eshop.EshopApp;

import com.social.eshop.domain.Comments;
import com.social.eshop.repository.CommentsRepository;
import com.social.eshop.service.CommentsService;
import com.social.eshop.repository.search.CommentsSearchRepository;
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

/**
 * Test class for the CommentsResource REST controller.
 *
 * @see CommentsResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = EshopApp.class)
public class CommentsResourceIntTest {

    private static final String DEFAULT_COMMENTS = "AAAAAAAAAA";
    private static final String UPDATED_COMMENTS = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_DATA = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATA = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    @Autowired
    private CommentsRepository commentsRepository;

    @Autowired
    private CommentsService commentsService;

    @Autowired
    private CommentsSearchRepository commentsSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restCommentsMockMvc;

    private Comments comments;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        CommentsResource commentsResource = new CommentsResource(commentsService);
        this.restCommentsMockMvc = MockMvcBuilders.standaloneSetup(commentsResource)
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
    public static Comments createEntity(EntityManager em) {
        Comments comments = new Comments()
            .comments(DEFAULT_COMMENTS)
            .data(DEFAULT_DATA);
        return comments;
    }

    @Before
    public void initTest() {
        commentsSearchRepository.deleteAll();
        comments = createEntity(em);
    }

    @Test
    @Transactional
    public void createComments() throws Exception {
        int databaseSizeBeforeCreate = commentsRepository.findAll().size();

        // Create the Comments
        restCommentsMockMvc.perform(post("/api/comments")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(comments)))
            .andExpect(status().isCreated());

        // Validate the Comments in the database
        List<Comments> commentsList = commentsRepository.findAll();
        assertThat(commentsList).hasSize(databaseSizeBeforeCreate + 1);
        Comments testComments = commentsList.get(commentsList.size() - 1);
        assertThat(testComments.getComments()).isEqualTo(DEFAULT_COMMENTS);
        assertThat(testComments.getData()).isEqualTo(DEFAULT_DATA);

        // Validate the Comments in Elasticsearch
        Comments commentsEs = commentsSearchRepository.findOne(testComments.getId());
        assertThat(commentsEs).isEqualToComparingFieldByField(testComments);
    }

    @Test
    @Transactional
    public void createCommentsWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = commentsRepository.findAll().size();

        // Create the Comments with an existing ID
        comments.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCommentsMockMvc.perform(post("/api/comments")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(comments)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Comments> commentsList = commentsRepository.findAll();
        assertThat(commentsList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllComments() throws Exception {
        // Initialize the database
        commentsRepository.saveAndFlush(comments);

        // Get all the commentsList
        restCommentsMockMvc.perform(get("/api/comments?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(comments.getId().intValue())))
            .andExpect(jsonPath("$.[*].comments").value(hasItem(DEFAULT_COMMENTS.toString())))
            .andExpect(jsonPath("$.[*].data").value(hasItem(sameInstant(DEFAULT_DATA))));
    }

    @Test
    @Transactional
    public void getComments() throws Exception {
        // Initialize the database
        commentsRepository.saveAndFlush(comments);

        // Get the comments
        restCommentsMockMvc.perform(get("/api/comments/{id}", comments.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(comments.getId().intValue()))
            .andExpect(jsonPath("$.comments").value(DEFAULT_COMMENTS.toString()))
            .andExpect(jsonPath("$.data").value(sameInstant(DEFAULT_DATA)));
    }

    @Test
    @Transactional
    public void getNonExistingComments() throws Exception {
        // Get the comments
        restCommentsMockMvc.perform(get("/api/comments/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateComments() throws Exception {
        // Initialize the database
        commentsService.save(comments);

        int databaseSizeBeforeUpdate = commentsRepository.findAll().size();

        // Update the comments
        Comments updatedComments = commentsRepository.findOne(comments.getId());
        updatedComments
            .comments(UPDATED_COMMENTS)
            .data(UPDATED_DATA);

        restCommentsMockMvc.perform(put("/api/comments")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedComments)))
            .andExpect(status().isOk());

        // Validate the Comments in the database
        List<Comments> commentsList = commentsRepository.findAll();
        assertThat(commentsList).hasSize(databaseSizeBeforeUpdate);
        Comments testComments = commentsList.get(commentsList.size() - 1);
        assertThat(testComments.getComments()).isEqualTo(UPDATED_COMMENTS);
        assertThat(testComments.getData()).isEqualTo(UPDATED_DATA);

        // Validate the Comments in Elasticsearch
        Comments commentsEs = commentsSearchRepository.findOne(testComments.getId());
        assertThat(commentsEs).isEqualToComparingFieldByField(testComments);
    }

    @Test
    @Transactional
    public void updateNonExistingComments() throws Exception {
        int databaseSizeBeforeUpdate = commentsRepository.findAll().size();

        // Create the Comments

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restCommentsMockMvc.perform(put("/api/comments")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(comments)))
            .andExpect(status().isCreated());

        // Validate the Comments in the database
        List<Comments> commentsList = commentsRepository.findAll();
        assertThat(commentsList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteComments() throws Exception {
        // Initialize the database
        commentsService.save(comments);

        int databaseSizeBeforeDelete = commentsRepository.findAll().size();

        // Get the comments
        restCommentsMockMvc.perform(delete("/api/comments/{id}", comments.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate Elasticsearch is empty
        boolean commentsExistsInEs = commentsSearchRepository.exists(comments.getId());
        assertThat(commentsExistsInEs).isFalse();

        // Validate the database is empty
        List<Comments> commentsList = commentsRepository.findAll();
        assertThat(commentsList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void searchComments() throws Exception {
        // Initialize the database
        commentsService.save(comments);

        // Search the comments
        restCommentsMockMvc.perform(get("/api/_search/comments?query=id:" + comments.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(comments.getId().intValue())))
            .andExpect(jsonPath("$.[*].comments").value(hasItem(DEFAULT_COMMENTS.toString())))
            .andExpect(jsonPath("$.[*].data").value(hasItem(sameInstant(DEFAULT_DATA))));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Comments.class);
        Comments comments1 = new Comments();
        comments1.setId(1L);
        Comments comments2 = new Comments();
        comments2.setId(comments1.getId());
        assertThat(comments1).isEqualTo(comments2);
        comments2.setId(2L);
        assertThat(comments1).isNotEqualTo(comments2);
        comments1.setId(null);
        assertThat(comments1).isNotEqualTo(comments2);
    }
}
