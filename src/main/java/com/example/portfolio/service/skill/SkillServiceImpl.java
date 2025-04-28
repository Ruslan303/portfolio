package com.example.portfolio.service.skill;

import com.example.portfolio.Entity.Portfolio;
import com.example.portfolio.Entity.Skill;
import com.example.portfolio.Entity.enums.ErrorMessage;
import com.example.portfolio.configuration.mapper.SkillMapper;
import com.example.portfolio.dto.request.SkillRequestDTO;
import com.example.portfolio.dto.response.SkillResponseDTO;
import com.example.portfolio.exception.PortfoiloException;
import com.example.portfolio.exception.SkillException;
import com.example.portfolio.repository.PortfolioRepository;
import com.example.portfolio.repository.SkillRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SkillServiceImpl implements SkillService {
    private final SkillRepository skillRepository;
    private final SkillMapper skillMapper;
    private final PortfolioRepository portfolioRepository;
    @Override
    public SkillResponseDTO createSkill(SkillRequestDTO skillRequestDTO) {
        Portfolio portfolio = portfolioRepository.findById(skillRequestDTO.getPortfolioId()).orElseThrow(() -> new PortfoiloException(ErrorMessage.PROJECT_NOT_FOUND.getMessage() + " " + skillRequestDTO.getPortfolioId()));
        Skill skill = skillMapper.toEntity(skillRequestDTO, portfolio);
        return skillMapper.toResponse(skillRepository.save(skill));
    }
    @Override
    public SkillResponseDTO updateSkill(Long id ,SkillRequestDTO skillRequestDTO) {
        Skill skill = skillRepository.findById(id).orElseThrow(()->new SkillException(ErrorMessage.SKILL_NOT_FOUND.getMessage()+ " "+id));
        skillMapper.update(skillRequestDTO, skill);
        return skillMapper.toResponse(skillRepository.save(skill));
    }
    @Override
    public SkillResponseDTO getSkillById(Long id) {
        return skillRepository.findById(id).map(skillMapper::toResponse).orElseThrow(()->new SkillException(ErrorMessage.SKILL_NOT_FOUND.getMessage()+ " "+id));
    }
    @Override
    public List<SkillResponseDTO> getSkills() {
        return skillRepository.findAll().stream().map(skillMapper::toResponse).collect(Collectors.toList());
    }
    @Override
    public List<SkillResponseDTO> getSkillsByPortfolioId(Long portfolioId) {
        Portfolio portfolio = portfolioRepository.findById(portfolioId).orElseThrow(()->new PortfoiloException(ErrorMessage.PORTFOLIO_NOT_FOUND.getMessage()+ " "+portfolioId));
        return portfolio.getSkills().stream().map(skillMapper::toResponse).collect(Collectors.toList());
    }
    @Override
    public void  deleteSkill(Long id) {
        Skill skill= skillRepository.findById(id).orElseThrow(()->new SkillException(ErrorMessage.SKILL_NOT_FOUND.getMessage()+ " "+id));
        skillRepository.delete(skill);
    }


}
