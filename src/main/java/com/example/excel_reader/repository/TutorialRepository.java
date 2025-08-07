package com.example.excel_reader.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.excel_reader.model.Tutorial;

public interface TutorialRepository extends JpaRepository<Tutorial,Long>{

}
