package com.cityquest.quest.model;

import javax.persistence.*;

@Entity
@Table(name = "assumptions")
public class Assumption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false)
    private Long id;

    @Column(name = "assumption")
    private String assumption;

    public Assumption() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAssumption() {
        return assumption;
    }

    public void setAssumption(String assumption) {
        this.assumption = assumption;
    }

    @Override
    public String toString() {
        return "Assumption{" +
                "id=" + id +
                ", assumption='" + assumption + '\'' +
                '}';
    }
}
