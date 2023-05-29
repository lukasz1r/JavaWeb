package com.example.demo.data;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserData, Integer> {
     UserData findById(int id);
     UserData findByUsername(String username);
     List<UserData> findAll();
}