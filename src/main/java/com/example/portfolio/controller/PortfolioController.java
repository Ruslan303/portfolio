package com.example.portfolio.controller;
import com.example.portfolio.dto.request.PortfolioRequestDTO;
import com.example.portfolio.dto.response.PortfolioResponseDTO;
import com.example.portfolio.service.portfolio.PortfolioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/portfolios")
@RequiredArgsConstructor
public class PortfolioController {


    private final PortfolioService portfolioService;


    @PostMapping("create")
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Map<String, Object>> creatPortfolio(@RequestBody PortfolioRequestDTO portfolioRequestDTO) {
        PortfolioResponseDTO createdPortfolio = portfolioService.createPortfolio(portfolioRequestDTO);
        Map<String, Object> response = new HashMap<>();
        response.put("status", " created new portfolio");
        response.put("message", createdPortfolio);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }



    @PutMapping("/{id}")
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<PortfolioResponseDTO> updatePortfolio(@PathVariable Long id, @RequestBody PortfolioRequestDTO portfolioRequestDTO) {
        return ResponseEntity.ok(portfolioService.updatePortfolio(id, portfolioRequestDTO));
    }



    @GetMapping("/{id}")
    //@PreAuthorize("hasRole('ADMIN') or hasRole('MEMBER')")
    public ResponseEntity<PortfolioResponseDTO> getPortfolioById(@PathVariable Long id) {
        return ResponseEntity.ok(portfolioService.getPortfolioById(id));
    }



    @GetMapping("/user/{userId}")
    //@PreAuthorize("hasRole('ADMIN') or hasRole('MEMBER')")
    public ResponseEntity<List<PortfolioResponseDTO>> getPortfolioByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(portfolioService.getPortfolioBYUserId(userId));
    }



    @GetMapping
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<PortfolioResponseDTO>> getPortfolios() {
        return ResponseEntity.ok(portfolioService.getPortfolioAll());
    }



    @DeleteMapping("{id}")
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deletePortfolio(@PathVariable Long id) {
        portfolioService.deletePortfolio(id);
        return ResponseEntity.noContent().build();
    }



}































































//    @GetMapping("getAllPortfolios")
//    public ResponseEntity<Map<String, Object>> getAll() {
//        List<Portfolio> portfolios = portfolioServiceImpl.getAllPortfolios();
//
//        Map<String, Object> response = new HashMap<>();
//        response.put("message", "Portfolios successfully retrieved.");
//        response.put("portfolios", portfolios);
//        return ResponseEntity.status(HttpStatus.OK).body(response);
//    }
//
//
//
//
//    @GetMapping("{id}")
//    public ResponseEntity<Map<String,Object>> getPortfolioById(@PathVariable Long id) {
//        Portfolio portfolio = portfolioServiceImpl.getPortfolioById(id);
//        Map<String, Object> response = new HashMap<>();
//        response.put("message", "Portfolio successfully retrieved.");
//        response.put("portfolio", portfolio);
//        return ResponseEntity.status(HttpStatus.OK).body(response);
//
//    }
//
//    @PutMapping("{id}")
//    public ResponseEntity<Map<String,Object>> updatePortfolio(@PathVariable Long id, @RequestBody PortfolioRequestDTO portfolioRequestDTO) {
//        Portfolio updatePortfolio = portfolioServiceImpl.updatePortfolio(id,portfolioRequestDTO );
//        Map<String,Object> response = new HashMap<>();
//        response.put("message", "Portfolio successfully updated.");
//        response.put("portfolio", updatePortfolio);
//        return ResponseEntity.status(HttpStatus.OK).body(response);
//
//
//    }
//
//    @DeleteMapping("{id}")
//    public ResponseEntity<String> deletePortfolio(@PathVariable Long id) {
//        portfolioServiceImpl.deletePortfolio(id);
//        return ResponseEntity.ok("Portfoilo with ID " + id + " has been deleted successfully.");
//
//    }
//
//
//    }
//
//
//


