package com.example.portfolio.repository;
import com.example.portfolio.Entity.Education;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EducationRepository extends JpaRepository<Education, Long> {
}
