package com.SimpleCabProject.SimpleCabProject.Repository;

import com.SimpleCabProject.SimpleCabProject.Entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
   // Optional<Category> findByName(String name);
    Optional<Category> findByName(String name);
    boolean existsByName(String name);


}

