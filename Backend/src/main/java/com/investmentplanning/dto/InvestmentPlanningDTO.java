package com.investmentplanning.dto;

import java.math.BigDecimal;
import java.util.Date;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class InvestmentPlanningDTO {

	private Long id;

	@NotBlank
	private String name;

	@Positive
	@NotNull
	private BigDecimal amount;

	@NotNull
	private Date date;

	@NotBlank
	private String category;

	public InvestmentPlanningDTO() {
		super();
	}

	public InvestmentPlanningDTO(Long id, String name, BigDecimal amount, Date date, String category) {
		super();
		this.id = id;
		this.name = name;
		this.amount = amount;
		this.date = date;
		this.category = category;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "InvestmentPolicy [id=" + id + ", name=" + name + ", amount=" + amount + ", date=" + date + ", category="
				+ category + "]";
	}
}
