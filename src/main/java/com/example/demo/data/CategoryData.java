package com.example.demo.data;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "categories")
@AllArgsConstructor
@NoArgsConstructor
public class CategoryData {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     @Column(name = "id")
     private int id;

     @Column(name = "name")
     private String name;

     @Column(name = "description")
     private String description;

     public int getId() {
          return id;
     }

     public String getCategoryName() {
          return name;
     }

     public String getCategoryDesc() {
          return description;
     }

     public void setId(int id) {
          this.id = id;
     }

     public void setCategoryName(String name) {
          this.name = name;
     }
     
     public void setCategoryDesc(String description) {
          this.description = description;
     }
}
