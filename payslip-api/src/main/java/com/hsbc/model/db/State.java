package com.hsbc.model.db;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "STATE")
public class State {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long stateId;
	private String stateName;
	private Double tax;
	/**
	 * @return the stateId
	 */
	public Long getStateId() {
		return stateId;
	}
	/**
	 * @param stateId the stateId to set
	 */
	public void setStateId(Long stateId) {
		this.stateId = stateId;
	}
	/**
	 * @return the stateName
	 */
	public String getStateName() {
		return stateName;
	}
	/**
	 * @param stateName the stateName to set
	 */
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
	/**
	 * @return the tax
	 */
	public Double getTax() {
		return tax;
	}
	/**
	 * @param tax the tax to set
	 */
	public void setTax(Double tax) {
		this.tax = tax;
	}

}
