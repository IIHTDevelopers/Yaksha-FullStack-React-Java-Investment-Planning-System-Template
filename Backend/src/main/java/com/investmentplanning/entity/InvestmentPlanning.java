package com.investmentplanning.entity;

import java.math.BigDecimal;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "investment_policy")
public class InvestmentPlanning {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private BigDecimal amount;

	@Column(nullable = false)
	private Date date;

	@Column(nullable = false)
	private String category;

	public InvestmentPlanning() {
		super();
	}

	public InvestmentPlanning(Long id, String name, BigDecimal amount, Date date, String category) {
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
