package com.hsbc.servcie.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hsbc.model.Deduction;
import com.hsbc.model.PaySlip;
import com.hsbc.model.db.FedTaxes;
import com.hsbc.model.db.repository.EmployeeDetailsRepository;
import com.hsbc.model.db.repository.FedTaxesRepository;
import com.hsbc.model.db.repository.StateRepository;
import com.hsbc.servcie.PaySlipApiService;

@Service
public class PaySlipApiServiceImpl implements PaySlipApiService {

	private static final Logger logger = LoggerFactory.getLogger(PaySlipApiServiceImpl.class);
	
	@Autowired
	private EmployeeDetailsRepository employeeDetailsRepository;
	@Autowired
	private StateRepository  stateRepository;
	@Autowired
	private FedTaxesRepository  fedTaxesRepository;
	
	@Override
	public PaySlip reteivePaySlip(Long empId) {
		logger.info("=================Inside Service===============");
		
		if(employeeDetailsRepository.findById(empId).isPresent())
		{
			PaySlip paySlip = new PaySlip();
			Deduction deduction = new Deduction();
			employeeDetailsRepository.findById(empId).ifPresent(employeeDetails -> {
				logger.info("Employee ID = {}", employeeDetails.getEmpId());
				logger.info("Employee Name = {}", employeeDetails.getEmpName());
				logger.info("Employee Salary = {}", employeeDetails.getEmpSalary());
				paySlip.setBasePay(employeeDetails.getEmpSalary());
				stateRepository.findById(employeeDetails.getStateId()).ifPresent(state ->{
					logger.info("Employee State Name = {}", state.getStateName());
					logger.info("State Tax = {}%", state.getTax());
					deduction.setStateTax(state.getTax());
				});
				
			});
			
			for (FedTaxes fedTax : fedTaxesRepository.findAll()) {
				logger.info("Fed Tax = {}%", fedTax.getFedTax());
				logger.info("Clamity Tax = {}%", fedTax.getClamityTax());
				deduction.setClamityTax(fedTax.getClamityTax());
				deduction.setFedTax(fedTax.getFedTax());
			}
		
			paySlip.setDeduction(deduction);
		
			Double totalDedPer = deduction.getClamityTax()+deduction.getFedTax()+deduction.getStateTax();
			logger.info("Total Deduction Percentage = {}%", totalDedPer);
			Double netPay = paySlip.getBasePay() - (paySlip.getBasePay()*(totalDedPer/100));
			logger.info("Net Pay After Deduction = {}", netPay);
			paySlip.setNetPay(netPay);
			return paySlip;
		}
		
		logger.info("Employee ID = {} not found", empId);
		return null;
	}

}
