package com.investmentplanning.boundary;

import static com.investmentplanning.utils.TestUtils.boundaryTestFile;
import static com.investmentplanning.utils.TestUtils.currentTest;
import static com.investmentplanning.utils.TestUtils.testReport;
import static com.investmentplanning.utils.TestUtils.yakshaAssert;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Set;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.investmentplanning.dto.InvestmentPlanningDTO;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

public class InvestmentPlanningDTOBoundaryTest {

	private static Validator validator;

	@BeforeAll
	public static void setUp() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@AfterAll
	public static void afterAll() {
		testReport();
	}

	@Test
	public void testNameNotBlank() throws IOException {
		InvestmentPlanningDTO investmentDTO = new InvestmentPlanningDTO();
		investmentDTO.setName("");
		Set<ConstraintViolation<InvestmentPlanningDTO>> violations = validator.validate(investmentDTO);
		try {
			yakshaAssert(currentTest(), !violations.isEmpty(), boundaryTestFile);
		} catch (Exception e) {
			yakshaAssert(currentTest(), false, boundaryTestFile);
		}
	}

	@Test
	public void testAmountPositive() throws IOException {
		InvestmentPlanningDTO investmentDTO = new InvestmentPlanningDTO();
		investmentDTO.setAmount(BigDecimal.valueOf(-0.01)); // Less than 0.01
		Set<ConstraintViolation<InvestmentPlanningDTO>> violations = validator.validate(investmentDTO);
		try {
			yakshaAssert(currentTest(), !violations.isEmpty(), boundaryTestFile);
		} catch (Exception e) {
			yakshaAssert(currentTest(), false, boundaryTestFile);
		}
	}

	@Test
	public void testAmountNotNull() throws IOException {
		InvestmentPlanningDTO investmentDTO = new InvestmentPlanningDTO();
		investmentDTO.setAmount(null);
		Set<ConstraintViolation<InvestmentPlanningDTO>> violations = validator.validate(investmentDTO);
		try {
			yakshaAssert(currentTest(), !violations.isEmpty(), boundaryTestFile);
		} catch (Exception e) {
			yakshaAssert(currentTest(), false, boundaryTestFile);
		}
	}

	@Test
	public void testDateNotNull() throws IOException {
		InvestmentPlanningDTO investmentDTO = new InvestmentPlanningDTO();
		investmentDTO.setDate(null);
		Set<ConstraintViolation<InvestmentPlanningDTO>> violations = validator.validate(investmentDTO);
		try {
			yakshaAssert(currentTest(), !violations.isEmpty(), boundaryTestFile);
		} catch (Exception e) {
			yakshaAssert(currentTest(), false, boundaryTestFile);
		}
	}

	@Test
	public void testCategoryNotBlank() throws IOException {
		InvestmentPlanningDTO investmentDTO = new InvestmentPlanningDTO();
		investmentDTO.setCategory("");
		Set<ConstraintViolation<InvestmentPlanningDTO>> violations = validator.validate(investmentDTO);
		try {
			yakshaAssert(currentTest(), !violations.isEmpty(), boundaryTestFile);
		} catch (Exception e) {
			yakshaAssert(currentTest(), false, boundaryTestFile);
		}
	}

	@Test
	public void testCategoryMinSize() throws IOException {
		InvestmentPlanningDTO investmentDTO = new InvestmentPlanningDTO();
		investmentDTO.setCategory("A"); // Less than 2 characters
		Set<ConstraintViolation<InvestmentPlanningDTO>> violations = validator.validate(investmentDTO);
		try {
			yakshaAssert(currentTest(), !violations.isEmpty(), boundaryTestFile);
		} catch (Exception e) {
			yakshaAssert(currentTest(), false, boundaryTestFile);
		}
	}

	@Test
	public void testCategoryMaxSize() throws IOException {
		InvestmentPlanningDTO investmentDTO = new InvestmentPlanningDTO();
		investmentDTO.setCategory("A".repeat(51)); // More than 50 characters
		Set<ConstraintViolation<InvestmentPlanningDTO>> violations = validator.validate(investmentDTO);
		try {
			yakshaAssert(currentTest(), !violations.isEmpty(), boundaryTestFile);
		} catch (Exception e) {
			yakshaAssert(currentTest(), false, boundaryTestFile);
		}
	}
}
