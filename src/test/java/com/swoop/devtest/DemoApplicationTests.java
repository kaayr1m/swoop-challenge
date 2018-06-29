package com.swoop.devtest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.jayway.jsonpath.internal.function.text.Concatenate;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class DemoApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	/*
	 * GET : http://<server_url>/math/add?n1=<numeric param 1>&n2=<numeric param 2>
	 * This should add numbers 1 and 2 and provide the result in JSON
	 */
	@Test
	public void onePlusOneEqualsTwo() throws Exception {
		this.mockMvc.perform(get("/math/add?n1=1&n2=1")).andExpect(status().isOk())
				.andExpect(jsonPath("$.result").value(2));
	}

	/*
	 * POST : http://<server_url>/math/add (allow for form params 1&2 in a POST
	 * body) This should add two numbers from a POST body
	 */
	@Test
	public void postOnePlusOneEqualsTwo() throws Exception {
		this.mockMvc
				.perform(post("/math/add")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.param("n1", "1")
				.param("n2", "1"))
				.andDo(print())
				.andExpect(status().isOk()).andExpect(jsonPath("$.result").value(2));
	}

	/*
	 * GET : http://<server_url>/time/now
	 * 
	 * This should fetch time for MST at time of call from another service
	 * (https://www.developer.aero/WaitTime-API/Try-it-Now for YYC is a free one)
	 * and simplify the result to timezone and current time. Return the result or
	 * the timestring in a rational JSON document.
	 */
	@Test
	public void currentTime() throws Exception {
		this.mockMvc.perform(get("/time/now")).andExpect(status().isOk());
	}
}
