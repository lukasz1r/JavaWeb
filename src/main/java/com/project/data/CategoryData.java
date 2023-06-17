package com.project.data;

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

     public int getId() {
          return id;
     }

     public String getCategoryName() {
          return name;
     }

     public void setId(int id) {
          this.id = id;
     }

     public void setCategoryName(String name) {
          this.name = name;
     }
}
