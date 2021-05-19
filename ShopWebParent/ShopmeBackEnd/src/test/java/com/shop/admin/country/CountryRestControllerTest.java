package com.shop.admin.country;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shop.common.entity.Country;

@SpringBootTest
@AutoConfigureMockMvc
public class CountryRestControllerTest {

	@Autowired
	MockMvc mockMvc;

	@Autowired
	ObjectMapper objectMapper;
	
	@Autowired
	TestEntityManager testEntityManager;

	@Test
	@WithMockUser(username = "alex@example.com", password = "johnson", roles = "Admin")
	public void countryRestTest() throws Exception {
		String url = "/countries/list";
		MvcResult mvcResult = mockMvc.perform(get(url)).andExpect(status().isOk()).andDo(print()).andReturn();

		String jsonResponse = mvcResult.getResponse().getContentAsString();
		System.out.println(jsonResponse);

//		write the json response to POJO
		Country[] countries = objectMapper.readValue(jsonResponse, Country[].class);
		for (Country country : countries) {
			System.out.println(country);
		}
	}

	@Test
	@WithMockUser(username = "alex@example.com", password = "johnson", roles = "Admin")
	public void testCreateCountry() throws JsonProcessingException, Exception {
		String url = "/countries/save";
		String countryName = "India";
		String countryCode = "IN";

		Country country = new Country(countryName, countryCode);

		MvcResult mvcResult = mockMvc.perform(post(url).contentType("application/json")
				.content(objectMapper.writeValueAsString(country)).with(csrf())).andDo(print())
				.andExpect(status().isOk()).andReturn();

		String postresponse = mvcResult.getResponse().getContentAsString();
		System.out.println("Country Id is: " + postresponse);
	}
}
