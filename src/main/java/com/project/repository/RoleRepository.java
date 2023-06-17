package com.project.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.project.data.RoleData;
import com.project.data.UsersRoles;

@Repository
public interface RoleRepository extends JpaRepository<RoleData, Long> {
    RoleData findByName(String name);
}