package com.investmentplanning.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.investmentplanning.dto.InvestmentPlanningDTO;
import com.investmentplanning.service.InvestmentPlanningService;

import jakarta.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/api/investments")
public class InvestmentPlanningController {

	private final InvestmentPlanningService investmentPlanningService;

	@Autowired
	public InvestmentPlanningController(InvestmentPlanningService investmentPlanningService) {
		this.investmentPlanningService = investmentPlanningService;
	}

	@GetMapping
	public ResponseEntity<List<InvestmentPlanningDTO>> getAllInvestments() {
		List<InvestmentPlanningDTO> investments = investmentPlanningService.getAllInvestments();
		return new ResponseEntity<>(investments, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<InvestmentPlanningDTO> getInvestmentById(@PathVariable Long id) {
		InvestmentPlanningDTO investment = investmentPlanningService.getInvestmentById(id);
		return new ResponseEntity<>(investment, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<InvestmentPlanningDTO> createInvestment(
			@Valid @RequestBody InvestmentPlanningDTO investmentDTO) {
		InvestmentPlanningDTO createdInvestment = investmentPlanningService.createInvestment(investmentDTO);
		return new ResponseEntity<>(createdInvestment, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<InvestmentPlanningDTO> updateInvestmentPlanning(@Valid @PathVariable Long id,
			@Valid @RequestBody InvestmentPlanningDTO investmentDTO) {
		InvestmentPlanningDTO updatedInvestment = investmentPlanningService.updateInvestmentPlanning(id, investmentDTO);
		return new ResponseEntity<>(updatedInvestment, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteInvestment(@PathVariable Long id) {
		investmentPlanningService.deleteInvestment(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@GetMapping("/category/{category}")
	public ResponseEntity<List<InvestmentPlanningDTO>> getAllInvestmentsInCategory(@PathVariable String category) {
		List<InvestmentPlanningDTO> investments = investmentPlanningService.getAllInvestmentsInCategory(category);
		return new ResponseEntity<>(investments, HttpStatus.OK);
	}
}
