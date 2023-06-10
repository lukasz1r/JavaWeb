package com.example.demo.data;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "notes")
@AllArgsConstructor
@NoArgsConstructor
public class NoteData {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     @Column(name = "id")
     private int id;

     @Column(name = "user_id")
     private int user_id;

     @Column(name = "title")
     private String title;

     @Column(name = "note")
     private String note;

     @Column(name = "date")
     private String date;

     @Column(name = "category_id")
     private int category_id;

     public int getId() {
          return id;
     }

     public int getUserId() {
          return user_id;
     }

     public String getTitle() {
          return title;
     }

     public String getNote() {
          return note;
     }

     public String getDate() {
          return date;
     }

     public int getCategoryId() {
          return category_id;
     }

     public void setId(int id) {
          this.id = id;
     }

     public void setUserId(int user_id) {
          this.user_id = user_id;
     }

     public void setTitle(String title) {
          this.title = title;
     }

     public void setNote(String note) {
          this.note = note;
     }

     public void setDate(String date) {
          this.date = date;
     }

     public void setCategoryId(int category_id) {
          this.category_id = category_id;
     }
}
