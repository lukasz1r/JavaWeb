package com.example.demo.data;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserData, Integer> {
     UserData findById(int id);
     UserData findByUsername(String username);
     List<UserData> findAll();

     @Modifying
     @Query(value = "DELETE FROM users WHERE id = :id", nativeQuery = true)
     int deleteById(@Param("id") int id);
} 