package com.jpa.dajaniTestDB.service.repository;

import com.jpa.dajaniTestDB.entity.RoleEntity;
import com.jpa.dajaniTestDB.model.RoleEnum;

import java.util.Optional;

public interface RoleRepository {
    Optional<RoleEntity> findByRole(RoleEnum role);
}
