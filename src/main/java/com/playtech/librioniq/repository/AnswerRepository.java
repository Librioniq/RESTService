package com.playtech.librioniq.repository;

import com.playtech.librioniq.domain.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository for the Post entity.
 */
public interface AnswerRepository extends JpaRepository<Answer, Long> {

}
