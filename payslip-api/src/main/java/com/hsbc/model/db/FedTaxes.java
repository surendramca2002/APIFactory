package com.hsbc.model.db;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "FED_TAXES")
public class FedTaxes {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Double fedTax;
	private Double clamityTax;
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * @return the fedTax
	 */
	public Double getFedTax() {
		return fedTax;
	}
	/**
	 * @param fedTax the fedTax to set
	 */
	public void setFedTax(Double fedTax) {
		this.fedTax = fedTax;
	}
	/**
	 * @return the clamityTax
	 */
	public Double getClamityTax() {
		return clamityTax;
	}
	/**
	 * @param clamityTax the clamityTax to set
	 */
	public void setClamityTax(Double clamityTax) {
		this.clamityTax = clamityTax;
	}

}
