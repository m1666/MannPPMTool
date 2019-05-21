package io.mann.ppmtool.domain;

import javax.persistence.*;

/**
 * @Author: Mann
 * @Date: 2019-05-21 20:10
 */
@Entity
public class Backlog {

    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Project Task 序列
     */
    private Integer PTSequence = 0;

    /**
     * Project 编号
     */
    private String projectIdentifier;

    // Todo: OneToOne with project

    // Todo: OneToMany Project tasks


    public Backlog() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPTSequence() {
        return PTSequence;
    }

    public void setPTSequence(Integer PTSequence) {
        this.PTSequence = PTSequence;
    }

    public String getProjectIdentifier() {
        return projectIdentifier;
    }

    public void setProjectIdentifier(String projectIdentifier) {
        this.projectIdentifier = projectIdentifier;
    }
}
