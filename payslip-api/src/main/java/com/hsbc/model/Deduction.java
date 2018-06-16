package com.hsbc.model;

public class Deduction {
	
	private Double fedTax;
	private Double stateTax;
	private Double clamityTax;
	
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
	 * @return the stateTax
	 */
	public Double getStateTax() {
		return stateTax;
	}
	/**
	 * @param stateTax the stateTax to set
	 */
	public void setStateTax(Double stateTax) {
		this.stateTax = stateTax;
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
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Deduction [fedTax=" + fedTax + ", stateTax=" + stateTax + ", clamityTax=" + clamityTax + "]";
	}

}
