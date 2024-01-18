package com.investmentplanning.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.investmentplanning.entity.InvestmentPlanning;

public interface InvestmentPlanningRepository extends JpaRepository<InvestmentPlanning, Long> {

	List<InvestmentPlanning> findAllByCategory(String category);

}
