package com.cityquest.quest.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column
    private String username;

    @Column
    private Long currentIssue;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = Question.class)
    @JoinColumn(name = "id")
    private Question currentQuestion;

    @OneToMany(cascade = CascadeType.ALL, targetEntity= Answer.class)
    @JoinColumn(name = "id")
    private List<Answer> answers;

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Question getCurrentQuestion() {
        return currentQuestion;
    }

    public void setCurrentQuestion(Question currentQuestion) {
        this.currentQuestion = currentQuestion;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public Long getCurrentIssue() {
        return currentIssue;
    }

    public void setCurrentIssue(Long currentIssue) {
        this.currentIssue = currentIssue;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", currentIssue=" + currentIssue +
                ", currentQuestion=" + currentQuestion +
                ", answers=" + answers +
                '}';
    }
}
