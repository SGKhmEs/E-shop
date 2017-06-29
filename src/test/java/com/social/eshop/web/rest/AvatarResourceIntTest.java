package com.social.eshop.web.rest;

import com.social.eshop.EshopApp;

import com.social.eshop.domain.Avatar;
import com.social.eshop.repository.AvatarRepository;
import com.social.eshop.service.AvatarService;
import com.social.eshop.repository.search.AvatarSearchRepository;
import com.social.eshop.service.dto.AvatarDTO;
import com.social.eshop.service.mapper.AvatarMapper;
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
import org.springframework.util.Base64Utils;

import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the AvatarResource REST controller.
 *
 * @see AvatarResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = EshopApp.class)
public class AvatarResourceIntTest {

    private static final byte[] DEFAULT_USERS_IMAGE = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_USERS_IMAGE = TestUtil.createByteArray(2, "1");
    private static final String DEFAULT_USERS_IMAGE_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_USERS_IMAGE_CONTENT_TYPE = "image/png";

    @Autowired
    private AvatarRepository avatarRepository;

    @Autowired
    private AvatarMapper avatarMapper;

    @Autowired
    private AvatarService avatarService;

    @Autowired
    private AvatarSearchRepository avatarSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restAvatarMockMvc;

    private Avatar avatar;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        AvatarResource avatarResource = new AvatarResource(avatarService);
        this.restAvatarMockMvc = MockMvcBuilders.standaloneSetup(avatarResource)
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
    public static Avatar createEntity(EntityManager em) {
        Avatar avatar = new Avatar()
            .usersImage(DEFAULT_USERS_IMAGE)
            .usersImageContentType(DEFAULT_USERS_IMAGE_CONTENT_TYPE);
        return avatar;
    }

    @Before
    public void initTest() {
        avatarSearchRepository.deleteAll();
        avatar = createEntity(em);
    }

    @Test
    @Transactional
    public void createAvatar() throws Exception {
        int databaseSizeBeforeCreate = avatarRepository.findAll().size();

        // Create the Avatar
        AvatarDTO avatarDTO = avatarMapper.toDto(avatar);
        restAvatarMockMvc.perform(post("/api/avatars")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(avatarDTO)))
            .andExpect(status().isCreated());

        // Validate the Avatar in the database
        List<Avatar> avatarList = avatarRepository.findAll();
        assertThat(avatarList).hasSize(databaseSizeBeforeCreate + 1);
        Avatar testAvatar = avatarList.get(avatarList.size() - 1);
        assertThat(testAvatar.getUsersImage()).isEqualTo(DEFAULT_USERS_IMAGE);
        assertThat(testAvatar.getUsersImageContentType()).isEqualTo(DEFAULT_USERS_IMAGE_CONTENT_TYPE);

