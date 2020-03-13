package com.code.java.application;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
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

	static boolean isfirsttime = true;

	@BeforeEach
	public void setup() {
		// context.refresh()
		if (isfirsttime) {
			SimpsonsCodingChallengeApplication.context.register(CharacterBean.class);
			SimpsonsCodingChallengeApplication.context.register(MyCache.class);
			SimpsonsCodingChallengeApplication.context.refresh();
			isfirsttime = false;
		}
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
		MyCache mycahe = SimpsonsCodingChallengeApplication.context.getBean(MyCache.class);
		characterBean.setChId("543999999");
		characterBean.setAge(new Long(70));
		characterBean.setFirstName("shareen");
		characterBean.setLastName("shahana");
		characterBean.setPictureURL("https://stockcharts.com/sales/img/user-icon.png");
		List<String> comentlist = new ArrayList();
		comentlist.add("Hello");
		comentlist.add("world");
		String mymap = "{\"chId\":\"" + characterBean.getChId() + "\"," + " \"firstName\":\""
				+ characterBean.getFirstName() + "\"," + "\"lastName\": \"" + characterBean.getLastName() + "\", "
				+ "\"comments\":[\"" + comentlist.get(0) + "\",\"" + comentlist.get(1) + "\"]," + "\"age\": \""
				+ characterBean.getAge() + "\"}";
		Set s = new HashSet<CharacterBean>();
		HashMap<String, CharacterBean> m = new HashMap<>();
		m.put(characterBean.getChId(), characterBean);
		s.add(characterBean);
		mycahe.setChlist(s);
		mycahe.setCharacterList(m);

		MvcResult result = mockMvc
				.perform(post("/postCharacter").content(mymap).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andReturn();
		int resultstatus = result.getResponse().getStatus();
		System.out.println(resultstatus);
		assertEquals(HttpStatus.OK, HttpStatus.valueOf(resultstatus));
	}

	@Test
	public void findAllCharacterTest() throws Exception {
		MvcResult result = mockMvc.perform(get("/characterlist").contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andReturn();
		MockHttpServletResponse resultRes = result.getResponse();
		int resultstatus = resultRes.getStatus();
		// System.out.println(resultRes.getContentAsString().length();
		assertEquals(HttpStatus.OK, HttpStatus.valueOf(resultstatus));
	}

	@Test
	public void deleteCharacterTest() throws Exception {
		postCharacterTest();
		MyCache mycahe = SimpsonsCodingChallengeApplication.context.getBean(MyCache.class);
		HashSet<CharacterBean> list = (HashSet<CharacterBean>) mycahe.getChlist();
		System.out.println(list.size());
		CharacterBean getonebean = null;
		for (CharacterBean characterBean2 : list) {
			getonebean = characterBean2;
		}
		String mymap = "{\"chId\":\"" + getonebean.getChId() + "\"}";
		MvcResult result = mockMvc
				.perform(delete("/deleteChracter").content(mymap).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andReturn();
		MockHttpServletResponse resultRes = result.getResponse();
		int resultstatus = resultRes.getStatus();
		System.out.println(list.size());
		assertEquals(HttpStatus.OK, HttpStatus.valueOf(resultstatus));
	}
	@Test
	public void putCharacterTest() throws Exception {
		postCharacterTest();
		MyCache mycahe = SimpsonsCodingChallengeApplication.context.getBean(MyCache.class);
		HashSet<CharacterBean> list = (HashSet<CharacterBean>) mycahe.getChlist();
		System.out.println(list.size());
		CharacterBean getonebean = null;
		for (CharacterBean characterBean2 : list) {
			getonebean = characterBean2;
		}
		String mymap = "{\"chId\":\"" + getonebean.getChId() + "\",\"firstName\":\"Shibil\"}";
		MvcResult result = mockMvc
				.perform(put("/putChracter").content(mymap).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andReturn();
		MockHttpServletResponse resultRes = result.getResponse();
		int resultstatus = resultRes.getStatus();
		System.out.println(list.size());
		assertEquals(HttpStatus.OK, HttpStatus.valueOf(resultstatus));
	}

}
