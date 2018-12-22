package com.cityquest.quest.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Entity
@Table(name = "questions")
public class Question implements Serializable {

    @Id
    @Column(updatable = false, nullable = false)
    private Long id;

    @NotEmpty
    @Column(name = "question")
    private String question;

    @Column(name = "hint")
    private String hint;

    public Question() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", question='" + question + '\'' +
                ", hint='" + hint + '\'' +
                '}';
    }
}
