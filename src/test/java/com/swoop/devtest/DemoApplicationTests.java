package com.swoop.devtest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.internal.function.text.Concatenate;
import com.swoop.devtest.model.CurrentTime;
import com.swoop.devtest.network.ThirdParty;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class DemoApplicationTests {

	@Mock
	private ThirdParty thirdParty;

	@Autowired
	private MockMvc mockMvc;

	@Before
	public void initMocks(){
			MockitoAnnotations.initMocks(this);
	}

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
		ObjectMapper mapper = new ObjectMapper();
		CurrentTime testCurrentTime = mapper.readValue(
			"{"+
			"\"airportCode\": \"DEN\","+
			"\"airportName\": \"Denver International Airport\","+
			"\"queueId\": \"4dbe38e82f245f3ce8c347d6d29184df\","+
			"\"queueName\": \"South General\","+
			"\"projectedWaitTime\": 960,"+
			"\"projectedMinWaitMinutes\": 14,"+
			"\"projectedMaxWaitMinutes\": 18,"+
			"\"localTime\": \"2015-12-09T17:06:00.000-07:00\","+
			"\"time\": \"2015-12-10T00:06:00.000Z\"}", CurrentTime.class);

		Mockito.when(thirdParty.getTime()).thenReturn(testCurrentTime);
		
		this.mockMvc.perform(get("/time/now")).andExpect(status().isOk()).andExpect(jsonPath("$.result").value("2015-12-10T00:06:00.000Z"));
	}
}
