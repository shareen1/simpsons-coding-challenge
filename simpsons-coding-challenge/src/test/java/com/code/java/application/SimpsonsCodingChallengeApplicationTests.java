package com.code.java.application;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.Assert;
import org.springframework.web.context.WebApplicationContext;

import com.code.java.application.bean.CharacterBean;
import com.code.java.application.bean.MyCache;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
class SimpsonsCodingChallengeApplicationTests {

	private static final String JSONObject = null;
	private MockMvc mockMvc;
	@Autowired
	public WebApplicationContext context;

	@BeforeEach
	public void setup() {
		// context.refresh()

		SimpsonsCodingChallengeApplication.context.register(CharacterBean.class);
		SimpsonsCodingChallengeApplication.context.register(MyCache.class);
		SimpsonsCodingChallengeApplication.context.refresh();
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();

	}

	ObjectMapper ob = new ObjectMapper();

	public MockMvc getMockMvc() {
		return mockMvc;
	}

	public void setMockMvc(MockMvc mockMvc) {
		this.mockMvc = mockMvc;
	}

	@Test
	public void postCharacterTest() throws Exception {
		
		CharacterBean characterBean = SimpsonsCodingChallengeApplication.context.getBean(CharacterBean.class);
		characterBean.setChId("543999999");
		characterBean.setAge(new Long(70));
		characterBean.setFirstName("shareen");
		characterBean.setLastName("shahana");
		characterBean.setPictureURL("https://stockcharts.com/sales/img/user-icon.png");
		Set<String> comentlist = new HashSet<>();
		comentlist.add("Hello");
		comentlist.add("world");
		characterBean.setComments(comentlist, "54399999");
		//String tstring='{\"chId\": \"'+characterBean.getChId()+'\", \"firstName\": \"'+characterBean.getFirstName()+'\", \"lastName\": \"'+characterBean.getLastName()+'\", \"comments\": \"['+characterBean.getComments()+']\", \"age\": \"'+characterBean.getAge()+'\"}';
		String mymap=	"{\"chId\": \"l\", \"firstName\": \"m\", \"lastName\": \"m\", \"comments\": [\"9\"], \"age\": \"8\"}";
		
		//String jsonRequest = ob.writeValueAsString(characterBean);
		MvcResult result = mockMvc
				.perform(post("/postCharacter").content(mymap).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andReturn();
		 int resultstatus = result.getResponse().getStatus();
		System.out.println(resultstatus);
		assertEquals(HttpStatus.OK,HttpStatus.valueOf(resultstatus));
		
		

	}
}
