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

     public NoteData(int user_id, String title, String note, String date) {
          super();
          this.user_id = user_id;
          this.title = title;
          this.note = note;
          this.date = date;
     }

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
}
