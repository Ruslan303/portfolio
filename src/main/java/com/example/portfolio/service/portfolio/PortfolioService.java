package com.example.portfolio.service.portfolio;

import com.example.portfolio.dto.request.PortfolioRequestDTO;
import com.example.portfolio.dto.response.PortfolioResponseDTO;

import java.util.List;


public interface PortfolioService {
    PortfolioResponseDTO createPortfolio(PortfolioRequestDTO portfolioRequestDTO);

    PortfolioResponseDTO updatePortfolio(Long id, PortfolioRequestDTO portfolioRequestDTO);

    PortfolioResponseDTO getPortfolioById(Long id);

    List<PortfolioResponseDTO> getPortfolioBYUserId(Long userId);

    List<PortfolioResponseDTO> getPortfolioAll();

    void deletePortfolio(Long id) ;

}