package com.playtech.librioniq.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.playtech.librioniq.service.PostService;
import com.playtech.librioniq.service.QuestionService;
import com.playtech.librioniq.web.rest.dto.QuestionDTO;
import com.playtech.librioniq.web.rest.util.HeaderUtil;
import org.apache.commons.lang3.NotImplementedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

/**
 * REST controller for managing Post.
 */
@RestController
@RequestMapping("/api")
public class QuestionResource {

    private final Logger log = LoggerFactory.getLogger(QuestionResource.class);

    @Inject
    private QuestionService questionService;

    // TODO Needs updating
    /**
     * POST  /questions -> Create a new question.
     */
    @RequestMapping(value = "/questions",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<QuestionDTO> createQuestion(@Valid @RequestBody QuestionDTO questionDTO) throws URISyntaxException {
        log.debug("REST request to save Question : {}", questionDTO);
        return null;
/*
        if (questionDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("question", "idExists", "New question can not have an id")).body(null);
        }
        QuestionDTO result = postService.save(questionDTO);
        return ResponseEntity.created(new URI("/api/questions/" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert("question", result.getId().toString()))
                .body(result);*/
    }

    /**
     * PUT  /questions -> Updates an existing question.
     */
    @RequestMapping(value = "/questions",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<QuestionDTO> updateQuestion(@Valid @RequestBody QuestionDTO questionDTO) throws URISyntaxException {
        log.debug("REST request to update Question : {}", questionDTO);
        return null;
/*
        if (questionDTO.getId() == null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("question", "idNotExists", "Try to update non-existing question")).body(null);
        }
        QuestionDTO result = postService.save(questionDTO);
        return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityUpdateAlert("question", questionDTO.getId().toString()))
                .body(result);*/
    }

    /**
     * GET  /questions -> get all the questions.
     */
    @RequestMapping(value = "/questions",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    @Transactional(readOnly = true)
    public List<QuestionDTO> getAllQuestions() {
        log.debug("REST request to get all Questions");
        return null;
//        return postService.findAllQuestions();
    }

    /**
     * GET  /questions/:id -> get the "id" question.
     */
    @RequestMapping(value = "/questions/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<QuestionDTO> getQuestion(@PathVariable Long id) {
        log.debug("REST request to get Question : {}", id);

        throw new NotImplementedException("#deleteQuestion has not implemented yet");
    }

    /**
     * DELETE  /questions/:id -> delete the "id" question.
     */
    @RequestMapping(value = "/questions/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteQuestion(@PathVariable Long id) {
        log.debug("REST request to delete Question : {}", id);

        throw new NotImplementedException("#deleteQuestion has not implemented yet");
    }

    /**
     * SEARCH  /_search/questions/:query -> search for the question corresponding
     * to the query.
     */
    @RequestMapping(value = "/_search/questions/{query:.+}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<QuestionDTO> searchQuestion(@PathVariable String query) {
        log.debug("Request to search Questions for query {}", query);
        throw new NotImplementedException("#searchQuestions has not implemented yet");
    }
}
