package com.example.portfolio.service.education;

import com.example.portfolio.Entity.Education;
import com.example.portfolio.Entity.Portfolio;
import com.example.portfolio.Entity.enums.ErrorMessage;
import com.example.portfolio.configuration.mapper.EducationMapper;
import com.example.portfolio.dto.request.EducationRequestDTO;
import com.example.portfolio.dto.response.EducationResponseDTO;
import com.example.portfolio.exception.EducationException;
import com.example.portfolio.exception.PortfoiloException;
import com.example.portfolio.repository.EducationRepository;
import com.example.portfolio.repository.PortfolioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;
@Service
@RequiredArgsConstructor
public class EducationServiceImpl implements EducationService {
    private final EducationRepository educationRepository;
    private final PortfolioRepository portfolioRepository;
    private final EducationMapper educationMapper;
    @Override
    public EducationResponseDTO createEducation(EducationRequestDTO educationRequestDTO){
        Portfolio portfolio= portfolioRepository.findById(educationRequestDTO.getPortfolioId())
                .orElseThrow(()-> new PortfoiloException(ErrorMessage.PORTFOLIO_NOT_FOUND.getMessage()+" "+educationRequestDTO.getPortfolioId()));
        Education education = educationMapper.toEntity(educationRequestDTO,portfolio);
        return  educationMapper.toResponse(educationRepository.save(education));

    }
    @Override
    public EducationResponseDTO updateEducation(Long id,EducationRequestDTO educationRequestDTO){
        Education education = educationRepository.findById(id).orElseThrow(()-> new EducationException(ErrorMessage.EDUCATION_NOT_FOUND.getMessage()+" "+id));
        educationMapper.updateEntity(educationRequestDTO,education);
        return educationMapper.toResponse(educationRepository.save(education));
    }
    @Override
    public EducationResponseDTO getEducationByid(Long id){
        return educationRepository.findById(id).map(educationMapper::toResponse).orElseThrow(()-> new EducationException(ErrorMessage.EDUCATION_NOT_FOUND.getMessage()+" "+id));

    }
    @Override
    public List<EducationResponseDTO> getEducationsBYPortfolioId(Long portfolioId){
        Portfolio portfolio = portfolioRepository.findById(portfolioId).orElseThrow(()-> new PortfoiloException(ErrorMessage.PORTFOLIO_NOT_FOUND.getMessage()+" "+portfolioId));
        return portfolio.getEducations().stream().map(educationMapper::toResponse).collect(Collectors.toList());

    }
    @Override
    public List<EducationResponseDTO> getAllEducation(){
        return educationRepository.findAll().stream().map(educationMapper::toResponse).collect(Collectors.toList());

    }
    @Override
    public void  deleteEducation(Long id ){
        Education education = educationRepository.findById(id).orElseThrow(()->new EducationException(ErrorMessage.EDUCATION_NOT_FOUND.getMessage()));
        educationRepository.delete(education);

    }




}

