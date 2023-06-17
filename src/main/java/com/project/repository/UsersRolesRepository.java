package com.project.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.project.data.UsersRoles;

@Repository
public interface UsersRolesRepository extends JpaRepository<UsersRoles, Long> {
     ArrayList<UsersRoles> findAll();

     @Query(value = "SELECT user_id FROM users_roles WHERE user_id = :user_id", nativeQuery = true)
     int findByUser_Id(@Param("user_id") Long user_id);

     @Query(value = "SELECT roles.name FROM users_roles JOIN users ON users.id = users_roles.user_id JOIN roles ON users_roles.role_id = roles.id WHERE users.email = :email", nativeQuery = true)
     String getRole(@Param("email") String email);

     @Query(value = "SELECT * FROM users_roles WHERE user_id = :id", nativeQuery = true)
     UsersRoles getRole(@Param("id") Long id);

     @Modifying
     @Query(value = "DELETE FROM users_roles WHERE user_id = :id", nativeQuery = true)
     void deleteByUser_id();
}