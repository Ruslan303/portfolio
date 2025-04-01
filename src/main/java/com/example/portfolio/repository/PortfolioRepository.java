package com.example.portfolio.repository;

import com.example.portfolio.Entity.Portfolio;
import com.example.portfolio.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PortfolioRepository extends JpaRepository<Portfolio, Long> {
    List<Portfolio> findByUser(User user);
}
