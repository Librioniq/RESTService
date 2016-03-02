package com.playtech.librioniq.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.playtech.librioniq.service.PostService;
import com.playtech.librioniq.web.rest.dto.QuestionDTO;
import com.playtech.librioniq.web.rest.mapper.PostMapper;
import org.apache.commons.lang3.NotImplementedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.validation.Valid;
import java.net.URISyntaxException;
import java.util.List;

/**
 * REST controller for managing Post.
 */
@RestController
@RequestMapping("/api")
public class AnswerResource {

    private final Logger log = LoggerFactory.getLogger(AnswerResource.class);

    @Inject
    private PostService postService;

    @Inject
    private PostMapper postMapper;

    /**
     * POST  /questions -> Create a new question.
     */
    @RequestMapping(value = "/questions/{questionId:\\d+}/answers",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<QuestionDTO> createAnswer(@PathVariable Long questionId, @Valid @RequestBody QuestionDTO questionDTO) throws URISyntaxException {
        log.debug("REST request to save Question : {}", questionDTO);

        throw new NotImplementedException("#updateQuestion has not implemented yet");
    }

    /**
     * PUT  /questions -> Updates an existing question.
     */
    @RequestMapping(value = "/questions/{questionId:\\d+}/answers",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<QuestionDTO> updateAnswer(@PathVariable Long questionId, @Valid @RequestBody QuestionDTO questionDTO) throws URISyntaxException {
        log.debug("REST request to update Question : {}", questionDTO);

        throw new NotImplementedException("#updateQuestion has not implemented yet");
    }

    /**
     * GET  /questions -> get all the questions.
     */
    @RequestMapping(value = "/questions/{questionId:\\d+}/answers",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    @Transactional(readOnly = true)
    public List<QuestionDTO> getAllQuestionsAnswers(@PathVariable Long questionId) {
        log.debug("REST request to get all Questions");

        throw new NotImplementedException("#getAllQuestions has not implemented yet");
    }

    /**
     * GET  /questions/:id -> get the "id" question.
     */
    @RequestMapping(value = "/questions/{questionId:\\d+}/answers/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<QuestionDTO> getAnswer(@PathVariable Long questionId, @PathVariable Long id) {
        log.debug("REST request to get Question : {}", id);

        throw new NotImplementedException("#deleteQuestion has not implemented yet");
    }

    /**
     * DELETE  /questions/:id -> delete the "id" question.
     */
    @RequestMapping(value = "/questions/{questionId:\\d+}/answers/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteAnswer(@PathVariable Long questionId, @PathVariable Long id) {
        log.debug("REST request to delete Question : {}", id);

        throw new NotImplementedException("#deleteQuestion has not implemented yet");
    }

    /**
     * SEARCH  /_search/questions/:query -> search for the question corresponding
     * to the query.
     */
    @RequestMapping(value = "/_search/answers/{query:.+}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<QuestionDTO> searchAnswer(@PathVariable String query) {
        log.debug("Request to search Questions for query {}", query);
        throw new NotImplementedException("#searchQuestions has not implemented yet");
    }
}
