package com.investmentplanning.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.investmentplanning.dto.InvestmentPlanningDTO;
import com.investmentplanning.entity.InvestmentPlanning;
import com.investmentplanning.exception.NotFoundException;
import com.investmentplanning.repo.InvestmentPlanningRepository;
import com.investmentplanning.service.InvestmentPlanningService;

@Service
public class InvestmentPlanningServiceImpl implements InvestmentPlanningService {

	private final InvestmentPlanningRepository investmentPlanningRepository;

	@Autowired
	public InvestmentPlanningServiceImpl(InvestmentPlanningRepository investmentPlanningRepository) {
		this.investmentPlanningRepository = investmentPlanningRepository;
	}

	@Override
	public List<InvestmentPlanningDTO> getAllInvestments() {
		List<InvestmentPlanning> investments = investmentPlanningRepository.findAll();
		return investments.stream().map(this::convertToDTO).collect(Collectors.toList());
	}

	@Override
	public InvestmentPlanningDTO getInvestmentById(Long id) {
		InvestmentPlanning investment = investmentPlanningRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Investment not found"));
		return convertToDTO(investment);
	}

	@Override
	public InvestmentPlanningDTO createInvestment(InvestmentPlanningDTO investmentPlanningDTO) {
		InvestmentPlanning investment = convertToEntity(investmentPlanningDTO);
		InvestmentPlanning savedInvestment = investmentPlanningRepository.save(investment);
		return convertToDTO(savedInvestment);
	}

	@Override
	public InvestmentPlanningDTO updateInvestmentPlanning(Long id, InvestmentPlanningDTO investmentPlanningDTO) {
		Optional<InvestmentPlanning> optionalInvestment = investmentPlanningRepository.findById(id);
		if (optionalInvestment.isPresent()) {
			InvestmentPlanning existingInvestment = optionalInvestment.get();
			// Update fields
			existingInvestment.setName(investmentPlanningDTO.getName());
			existingInvestment.setAmount(investmentPlanningDTO.getAmount());
			existingInvestment.setDate(investmentPlanningDTO.getDate());
			existingInvestment.setCategory(investmentPlanningDTO.getCategory());

			InvestmentPlanning updatedInvestment = investmentPlanningRepository.save(existingInvestment);
			return convertToDTO(updatedInvestment);
		} else {
			throw new NotFoundException("Investment not found");
		}
	}

	@Override
	public boolean deleteInvestment(Long id) {
		if (investmentPlanningRepository.existsById(id)) {
			investmentPlanningRepository.deleteById(id);
			return true;
		} else {
			throw new NotFoundException("Investment not found");
		}
	}

	@Override
	public List<InvestmentPlanningDTO> getAllInvestmentsInCategory(String category) {
		List<InvestmentPlanning> investments = investmentPlanningRepository.findAllByCategory(category);
		return investments.stream().map(this::convertToDTO).collect(Collectors.toList());
	}

	private InvestmentPlanningDTO convertToDTO(InvestmentPlanning investment) {
		return new InvestmentPlanningDTO(investment.getId(), investment.getName(), investment.getAmount(),
				investment.getDate(), investment.getCategory());
	}

	private InvestmentPlanning convertToEntity(InvestmentPlanningDTO investmentDTO) {
		return new InvestmentPlanning(investmentDTO.getId(), investmentDTO.getName(), investmentDTO.getAmount(),
				investmentDTO.getDate(), investmentDTO.getCategory());
	}
}
