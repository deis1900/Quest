package com.cityquest.quest.model;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "answers")
public class Answer implements Serializable {

    @Id
    @Column(updatable = false, nullable = false)
    private Long id;

    @Size(min = 1, message = "Answer should have at least 1 characters")
    @Column(name = "answer")
    private String answer;

    public Answer() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Override
    public String toString() {
        return "Answer{" +
                "id=" + id +
                ", answer='" + answer + '\'' +
                '}';
    }
}
