package com.project.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.project.data.NoteData;

import jakarta.transaction.Transactional;

@Repository
public interface NoteRepository extends JpaRepository<NoteData, Integer> {
     NoteData findById(int id);
     ArrayList<NoteData> findAll();

     @Query(value = "SELECT * FROM notes WHERE user_id = :id", nativeQuery = true)
     ArrayList<NoteData> findAllUsers(@Param("id") Long id);

     @Query(value = "SELECT * FROM notes WHERE category_id = :id", nativeQuery = true)
     ArrayList<NoteData> findAllByCategory(@Param("id") int id);

     @Query(value = "SELECT * FROM notes WHERE category_id = :id AND user_id = :user_id", nativeQuery = true)
     ArrayList<NoteData> findAllByCategory(@Param("id") int id, @Param("user_id") Long user_id);

     @Query(value = "SELECT notes.* FROM notes INNER JOIN categories ON notes.category_id = categories.id WHERE user_id = :id  ORDER BY categories.name ASC", nativeQuery = true)
     ArrayList<NoteData> orderedByNameAsc(@Param("id") Long id);

     @Query(value = "SELECT notes.* FROM notes INNER JOIN categories ON notes.category_id = categories.id WHERE user_id = :id  ORDER BY categories.name DESC", nativeQuery = true)
     ArrayList<NoteData> orderedByNameDesc(@Param("id") Long id);

     @Query(value = "SELECT * FROM notes WHERE user_id = :id ORDER BY date ASC", nativeQuery = true)
     ArrayList<NoteData> orderedByDateAsc(@Param("id") Long id);

     @Query(value = "SELECT * FROM notes WHERE user_id = :id ORDER BY date DESC", nativeQuery = true)
     ArrayList<NoteData> orderedByDateDesc(@Param("id") Long id);

     @Query(value = "SELECT * FROM notes WHERE user_id = :id ORDER BY title ASC", nativeQuery = true)
     ArrayList<NoteData> orderedByTitleAsc(@Param("id") Long id);

     @Query(value = "SELECT * FROM notes WHERE user_id = :id ORDER BY title DESC", nativeQuery = true)
     ArrayList<NoteData> orderedByTitleDesc(@Param("id") Long id);

     @Query(value = "SELECT n.* FROM notes n JOIN categories c ON n.category_id = c.id WHERE user_id = :id ORDER BY (SELECT COUNT(*) FROM notes WHERE category_id = n.category_id) ASC, n.category_id DESC", nativeQuery = true)
     ArrayList<NoteData> orderedByPopularCategoryAsc(@Param("id") Long id);

     @Query(value = "SELECT n.* FROM notes n JOIN categories c ON n.category_id = c.id WHERE user_id = :id ORDER BY (SELECT COUNT(*) FROM notes WHERE category_id = n.category_id) DESC, n.category_id ASC", nativeQuery = true)
     ArrayList<NoteData> orderedByPopularCategoryDesc(@Param("id") Long id);

     @Query(value = "SELECT categories.name FROM categories JOIN notes ON notes.category_id = categories.id WHERE notes.id = :id", nativeQuery = true)
     String getNoteCategory(@Param("id") int id);

     @Query(value = "SELECT notes.* FROM notes JOIN shared ON notes.id = shared.note_id WHERE shared.user_id = :id", nativeQuery = true)
     ArrayList<NoteData> getSharedNotes(@Param("id") Long id);

     @Query(value = "SELECT * FROM notes WHERE remind_date = CURDATE()", nativeQuery = true)
     ArrayList<NoteData> getRemindNotes();

     @Modifying
     @Query(value = "DELETE FROM notes WHERE id = :id", nativeQuery = true)
     void deleteById(@Param("id") int id);

     @Modifying
     @Transactional
     @Query(value = "DELETE FROM notes WHERE user_id = :id", nativeQuery = true)
     void deleteByUser_id(@Param("id") Long id);
}
