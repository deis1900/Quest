package com.cityquest.quest.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Size(min=2, message="Name should have at least 2 characters")
    @Column
    private String username;

    @NotNull
    @Column
    private Long currentIssue;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, targetEntity = Question.class)
    @JoinColumn(name = "currentIssue", updatable = false, insertable = false)
    private Question currentQuestion;

    @OneToMany(cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Assumption> assumptions = new ArrayList<>();

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

    public List<Assumption> getAssumptions() {
        return assumptions;
    }

    public void addAssumptions(Assumption assumption) {
        this.assumptions.add(assumption);
    }

    public void removeAssumptions(Assumption assumption) {
        this.assumptions.remove(assumption);
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
                ", assumptions=" + assumptions +
                '}';
    }
}
