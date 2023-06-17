package com.project.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.project.data.SharedData;

import jakarta.transaction.Transactional;

@Repository
public interface SharedRepository extends JpaRepository<SharedData, Long> {
     ArrayList<SharedData> findAll();

     @Modifying
     @Transactional
     @Query(value = "INSERT INTO shared VALUES (:user_id, :note_id)", nativeQuery = true)
     void addSharedNote(@Param("user_id") Long user_id, @Param("note_id") Long note_id);

     @Modifying
     @Query(value = "DELETE FROM shared WHERE note_id = :id", nativeQuery = true)
     void deleteById(@Param("id") Long id);
}