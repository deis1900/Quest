package com.cityquest.quest.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty
    @OneToOne(orphanRemoval = true)
    private Question question;

    @NotEmpty
    @OneToOne(orphanRemoval = true, fetch = FetchType.LAZY)
    private Answer answer;

    public Task() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public Answer getAnswer() {
        return answer;
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", question=" + question +
                ", answer=" + answer + '\'' +
                '}';
    }
}