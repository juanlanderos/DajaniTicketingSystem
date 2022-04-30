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
@Table(name = "department")
public class Department {
    @Id
    //defines a sequence for new entry's PK
    @SequenceGenerator(
            name = "department_sequence",
            sequenceName = "department_sequence",
            allocationSize = 1
    )
    //generates the values needed for pk sequence
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "department_sequence"
    )
    @Column(name = "department_id", nullable = false)
    private Integer departmentId;

    @Column(name = "department_name", length = 45)
    private String name;

    @OneToMany(mappedBy = "department")
    private List<Ticket> ticketList = new ArrayList<>();

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Ticket> getTicketList() {return ticketList;}

    public void setTicketList(List<Ticket> ticketList) {this.ticketList = ticketList;}

}