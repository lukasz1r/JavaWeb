package com.project.data;

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
     private Long id;

     @Column(name = "user_id")
     private Long user_id;

     @Column(name = "title")
     private String title;

     @Column(name = "note")
     private String note;

     @Column(name = "date")
     private String date;

     @Column(name = "remind_date")
     private String remind_date;

     @Column(name = "category_id")
     private int category_id;

     public Long getId() {
          return id;
     }

     public Long getUserId() {
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

     public String getRemindDate() {
          return remind_date;
     }

     public int getCategoryId() {
          return category_id;
     }

     public void setId(Long id) {
          this.id = id;
     }

     public void setUserId(Long user_id) {
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

     public void setRemindDate(String remind_date) {
          this.remind_date = remind_date;
     }

     public void setCategoryId(int category_id) {
          this.category_id = category_id;
     }
}
