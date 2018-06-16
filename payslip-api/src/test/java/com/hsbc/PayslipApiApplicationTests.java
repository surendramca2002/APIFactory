package com.hsbc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PayslipApiApplicationTests {
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	private MockMvc mockMvc;
	
	@Test
	public void contextLoads() {
	}
	
	@Before
	public void setup() {
		mockMvc = webAppContextSetup(webApplicationContext).build();
	}

    @Test
    public void test_valid_empId() throws Exception {
        this.mockMvc.perform(get("/v1/hsbc/employee/101/paySlip"))
        			.andExpect(status().isOk());
    }
    
    @Test
    public void test_empId_notFound() throws Exception {
        this.mockMvc.perform(get("/v1/hsbc/employee/108/paySlip"))
        			.andExpect(status().isNotFound());
    }
    
    @Test
    public void test_inValid_empId() throws Exception {
        this.mockMvc.perform(get("/v1/hsbc/employee/101A/paySlip"))
        			.andExpect(status().isBadRequest());
    }
}
