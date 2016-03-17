package com.playtech.librioniq.web.rest;

import com.google.common.collect.Sets;
import com.playtech.librioniq.Application;
import com.playtech.librioniq.domain.Answer;
import com.playtech.librioniq.domain.PostType;
import com.playtech.librioniq.domain.Question;
import com.playtech.librioniq.repository.AnswerRepository;
import com.playtech.librioniq.repository.QuestionRepository;
import com.playtech.librioniq.web.rest.dto.AnswerDTO;
import com.playtech.librioniq.web.rest.mapper.AnswerMapper;
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
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
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
public class AnswerResourceIntTest {

    private static final String DEFAULT_CREATED_BY = "admin";
    private static final String UPDATED_CREATED_BY = "user";

    private static final ZonedDateTime DEFAULT_CREATED_DATE = ZonedDateTime.now();
    private static final ZonedDateTime UPDATED_CREATED_DATE = DEFAULT_CREATED_DATE.plus(10, ChronoUnit.DAYS);

    private static final Integer DEFAULT_RATING = 0;
    private static final Integer UPDATED_RATING = 1;

    private static final String DEFAULT_CONTENT = "AAAAA";
    private static final String UPDATED_CONTENT = "BBBBB";

    private static final PostType DEFAULT_TYPE = PostType.ANSWER;
    private static final PostType UPDATED_TYPE = PostType.ANSWER;

    @Inject
    private AnswerRepository answerRepository;
    @Inject
    private QuestionRepository questionRepository;

    @Inject
    private AnswerMapper answerMapper;

    @Inject
    private AnswerService answerService;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restAnswerMockMvc;

    private Answer answer;
    private Question question;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        AnswerResource answerResource = new AnswerResource();
        ReflectionTestUtils.setField(answerResource, "answerService", answerService);
        ReflectionTestUtils.setField(answerResource, "answerMapper", answerMapper);
        this.restAnswerMockMvc = MockMvcBuilders.standaloneSetup(answerResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        answer = new Answer();
        question = new Question();

        question.setContent(DEFAULT_CONTENT);
        question.setType(DEFAULT_TYPE);
        question.setRating(DEFAULT_RATING);
        question.setCreatedBy(DEFAULT_CREATED_BY);
        question.setCreatedDate(DEFAULT_CREATED_DATE);

        answer.setContent(DEFAULT_CONTENT);
        answer.setType(DEFAULT_TYPE);
        answer.setRating(DEFAULT_RATING);
        answer.setCreatedBy(DEFAULT_CREATED_BY);
        answer.setCreatedDate(DEFAULT_CREATED_DATE);
    }

    @Test
    @Transactional
    public void createAnswer() throws Exception {
        int databaseSizeBeforeCreate = answerRepository.findAll().size();
        question = questionRepository.save(question);
        // Create the Post
        AnswerDTO answerDTO = answerMapper.answerToAnswerDTO(answer);

        restAnswerMockMvc.perform(post("/api/questions/{id}/answers", question.getId())
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(answerDTO)))
            .andExpect(status().isCreated());

        // Validate the Post in the database
        List<Answer> answers = answerRepository.findAll();
        assertThat(answers).hasSize(databaseSizeBeforeCreate + 1);
        Answer testAnswer = answers.get(answers.size() - 1);
        assertThat(testAnswer.getContent()).isEqualTo(DEFAULT_CONTENT);
        assertThat(testAnswer.getType()).isEqualTo(DEFAULT_TYPE);
    }

    @Test
    @Transactional
    public void getAllQuestionsAnswers() throws Exception {
        // Initialize the database
        answerRepository.saveAndFlush(answer);

        question.setPosts(Sets.newHashSet(answer));
        questionRepository.saveAndFlush(question);

        // Get all the posts
        restAnswerMockMvc.perform(get("/api/questions/{qId}/answers?sort=id,desc", question.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.[*].id").value(hasItem(answer.getId().intValue())))
            .andExpect(jsonPath("$.[*].content").value(hasItem(DEFAULT_CONTENT)))
            .andExpect(jsonPath("$.[*].type").value(hasItem(String.valueOf(DEFAULT_TYPE))));
    }

    @Test
    @Transactional
    public void getAnswer() throws Exception {
        // Initialize the database
        answerRepository.saveAndFlush(answer);

        question.setPosts(Sets.newHashSet(answer));
        questionRepository.saveAndFlush(question);

        // Get the post
        restAnswerMockMvc.perform(get("/api/questions/{qId}/answers/{aId}", question.getId(), answer.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(answer.getId().intValue()))
            .andExpect(jsonPath("$.content").value(DEFAULT_CONTENT))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE));
    }

    @Test
    @Transactional
    public void getNonExistingPost() throws Exception {
        // Get the post
        restAnswerMockMvc.perform(get("/api/questions/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePost() throws Exception {
        // Initialize the database
        answerRepository.saveAndFlush(answer);

        question.setPosts(Sets.newHashSet(answer));
        questionRepository.saveAndFlush(question);

        int databaseSizeBeforeUpdate = answerRepository.findAll().size();

        // Update the post
        answer.setContent(UPDATED_CONTENT);
        answer.setType(UPDATED_TYPE);
        AnswerDTO answerDTO = answerMapper.answerToAnswerDTO(answer);

        restAnswerMockMvc.perform(put("/api/questions/{qId}/answers", question.getId())
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(answerDTO)))
            .andExpect(status().isOk());

        // Validate the Post in the database
        List<Answer> answers = answerRepository.findAll();
        assertThat(answers).hasSize(databaseSizeBeforeUpdate);
        Answer testAnswer = answers.get(answers.size() - 1);
        assertThat(testAnswer.getContent()).isEqualTo(UPDATED_CONTENT);
        assertThat(testAnswer.getType()).isEqualTo(UPDATED_TYPE);
    }

    @Test
    @Transactional
    public void deletePost() throws Exception {
        // Initialize the database
        answerRepository.saveAndFlush(answer);

        question.setPosts(Sets.newHashSet(answer));
        questionRepository.saveAndFlush(question);

        int databaseSizeBeforeDelete = answerRepository.findAll().size();

        // Get the post
        restAnswerMockMvc.perform(delete("/api/questions/{qId}/answers/{aId}", question.getId(), answer.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Answer> answers = answerRepository.findAll();
        assertThat(answers).hasSize(databaseSizeBeforeDelete - 1);
    }
}
