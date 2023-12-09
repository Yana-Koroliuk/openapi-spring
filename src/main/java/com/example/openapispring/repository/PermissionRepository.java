package com.example.openapispring.repository;

import com.example.openapispring.entity.Permission;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;


public interface PermissionRepository extends CrudRepository<Permission, Long> {
    Optional<Permission> findByAction(String action);
}
