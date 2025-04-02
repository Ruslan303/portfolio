package com.example.portfolio.service;

import com.example.portfolio.Entity.Portfolio;
import com.example.portfolio.exception.NotFoundException;
import com.example.portfolio.repository.PortfolioRepository;
import lombok.RequiredArgsConstructor;


import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;



@Service
@RequiredArgsConstructor
public class PortfolioServiceImpl {
    private final PortfolioRepository portfolioRepository;
    private final ModelMapper modelMapper;

    public Portfolio createPortfolio(Portfolio portfolio) {
        return portfolioRepository.save(portfolio);
    }

    public List<Portfolio>  getAllPortfolios(){
        return portfolioRepository.findAll();
    }



    public Portfolio getPortfolioById(Long id){
        return portfolioRepository.findById(id).orElseThrow(()-> new NotFoundException("Portfolio not found with id:"  + id));
    }


    public void  deletePortfolio(Long id){
       Portfolio portfolio=  portfolioRepository.findById(id).orElseThrow(()-> new NotFoundException("Portfolio not found with id:"  + id));
        portfolioRepository.delete(portfolio);  // Portfeli silirik
    }


    public Portfolio updatePortfolio(Long id,Portfolio portfolioDetails){
         Portfolio portfolio=portfolioRepository.findById(id).orElseThrow(()-> new NotFoundException("Portfolio not found"));
         modelMapper.map(portfolioDetails,portfolio);
         return portfolioRepository.save(portfolio);
    }
}
