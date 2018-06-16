package com.hsbc.model.db;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "EMPLOYEE_DETAILS")
public class EmployeeDetails {
	
	@Id
	private Long empId;
	private String empName;
	private Long stateId;
	private Double empSalary;
	/**
	 * @return the empId
	 */
	public Long getEmpId() {
		return empId;
	}
	/**
	 * @param empId the empId to set
	 */
	public void setEmpId(Long empId) {
		this.empId = empId;
	}
	/**
	 * @return the empName
	 */
	public String getEmpName() {
		return empName;
	}
	/**
	 * @param empName the empName to set
	 */
	public void setEmpName(String empName) {
		this.empName = empName;
	}
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
	 * @return the empSalary
	 */
	public Double getEmpSalary() {
		return empSalary;
	}
	/**
	 * @param empSalary the empSalary to set
	 */
	public void setEmpSalary(Double empSalary) {
		this.empSalary = empSalary;
	}

}
