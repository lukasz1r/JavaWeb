package com.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.data.RoleData;

@Repository
public interface RoleRepository extends JpaRepository<RoleData, Long> {
    RoleData findByName(String name);
}