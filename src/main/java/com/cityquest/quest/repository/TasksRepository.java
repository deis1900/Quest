package com.cityquest.quest.repository;

import com.cityquest.quest.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TasksRepository extends JpaRepository<Task, Long> {

    Optional<Task> findById (Long id);
    Optional<Task> findByQuestion(Long questionId);
    Optional<Task> findByAnswer(Long answerId);

}
