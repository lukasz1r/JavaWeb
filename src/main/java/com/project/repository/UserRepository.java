package com.project.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.project.data.UserData;

@Repository
public interface UserRepository extends JpaRepository<UserData, Long> {
    UserData findByEmail(String email);
    UserData findByName(String name);

    ArrayList<UserData> findAll();

    @Modifying
    @Query(value = "DELETE FROM users WHERE id = :id", nativeQuery = true)
    void deleteById(@Param("id") Long id);
}
