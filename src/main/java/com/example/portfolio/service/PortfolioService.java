package com.example.portfolio.service;

import com.example.portfolio.Entity.Portfolio;

import java.util.List;
import java.util.Optional;

public interface PortfolioService {
     List<Portfolio>  getAllPortfolios();
     Optional<Portfolio> getPortfolioById(Long id);
     Portfolio createPortfolio(Portfolio portfolio);
     Portfolio updatePortfolio(Portfolio portfolio);
     void deletePortfolioById(Long id);

}
