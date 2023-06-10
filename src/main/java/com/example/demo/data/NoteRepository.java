package com.example.demo.data;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteRepository extends JpaRepository<NoteData, Integer> {
     NoteData findById(int id);
     List<NoteData> findAll();

     @Modifying
     @Query(value = "DELETE FROM notes WHERE id = :id", nativeQuery = true)
     int deleteById(@Param("id") int id);
}
