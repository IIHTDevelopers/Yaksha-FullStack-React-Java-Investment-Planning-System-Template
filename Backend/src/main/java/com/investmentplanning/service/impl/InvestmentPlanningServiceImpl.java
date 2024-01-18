package com.investmentplanning.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.investmentplanning.dto.InvestmentPlanningDTO;
import com.investmentplanning.service.InvestmentPlanningService;

@Service
public class InvestmentPlanningServiceImpl implements InvestmentPlanningService {

	@Override
	public List<InvestmentPlanningDTO> getAllInvestments() {
		// write your logic here
		return null;
	}

	@Override
	public InvestmentPlanningDTO getInvestmentById(Long id) {
		// write your logic here
		return null;
	}

	@Override
	public InvestmentPlanningDTO createInvestment(InvestmentPlanningDTO investmentPlanningDTO) {
		// write your logic here
		return null;
	}

	@Override
	public InvestmentPlanningDTO updateInvestmentPlanning(Long id, InvestmentPlanningDTO investmentPlanningDTO) {
		// write your logic here
		return null;
	}

	@Override
	public boolean deleteInvestment(Long id) {
		// write your logic here
		return false;
	}

	@Override
	public List<InvestmentPlanningDTO> getAllInvestmentsInCategory(String category) {
		// write your logic here
		return null;
	}
}
