package com.example.demo.data;

import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
public class UserData {
     @Id
     @Column(name = "id")
     private int id;

     @Column(name = "username")
     private String username;

     @Column(name = "password")
     private String password;

     @Column(name = "role")
     private String role;

     @OneToMany(targetEntity = NoteData.class, cascade = CascadeType.ALL)
     @JoinColumn(name = "id")
     private List<NoteData> notes;

     public int getId() {
          return id;
     }

     public String getUsername() {
          return username;
     }

     public String getPassword() {
          return password;
     }

     public String getRole() {
          return role;
     }

     public void setId(int id) {
          this.id = id;
     }

     public void setUsername(String username) {
          this.username = username;
     }

     public void setPassword(String password) {
          this.password = password;
     }

     public void setRole(String role) {
          this.role = role;
     }
}
