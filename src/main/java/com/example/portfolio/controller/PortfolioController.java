package com.example.portfolio.controller;

import com.example.portfolio.Entity.Portfolio;
import com.example.portfolio.service.PortfolioServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/portfolios")
@RequiredArgsConstructor
public class PortfolioController {
    private final PortfolioServiceImpl portfolioServiceImpl;

    @PostMapping("create")
    public ResponseEntity<Map<String,Object>> creatPortfolio(@RequestBody Portfolio portfolio) {
        Portfolio createdPortfolio = portfolioServiceImpl.createPortfolio(portfolio);
        Map <String,Object> response= new HashMap<>();
        response.put("status"," created new portfolio");
        response.put("message",createdPortfolio);
        return ResponseEntity.status( HttpStatus.CREATED ).body(response);


    }
    @GetMapping("getAllPortfolios")
    public ResponseEntity<Map<String, Object>> getAll() {
        List<Portfolio> portfolios = portfolioServiceImpl.getAllPortfolios();

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Portfolios successfully retrieved.");
        response.put("portfolios", portfolios);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }




    @GetMapping("{id}")
    public ResponseEntity<Map<String,Object>> getPortfolioById(@PathVariable Long id) {
        Portfolio portfolio = portfolioServiceImpl.getPortfolioById(id);
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Portfolio successfully retrieved.");
        response.put("portfolio", portfolio);
        return ResponseEntity.status(HttpStatus.OK).body(response);

    }

    @PutMapping("{id}")
    public ResponseEntity<Map<String,Object>> updatePortfolio(@PathVariable Long id, @RequestBody Portfolio portfolioDetails) {
        Portfolio updatePortfolio = portfolioServiceImpl.updatePortfolio(id, portfolioDetails);
        Map<String,Object> response = new HashMap<>();
        response.put("message", "Portfolio successfully updated.");
        response.put("portfolio", updatePortfolio);
        return ResponseEntity.status(HttpStatus.OK).body(response);


    }


    }





