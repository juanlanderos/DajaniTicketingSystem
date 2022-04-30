package com.jpa.dajaniTestDB.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ticket")
public class Ticket {

    @Id
    //auto-increments primary key per new entry
    @SequenceGenerator(
            name = "ticket_sequence",
            sequenceName = "ticket_sequence",
            allocationSize = 1
    )
    //generates the values needed for pk sequence
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "ticket_sequence"
    )
    private Integer ticketId;

    @ManyToOne
    @JoinColumn(
            name = "department_id",
            referencedColumnName = "department_id",
            nullable = true
    )
    private Department department;

    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "updated_at")
    private Instant updatedAt;

    @Column(name = "completed_at")
    private Instant completedAt;

    @Column(name = "status_ID")
    private String statusId;

    @Column(name = "assignee_ID")
    private Integer assigneeId;

    @Column(name = "requester_ID")
    private Integer requesterId;

    @OneToMany(mappedBy = "ticket")
    private List<Comment> commentList = new ArrayList<>();



//------------------Modifiers Past This Point ----------------------------

    public Integer getTicketId() {
        return ticketId;
    }

    public void setTicketId(Integer ticketId) {
        this.ticketId = ticketId;
    }

    public Instant getCreatedAt() {return createdAt;}

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Instant getCompletedAt() {
        return completedAt;
    }

    public void setCompletedAt(Instant completedAt) {
        this.completedAt = completedAt;
    }

    public String getStatusId() {
        return statusId;
    }

    public void setStatusId(String statusId) {
        this.statusId = statusId;
    }

    public Integer getAssigneeId() {
        return assigneeId;
    }

    public void setAssigneeId(Integer assigneeId) {
        this.assigneeId = assigneeId;
    }

    public Integer getRequesterId() {
        return requesterId;
    }

    public void setRequesterId(Integer requesterId) {
        this.requesterId = requesterId;
    }

    public Department getDepartment() {return department;}

    public void setDepartment(Department department) {this.department = department;}

    public List<Comment> getCommentList() {return commentList;}

    public void setCommentList(List<Comment> commentList) {this.commentList = commentList;}
}