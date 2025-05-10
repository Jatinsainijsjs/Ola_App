package com.SimpleCabProject.SimpleCabProject.Repository;


import com.SimpleCabProject.SimpleCabProject.Entities.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AdminRepository extends JpaRepository<Admin, String> {}