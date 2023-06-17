package com.project.repository;

import java.util.ArrayList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.data.CategoryData;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryData, Integer> {
     CategoryData findById(int id);
     ArrayList<CategoryData> findAll();
     ArrayList<CategoryData> findAllByOrderByNameAsc();
     ArrayList<CategoryData> findAllByOrderByNameDesc();
}