package com.jpa.dajaniTestDB.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleModel {
    private Integer roleId;
    private RoleEnum roleName;
}