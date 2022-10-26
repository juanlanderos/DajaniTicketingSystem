package com.jpa.dajaniTestDB.entity;

import com.jpa.dajaniTestDB.model.RoleEnum;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "roles")
public class RoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer roleId;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private RoleEnum roleName;

    public RoleEntity(){
    }

    public RoleEntity(RoleEnum roleName){
        this.roleName = roleName;
    }

    public int getRoleID(){
        return roleId;
    }

    public void setRoleID(int roleId) {
        this.roleId = roleId;
    }

    public RoleEnum getRoleName() {
        return roleName;
    }

    public void setRoleName(RoleEnum roleName) {
        this.roleName = roleName;
    }
}
