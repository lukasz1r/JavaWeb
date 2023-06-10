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

     @Query(value = "SELECT notes.* FROM notes INNER JOIN categories ON notes.category_id = categories.id ORDER BY categories.name ASC", nativeQuery = true)
     List<NoteData> orderedByNameAsc();

     @Query(value = "SELECT notes.* FROM notes INNER JOIN categories ON notes.category_id = categories.id ORDER BY categories.name DESC", nativeQuery = true)
     List<NoteData> orderedByNameDesc();

     @Query(value = "SELECT * FROM notes ORDER BY date ASC", nativeQuery = true)
     List<NoteData> orderedByDateAsc();

     @Query(value = "SELECT * FROM notes ORDER BY date DESC", nativeQuery = true)
     List<NoteData> orderedByDateDesc();

     @Modifying
     @Query(value = "DELETE FROM notes WHERE id = :id", nativeQuery = true)
     int deleteById(@Param("id") int id);
}
