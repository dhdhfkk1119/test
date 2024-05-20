package com.example.demo.repository;

import com.example.demo.entity.Survey;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SurveyRepository extends JpaRepository<Survey, Long>, JpaSpecificationExecutor<Survey> {
    Page<Survey> findAll(Pageable pageable);
    Page<Survey> findAll(Specification<Survey> spec , Pageable pageable);
}