        // Validate the Avatar in Elasticsearch
        Avatar avatarEs = avatarSearchRepository.findOne(testAvatar.getId());
        assertThat(avatarEs).isEqualToComparingFieldByField(testAvatar);
    }

    @Test
    @Transactional
    public void createAvatarWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = avatarRepository.findAll().size();

        // Create the Avatar with an existing ID
        avatar.setId(1L);
        AvatarDTO avatarDTO = avatarMapper.toDto(avatar);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAvatarMockMvc.perform(post("/api/avatars")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(avatarDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Avatar> avatarList = avatarRepository.findAll();
        assertThat(avatarList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllAvatars() throws Exception {
        // Initialize the database
        avatarRepository.saveAndFlush(avatar);

        // Get all the avatarList
        restAvatarMockMvc.perform(get("/api/avatars?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(avatar.getId().intValue())))
            .andExpect(jsonPath("$.[*].usersImageContentType").value(hasItem(DEFAULT_USERS_IMAGE_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].usersImage").value(hasItem(Base64Utils.encodeToString(DEFAULT_USERS_IMAGE))));
    }

    @Test
    @Transactional
    public void getAvatar() throws Exception {
        // Initialize the database
        avatarRepository.saveAndFlush(avatar);

        // Get the avatar
        restAvatarMockMvc.perform(get("/api/avatars/{id}", avatar.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(avatar.getId().intValue()))
            .andExpect(jsonPath("$.usersImageContentType").value(DEFAULT_USERS_IMAGE_CONTENT_TYPE))
            .andExpect(jsonPath("$.usersImage").value(Base64Utils.encodeToString(DEFAULT_USERS_IMAGE)));
    }

    @Test
    @Transactional
    public void getNonExistingAvatar() throws Exception {
        // Get the avatar
        restAvatarMockMvc.perform(get("/api/avatars/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAvatar() throws Exception {
        // Initialize the database
        avatarRepository.saveAndFlush(avatar);
        avatarSearchRepository.save(avatar);
        int databaseSizeBeforeUpdate = avatarRepository.findAll().size();

        // Update the avatar
        Avatar updatedAvatar = avatarRepository.findOne(avatar.getId());
        updatedAvatar
            .usersImage(UPDATED_USERS_IMAGE)
            .usersImageContentType(UPDATED_USERS_IMAGE_CONTENT_TYPE);
        AvatarDTO avatarDTO = avatarMapper.toDto(updatedAvatar);

        restAvatarMockMvc.perform(put("/api/avatars")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(avatarDTO)))
            .andExpect(status().isOk());

        // Validate the Avatar in the database
        List<Avatar> avatarList = avatarRepository.findAll();
        assertThat(avatarList).hasSize(databaseSizeBeforeUpdate);
        Avatar testAvatar = avatarList.get(avatarList.size() - 1);
        assertThat(testAvatar.getUsersImage()).isEqualTo(UPDATED_USERS_IMAGE);
        assertThat(testAvatar.getUsersImageContentType()).isEqualTo(UPDATED_USERS_IMAGE_CONTENT_TYPE);

        // Validate the Avatar in Elasticsearch
        Avatar avatarEs = avatarSearchRepository.findOne(testAvatar.getId());
        assertThat(avatarEs).isEqualToComparingFieldByField(testAvatar);
    }

    @Test
    @Transactional
    public void updateNonExistingAvatar() throws Exception {
        int databaseSizeBeforeUpdate = avatarRepository.findAll().size();

        // Create the Avatar
        AvatarDTO avatarDTO = avatarMapper.toDto(avatar);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restAvatarMockMvc.perform(put("/api/avatars")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(avatarDTO)))
            .andExpect(status().isCreated());

        // Validate the Avatar in the database
        List<Avatar> avatarList = avatarRepository.findAll();
        assertThat(avatarList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteAvatar() throws Exception {
        // Initialize the database
        avatarRepository.saveAndFlush(avatar);
        avatarSearchRepository.save(avatar);
        int databaseSizeBeforeDelete = avatarRepository.findAll().size();

        // Get the avatar
        restAvatarMockMvc.perform(delete("/api/avatars/{id}", avatar.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate Elasticsearch is empty
        boolean avatarExistsInEs = avatarSearchRepository.exists(avatar.getId());
        assertThat(avatarExistsInEs).isFalse();

        // Validate the database is empty
        List<Avatar> avatarList = avatarRepository.findAll();
        assertThat(avatarList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void searchAvatar() throws Exception {
        // Initialize the database
        avatarRepository.saveAndFlush(avatar);
        avatarSearchRepository.save(avatar);

        // Search the avatar
        restAvatarMockMvc.perform(get("/api/_search/avatars?query=id:" + avatar.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(avatar.getId().intValue())))
            .andExpect(jsonPath("$.[*].usersImageContentType").value(hasItem(DEFAULT_USERS_IMAGE_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].usersImage").value(hasItem(Base64Utils.encodeToString(DEFAULT_USERS_IMAGE))));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Avatar.class);
        Avatar avatar1 = new Avatar();
        avatar1.setId(1L);
        Avatar avatar2 = new Avatar();
        avatar2.setId(avatar1.getId());
        assertThat(avatar1).isEqualTo(avatar2);
        avatar2.setId(2L);
        assertThat(avatar1).isNotEqualTo(avatar2);
        avatar1.setId(null);
        assertThat(avatar1).isNotEqualTo(avatar2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AvatarDTO.class);
        AvatarDTO avatarDTO1 = new AvatarDTO();
        avatarDTO1.setId(1L);
        AvatarDTO avatarDTO2 = new AvatarDTO();
        assertThat(avatarDTO1).isNotEqualTo(avatarDTO2);
        avatarDTO2.setId(avatarDTO1.getId());
        assertThat(avatarDTO1).isEqualTo(avatarDTO2);
        avatarDTO2.setId(2L);
        assertThat(avatarDTO1).isNotEqualTo(avatarDTO2);
        avatarDTO1.setId(null);
        assertThat(avatarDTO1).isNotEqualTo(avatarDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(avatarMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(avatarMapper.fromId(null)).isNull();
    }
}
