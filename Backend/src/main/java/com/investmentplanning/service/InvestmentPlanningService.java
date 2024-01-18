package com.investmentplanning.service;

import java.util.List;

import com.investmentplanning.dto.InvestmentPlanningDTO;

public interface InvestmentPlanningService {
	List<InvestmentPlanningDTO> getAllInvestments();

	InvestmentPlanningDTO getInvestmentById(Long id);

	InvestmentPlanningDTO createInvestment(InvestmentPlanningDTO investmentPlanningDTO);

	InvestmentPlanningDTO updateInvestmentPlanning(Long id, InvestmentPlanningDTO investmentPlanningDTO);

	boolean deleteInvestment(Long id);
	
	List<InvestmentPlanningDTO> getAllInvestmentsInCategory(String category);
}