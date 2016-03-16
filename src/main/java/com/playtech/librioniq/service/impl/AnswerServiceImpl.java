package com.playtech.librioniq.service.impl;

import com.playtech.librioniq.repository.AnswerRepository;
import com.playtech.librioniq.repository.search.AnswerSearchRepository;
import com.playtech.librioniq.service.AnswerService;
import com.playtech.librioniq.web.rest.dto.AnswerDTO;
import com.playtech.librioniq.web.rest.mapper.AnswerMapper;
import org.apache.commons.lang3.NotImplementedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

/**
 * Service Implementation for managing Post.
 */
@Service
@Transactional
public class AnswerServiceImpl implements AnswerService {

    private final Logger log = LoggerFactory.getLogger(AnswerServiceImpl.class);

    @Inject
    private AnswerRepository answerRepository;

    @Inject
    private AnswerMapper answerMapper;

    @Inject
    private AnswerSearchRepository answerSearchRepository;

    /**
     * Save a post.
     *
     * @return the persisted entity
     */
    @Override
    public AnswerDTO save(Long questionId, AnswerDTO answerDTO) {
        log.debug("Request to save Answer : {}", answerDTO);

//        Answer answer = answerMapper.answerDTOToAnswer(answerDTO);
//        answer = answerRepository.save(answer);
//        AnswerDTO result = answerMapper.answerToAnswerDTO(answer);
//        answerSearchRepository.save(answer);
//
//        return result;


        throw new NotImplementedException("#findAll not implemented yet");
    }

    /**
     * get all the posts.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<AnswerDTO> findAll() {
        log.debug("Request to get all Posts");

        throw new NotImplementedException("#findAll not implemented yet");
    }

    /**
     * get one post by id.
     *
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public AnswerDTO findOne(Long id) {
        log.debug("Request to get Post : {}", id);

        throw new NotImplementedException("#findOne not implemented yet");
    }

    /**
     * delete the  post by id.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Post : {}", id);

        throw new NotImplementedException("#delete not implemented yet");
    }

    /**
     * search for the post corresponding
     * to the query.
     */
    @Override
    @Transactional(readOnly = true)
    public List<AnswerDTO> search(String query) {

        log.debug("REST request to search Posts for query {}", query);
//        return StreamSupport
//            .stream(postSearchRepository.search(queryStringQuery(query)).spliterator(), false)
//            .map(postMapper::postToPostDTO)
//            .collect(Collectors.toList());
        throw new NotImplementedException("#search not implemented yet");
    }
}
