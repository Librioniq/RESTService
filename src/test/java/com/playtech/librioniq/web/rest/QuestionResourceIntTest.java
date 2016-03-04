package com.playtech.librioniq.web.rest;

import com.playtech.librioniq.Application;
import com.playtech.librioniq.domain.Post;
import com.playtech.librioniq.domain.PostType;
import com.playtech.librioniq.domain.Question;
import com.playtech.librioniq.repository.PostRepository;
import com.playtech.librioniq.service.PostService;
import com.playtech.librioniq.web.rest.dto.PostDTO;
import com.playtech.librioniq.web.rest.dto.QuestionDTO;
import com.playtech.librioniq.web.rest.mapper.PostMapper;
import com.playtech.librioniq.web.rest.mapper.QuestionMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


/**
 * Test class for the PostResource REST controller.
 *
 * @see PostResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class QuestionResourceIntTest {

    private static final String DEFAULT_CONTENT = "AAAAA";
    private static final String UPDATED_CONTENT = "BBBBB";

    private static final PostType DEFAULT_TYPE = PostType.COMMENT;
    private static final PostType UPDATED_TYPE = PostType.ANSWER;

    @Inject
    private PostRepository postRepository;

    @Inject
    private QuestionMapper questionMapper;

    @Inject
    private PostService postService;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restQuestionMockMvc;

    private Question question;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        QuestionResource postResource = new QuestionResource();
        ReflectionTestUtils.setField(postResource, "postService", postService);
        ReflectionTestUtils.setField(postResource, "questionMapper", questionMapper);
        this.restQuestionMockMvc = MockMvcBuilders.standaloneSetup(postResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        question = new Question();
        question.setContent(DEFAULT_CONTENT);
        question.setType(DEFAULT_TYPE);
    }

    @Test
    @Transactional
    public void createQuestion() throws Exception {
        int databaseSizeBeforeCreate = postRepository.findAll().size();

        // Create the Post
        QuestionDTO postDTO = questionMapper.questionToQuestionDTO(question);

        restQuestionMockMvc.perform(post("/api/question")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(postDTO)))
            .andExpect(status().isCreated());

        // Validate the Post in the database
        List<Post> posts = postRepository.findAll();
        assertThat(posts).hasSize(databaseSizeBeforeCreate + 1);
        Post testPost = posts.get(posts.size() - 1);
        assertThat(testPost.getContent()).isEqualTo(DEFAULT_CONTENT);
        assertThat(testPost.getType()).isEqualTo(DEFAULT_TYPE);
    }

    @Test
    @Transactional
    public void getAllPosts() throws Exception {
        // Initialize the database
        postRepository.saveAndFlush(question);

        // Get all the posts
        restQuestionMockMvc.perform(get("/api/question?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.[*].id").value(hasItem(question.getId().intValue())))
            .andExpect(jsonPath("$.[*].content").value(hasItem(DEFAULT_CONTENT.toString())))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE)));
    }

    @Test
    @Transactional
    public void getPost() throws Exception {
        // Initialize the database
        postRepository.saveAndFlush(question);

        // Get the post
        restQuestionMockMvc.perform(get("/api/question/{id}", question.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(question.getId().intValue()))
            .andExpect(jsonPath("$.content").value(DEFAULT_CONTENT.toString()))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE));
    }

    @Test
    @Transactional
    public void getNonExistingPost() throws Exception {
        // Get the post
        restQuestionMockMvc.perform(get("/api/question/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePost() throws Exception {
        // Initialize the database
        postRepository.saveAndFlush(question);

        int databaseSizeBeforeUpdate = postRepository.findAll().size();

        // Update the post
        question.setContent(UPDATED_CONTENT);
        question.setType(UPDATED_TYPE);
        QuestionDTO questionDTO = questionMapper.questionToQuestionDTO(question);

        restQuestionMockMvc.perform(put("/api/question")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(questionDTO)))
            .andExpect(status().isOk());

        // Validate the Post in the database
        List<Post> posts = postRepository.findAll();
        assertThat(posts).hasSize(databaseSizeBeforeUpdate);
        Post testPost = posts.get(posts.size() - 1);
        assertThat(testPost.getContent()).isEqualTo(UPDATED_CONTENT);
        assertThat(testPost.getType()).isEqualTo(UPDATED_TYPE);
    }

    @Test
    @Transactional
    public void deletePost() throws Exception {
        // Initialize the database
        postRepository.saveAndFlush(question);

        int databaseSizeBeforeDelete = postRepository.findAll().size();

        // Get the post
        restQuestionMockMvc.perform(delete("/api/question/{id}", question.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Post> posts = postRepository.findAll();
        assertThat(posts).hasSize(databaseSizeBeforeDelete - 1);
    }
}
