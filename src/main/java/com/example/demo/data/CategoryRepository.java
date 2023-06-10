package com.example.demo.data;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryData, Integer> {
     CategoryData findById(int id);
     List<CategoryData> findAll();
     List<CategoryData> findAllByOrderByNameAsc();
     List<CategoryData> findAllByOrderByNameDesc();
}