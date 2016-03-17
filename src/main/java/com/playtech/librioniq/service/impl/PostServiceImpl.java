package com.playtech.librioniq.service.impl;

import com.playtech.librioniq.repository.AnswerRepository;
import com.playtech.librioniq.repository.CommentRepository;
import com.playtech.librioniq.repository.PostRepository;
import com.playtech.librioniq.repository.QuestionRepository;
import com.playtech.librioniq.repository.search.PostSearchRepository;
import com.playtech.librioniq.service.AnswerService;
import com.playtech.librioniq.service.CommentService;
import com.playtech.librioniq.service.QuestionService;
import com.playtech.librioniq.web.rest.dto.AnswerDTO;
import com.playtech.librioniq.web.rest.dto.CommentDTO;
import com.playtech.librioniq.web.rest.dto.QuestionDTO;
import com.playtech.librioniq.web.rest.mapper.AnswerMapper;
import com.playtech.librioniq.web.rest.mapper.CommentMapper;
import com.playtech.librioniq.web.rest.mapper.QuestionMapper;
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
public class PostServiceImpl implements QuestionService, AnswerService, CommentService {

    private final Logger log = LoggerFactory.getLogger(PostServiceImpl.class);

    @Inject
    private PostRepository postRepository;

    @Inject
    private CommentRepository commentRepository;

    @Inject
    private QuestionRepository questionRepository;

    @Inject
    private AnswerRepository answerRepository;

    @Inject
    private PostSearchRepository postSearchRepository;

    @Inject
    private QuestionMapper questionMapper;

    @Inject
    private CommentMapper commentMapper;

    @Inject
    private AnswerMapper answerMapper;

    @Override
    public AnswerDTO save(AnswerDTO answerDTO) {
        throw new NotImplementedException("");
    }

    @Override
    public CommentDTO save(CommentDTO commentDTO) {
        throw new NotImplementedException("");
    }

    @Override
    public QuestionDTO save(QuestionDTO questionDTO) {
        throw new NotImplementedException("");
    }

    @Override
    public List<AnswerDTO> findAllAnswersForQuestion(Long questionId) {
        throw new NotImplementedException("");
    }

    @Override
    public List<CommentDTO> findAllCommentsForPost(Long postId) {
        throw new NotImplementedException("");
    }

    @Override
    public List<QuestionDTO> findAllQuestions() {
        throw new NotImplementedException("");
    }

    @Override
    public void delete(Long id) {
        throw new NotImplementedException("");
    }

    @Override
    public QuestionDTO findQuestion(Long id) {
        throw new NotImplementedException("");
    }

    // AUTO GENERATED STUFF

    /*

    private final Logger log = LoggerFactory.getLogger(PostServiceImpl.class);
    
    @Inject
    private PostRepository postRepository;
    
    @Inject
    private PostMapper postMapper;
    
    @Inject
    private PostSearchRepository postSearchRepository;
    
    */
/**
     * Save a post.
     * @return the persisted entity
     *//*

    public PostDTO save(PostDTO postDTO) {
        log.debug("Request to save Post : {}", postDTO);
        Post post = postMapper.postDTOToPost(postDTO);
        post = postRepository.save(post);
        PostDTO result = postMapper.postToPostDTO(post);
        postSearchRepository.save(post);
        return result;
    }

    // TODO save for QuestionDTO
    public QuestionDTO save(QuestionDTO questionDTO) {
        throw new NotYetImplementedException();
    }

    // TODO save for AnswerDTO
    public AnswerDTO save(AnswerDTO answerDTO) {
        throw new NotYetImplementedException();
    }

    // TODO save for CommentDTO
    public CommentDTO save(CommentDTO commentDTO) {
        throw new NotYetImplementedException();
    }

    */
/**
     *  get all the posts.
     *  @return the list of entities
     *//*

    @Transactional(readOnly = true) 
    public List<PostDTO> findAll() {
        log.debug("Request to get all Posts");
        List<PostDTO> result = postRepository.findAll().stream()
            .map(postMapper::postToPostDTO)
            .collect(Collectors.toCollection(LinkedList::new));
        return result;
    }

    */
/**
     *  get one post by id.
     *  @return the entity
     *//*

    @Transactional(readOnly = true) 
    public PostDTO findOne(Long id) {
        log.debug("Request to get Post : {}", id);
        Post post = postRepository.findOne(id);
        PostDTO postDTO = postMapper.postToPostDTO(post);
        return postDTO;
    }

    */
/**
     *  delete the  post by id.
     *//*

    public void delete(Long id) {
        log.debug("Request to delete Post : {}", id);
        postRepository.delete(id);
        postSearchRepository.delete(id);
    }

    */
/**
     * search for the post corresponding
     * to the query.
     *//*

    @Transactional(readOnly = true) 
    public List<PostDTO> search(String query) {
        
        log.debug("REST request to search Posts for query {}", query);
        return StreamSupport
            .stream(postSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .map(postMapper::postToPostDTO)
            .collect(Collectors.toList());
    }
*/
}
