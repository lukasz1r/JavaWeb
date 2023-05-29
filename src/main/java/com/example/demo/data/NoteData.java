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
public class NoteData implements Comparable<NoteData> {
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

     @Override
     public int compareTo(NoteData other) {
         return this.date.compareTo(other.date);
     }
     
     public int compareTo(NoteData other, String flag) {
          if (flag.equals("descending")) {
               return other.date.compareTo(this.date);
          } else if (flag.equals("ascending")) {
               return this.date.compareTo(other.date);
          }

          return 0;
     }
}
