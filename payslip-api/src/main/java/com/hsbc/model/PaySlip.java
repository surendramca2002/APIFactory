package com.hsbc.model;

public class PaySlip {
	
	private Double basePay;
	private Deduction deduction;
	private Double netPay;
	/**
	 * @return the basePay
	 */
	public Double getBasePay() {
		return basePay;
	}
	/**
	 * @param basePay the basePay to set
	 */
	public void setBasePay(Double basePay) {
		this.basePay = basePay;
	}
	/**
	 * @return the deduction
	 */
	public Deduction getDeduction() {
		return deduction;
	}
	/**
	 * @param deduction the deduction to set
	 */
	public void setDeduction(Deduction deduction) {
		this.deduction = deduction;
	}
	/**
	 * @return the netPay
	 */
	public Double getNetPay() {
		return netPay;
	}
	/**
	 * @param netPay the netPay to set
	 */
	public void setNetPay(Double netPay) {
		this.netPay = netPay;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PaySlip [basePay=" + basePay + ", deduction=" + deduction + ", netPay=" + netPay + "]";
	}

}
