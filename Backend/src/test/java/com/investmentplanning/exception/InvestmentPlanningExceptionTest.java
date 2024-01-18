package com.investmentplanning.exception;

import static com.investmentplanning.utils.MasterData.getInvestmentPlanningDTO;
import static com.investmentplanning.utils.TestUtils.currentTest;
import static com.investmentplanning.utils.TestUtils.exceptionTestFile;
import static com.investmentplanning.utils.TestUtils.testReport;
import static com.investmentplanning.utils.TestUtils.yakshaAssert;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.investmentplanning.controller.InvestmentPlanningController;
import com.investmentplanning.dto.InvestmentPlanningDTO;
import com.investmentplanning.service.InvestmentPlanningService;
import com.investmentplanning.utils.MasterData;

@WebMvcTest(InvestmentPlanningController.class)
@AutoConfigureMockMvc
public class InvestmentPlanningExceptionTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private InvestmentPlanningService investmentPlanningService;

	@AfterAll
	public static void afterAll() {
		testReport();
	}

	@Test
	public void testCreateInvestmentInvalidDataException() throws Exception {
		InvestmentPlanningDTO investmentDTO = getInvestmentPlanningDTO();
		investmentDTO.setAmount(null);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/investments")
				.content(MasterData.asJsonString(investmentDTO)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(),
				(result.getResponse().getStatus() == HttpStatus.BAD_REQUEST.value() ? "true" : "false"),
				exceptionTestFile);
	}

	@Test
	public void testUpdateInvestmentInvalidDataException() throws Exception {
		InvestmentPlanningDTO investmentDTO = getInvestmentPlanningDTO();
		investmentDTO.setAmount(null);
		investmentDTO.setDate(null);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/investments/" + investmentDTO.getId())
				.content(MasterData.asJsonString(investmentDTO)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		System.out.println(result.getResponse().getStatus());
		yakshaAssert(currentTest(),
				(result.getResponse().getStatus() == HttpStatus.BAD_REQUEST.value() ? "true" : "false"),
				exceptionTestFile);
	}

	@Test
	public void testGetInvestmentByIdResourceNotFoundException() throws Exception {
		InvestmentPlanningDTO investmentDTO = getInvestmentPlanningDTO();
		ErrorResponse exResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(), "Investment not found");

		when(this.investmentPlanningService.getInvestmentById(investmentDTO.getId()))
				.thenThrow(new NotFoundException("Investment not found"));
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/investments/" + investmentDTO.getId())
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contains(exResponse.getMessage()) ? "true" : "false"),
				exceptionTestFile);
	}

	@Test
	public void testUpdateInvestmentByIdResourceNotFoundException() throws Exception {
		InvestmentPlanningDTO investmentDTO = getInvestmentPlanningDTO();
		ErrorResponse exResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(), "Investment not found");

		when(this.investmentPlanningService.updateInvestmentPlanning(eq(1234l), any()))
				.thenThrow(new NotFoundException("Investment not found"));

		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/investments/" + 1234l)
				.content(MasterData.asJsonString(investmentDTO)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contains(exResponse.getMessage()) ? "true" : "false"),
				exceptionTestFile);
	}

	@Test
	public void testDeleteInvestmentByIdResourceNotFoundException() throws Exception {
		InvestmentPlanningDTO investmentDTO = getInvestmentPlanningDTO();
		ErrorResponse exResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(), "Investment not found");

		when(this.investmentPlanningService.deleteInvestment(investmentDTO.getId()))
				.thenThrow(new NotFoundException("Investment not found"));
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/investments/" + investmentDTO.getId())
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contains(exResponse.getMessage()) ? "true" : "false"),
				exceptionTestFile);
	}
}
