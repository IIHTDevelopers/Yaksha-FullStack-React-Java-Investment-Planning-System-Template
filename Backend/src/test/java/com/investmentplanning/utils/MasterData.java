package com.investmentplanning.utils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.investmentplanning.dto.InvestmentPlanningDTO;

public class MasterData {

	public static InvestmentPlanningDTO getInvestmentPlanningDTO() {
		InvestmentPlanningDTO invest = new InvestmentPlanningDTO();
		invest.setId(1l);
		invest.setName("Gold");
		invest.setCategory("Gold");
		invest.setAmount(new BigDecimal("10000.00"));
		invest.setDate(new Date());
		return invest;
	}

	public static List<InvestmentPlanningDTO> getInvestmentPlanningDTOList() {
		List<InvestmentPlanningDTO> investList = new ArrayList<>();

		InvestmentPlanningDTO invest = new InvestmentPlanningDTO();
		invest.setId(1l);
		invest.setName("Gold");
		invest.setCategory("Gold");
		invest.setAmount(new BigDecimal("10000.00"));
		invest.setDate(new Date());

		investList.add(invest);
		return investList;
	}

	public static String asJsonString(final Object obj) {
		try {
			final ObjectMapper mapper = new ObjectMapper();
			mapper.registerModule(new JavaTimeModule());
			mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
			final String jsonContent = mapper.writeValueAsString(obj);

			return jsonContent;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static String randomStringWithSize(int size) {
		StringBuilder s = new StringBuilder();
		for (int i = 0; i < size; i++) {
			s.append("A");
		}
		return s.toString();
	}
}
