package com.jpa.dajaniTestDB.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user")
public class User {
    @Id
    //auto-increments primary key per new entry (upon insert)
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    //generates the values needed for pk sequence
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_sequence"
    )
    @Column(name = "user_id", nullable = false)
    private Integer userId;


    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;


    @OneToMany(mappedBy = "user")
    private List<Comment> commentList = new ArrayList<>();

    @Column(name = "admin")
    private Integer admin;

    @Column(name = "agent")
    private Integer agent;

    @Column(name = "requester")
    private Integer requester;

    //----------------Accessors and Modifiers Past this-----------
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Integer getAdmin() {
        return admin;
    }

    public void setAdmin(Integer admin) {
        this.admin = admin;
    }

    public Integer getAgent() {
        return agent;
    }

    public void setAgent(Integer agent) {
        this.agent = agent;
    }

    public Integer getRequester() {
        return requester;
    }

    public void setRequester(Integer requester) {
        this.requester = requester;
    }

    public List<Comment> getCommentList() {return commentList;}

    public void setCommentList(List<Comment> commentList) {this.commentList = commentList;}

}