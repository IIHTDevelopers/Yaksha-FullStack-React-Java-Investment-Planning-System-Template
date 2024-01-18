package com.investmentplanning.functional;

import static com.investmentplanning.utils.MasterData.getInvestmentPlanningDTO;
import static com.investmentplanning.utils.MasterData.getInvestmentPlanningDTOList;
import static com.investmentplanning.utils.TestUtils.businessTestFile;
import static com.investmentplanning.utils.TestUtils.currentTest;
import static com.investmentplanning.utils.TestUtils.testReport;
import static com.investmentplanning.utils.TestUtils.yakshaAssert;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
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
public class InvestmentPlanningControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private InvestmentPlanningService investmentPlanningService;

	@AfterAll
	public static void afterAll() {
		testReport();
	}

	@Test
	public void testGetAllInvestments() throws Exception {
		List<InvestmentPlanningDTO> investmentDTOs = getInvestmentPlanningDTOList();

		when(this.investmentPlanningService.getAllInvestments()).thenReturn(investmentDTOs);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/investments")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(investmentDTOs))
						? "true"
						: "false"),
				businessTestFile);
	}

	@Test
	public void testGetInvestmentById() throws Exception {
		InvestmentPlanningDTO investmentDTO = getInvestmentPlanningDTO();
		when(this.investmentPlanningService.getInvestmentById(investmentDTO.getId())).thenReturn(investmentDTO);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/investments/" + investmentDTO.getId())
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(investmentDTO))
						? "true"
						: "false"),
				businessTestFile);
	}

	@Test
	public void testCreateInvestment() throws Exception {
		InvestmentPlanningDTO investmentDTO = getInvestmentPlanningDTO();

		when(this.investmentPlanningService.createInvestment(any())).thenReturn(investmentDTO);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/investments")
				.content(MasterData.asJsonString(investmentDTO)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(investmentDTO))
						? "true"
						: "false"),
				businessTestFile);
	}

	@Test
	public void testUpdateInvestmentPlanning() throws Exception {
		InvestmentPlanningDTO investmentDTO = getInvestmentPlanningDTO();

		when(this.investmentPlanningService.updateInvestmentPlanning(eq(investmentDTO.getId()), any()))
				.thenReturn(investmentDTO);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/investments/" + investmentDTO.getId())
				.content(MasterData.asJsonString(investmentDTO)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(investmentDTO))
						? "true"
						: "false"),
				businessTestFile);
	}

	@Test
	public void testDeleteInvestment() throws Exception {
		InvestmentPlanningDTO investmentDTO = getInvestmentPlanningDTO();
		when(this.investmentPlanningService.deleteInvestment(investmentDTO.getId())).thenReturn(true);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/investments/" + investmentDTO.getId())
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(), (result.getResponse().getContentAsString().contentEquals("") ? "true" : "false"),
				businessTestFile);
	}
}
