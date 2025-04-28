package com.example.portfolio.service.portfolio;
import com.example.portfolio.Entity.Portfolio;
import com.example.portfolio.Entity.User;
import com.example.portfolio.Entity.enums.ErrorMessage;
import com.example.portfolio.configuration.mapper.PortfolioMapper;
import com.example.portfolio.dto.request.PortfolioRequestDTO;
import com.example.portfolio.dto.response.PortfolioResponseDTO;
import com.example.portfolio.exception.PortfoiloException;
import com.example.portfolio.exception.UserException;
import com.example.portfolio.repository.PortfolioRepository;
import com.example.portfolio.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class PortfolioServiceImpl implements PortfolioService {
    private final PortfolioRepository portfolioRepository;
    private final PortfolioMapper portfolioMapper;
    private final UserRepository userRepository;
    @Override
    public PortfolioResponseDTO createPortfolio(PortfolioRequestDTO portfolioRequestDTO) {
        User user = userRepository.findById(portfolioRequestDTO.getUserId()).orElseThrow(() -> new UserException(ErrorMessage.USER_NOT_FOUND.getMessage() + " " + portfolioRequestDTO.getUserId()));
        Portfolio portfolio = portfolioMapper.toEntity(portfolioRequestDTO, user);
        return portfolioMapper.toResponse(portfolioRepository.save(portfolio));
    }
    @Override
    public PortfolioResponseDTO updatePortfolio(Long id ,PortfolioRequestDTO portfolioRequestDTO) {
        Portfolio portfolio  = portfolioRepository.findById(id).orElseThrow(()->new PortfoiloException(ErrorMessage.PORTFOLIO_NOT_FOUND.getMessage() + " " + id));
        portfolioMapper.updateEntity(portfolioRequestDTO,portfolio);
        return portfolioMapper.toResponse(portfolioRepository.save(portfolio));
    }
    @Override
    public PortfolioResponseDTO getPortfolioById(Long id) {
         return portfolioRepository.findById(id).map(portfolioMapper::toResponse).orElseThrow(()->new PortfoiloException(ErrorMessage.PORTFOLIO_NOT_FOUND.getMessage() + " " + id));
    }
    @Override
    public List <PortfolioResponseDTO> getPortfolioBYUserId(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(()->new UserException(ErrorMessage.USER_NOT_FOUND.getMessage() + " " + userId));
        return user.getPortfolios().stream().map(portfolioMapper::toResponse).collect(Collectors.toList());
    }
    @Override
    public List <PortfolioResponseDTO> getPortfolioAll() {
        return portfolioRepository.findAll().stream().map(portfolioMapper::toResponse).collect(Collectors.toList());
    }
    @Override
    public void deletePortfolio(Long id) {
        Portfolio portfolio = portfolioRepository.findById(id).orElseThrow(()->new PortfoiloException(ErrorMessage.PORTFOLIO_NOT_FOUND.getMessage() + " " + id));
        portfolioRepository.delete(portfolio);
    }


}









