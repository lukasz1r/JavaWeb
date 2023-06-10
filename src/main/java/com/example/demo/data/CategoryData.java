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
public class CategoryData implements Comparable<CategoryData> {
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

     @Override
     public int compareTo(CategoryData other) {
         return this.name.compareTo(other.name);
     }
     
     public int compareTo(CategoryData other, String flag) {
          if (flag.equals("descending")) {
               return other.name.compareTo(this.name);
          } else if (flag.equals("ascending")) {
               return this.name.compareTo(other.name);
          }

          return 0;
     }
}
