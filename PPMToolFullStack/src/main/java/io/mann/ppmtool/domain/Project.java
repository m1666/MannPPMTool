package io.mann.ppmtool.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * @Author: mann
 * @Date: 2019-02-09 19:45
 */
@Entity
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 项目名称
     */
    @NotBlank(message = "Project name is required")
    private String projectName;

    /**
     * 项目标识符
     */
    @NotBlank(message = "Project Identifier is required")
    @Size(min = 4,max = 5,message = "Please use 4 to 5 characters")
    @Column(updatable = false, unique = true)
    private String projectIdentifier;

    /**
     * 描述
     */
    @NotBlank(message = "Project description is required")
    private String  description;

    /**
     * 开始时间
     */
    @JsonFormat(pattern = "yyyy-mm-dd")
    private Date start_date;

    /**
     * 结束时间
     */
    @JsonFormat(pattern = "yyyy-mm-dd")
    private Date end_date;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-mm-dd")
    private Date created_At;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-mm-dd")
    private Date updated_At;

    public Project() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectIdentifier() {
        return projectIdentifier;
    }

    public void setProjectIdentifier(String projectIdentifier) {
        this.projectIdentifier = projectIdentifier;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }

    public Date getCreated_At() {
        return created_At;
    }

    public void setCreated_At(Date created_At) {
        this.created_At = created_At;
    }

    public Date getUpdated_At() {
        return updated_At;
    }

    public void setUpdated_At(Date updated_At) {
        this.updated_At = updated_At;
    }

    /**
     * @PrePersist 记录与业务无关的字段。
     */
    @PrePersist
    protected void onCreate() {
        this.created_At = new Date();
    }

    /**
     * @PreUpdate 用于为相应的生命周期事件指定回调方法。
     */
    @PreUpdate
    protected void onUpdate() {
        this.updated_At = new Date();
    }
}
