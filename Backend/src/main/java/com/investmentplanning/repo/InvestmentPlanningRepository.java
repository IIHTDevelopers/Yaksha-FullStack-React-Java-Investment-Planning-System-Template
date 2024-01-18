package com.investmentplanning.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.investmentplanning.entity.InvestmentPlanning;

public interface InvestmentPlanningRepository extends JpaRepository<InvestmentPlanning, Long> {

	// write your logic here
}
