package com.startaideia.vuttr;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.startaideia.vuttr.model.Tool;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class ApplicationTests {

	private final String BASE_URL = "/tools";

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	void contextLoads() {
	}

	@Test
	void toolCreate() throws Exception {

		Tool newTool = new Tool();

		newTool.setTitle("test");
		newTool.setLink("test");
		newTool.setDescription("test");
		newTool.setTags(Arrays.asList(new String[] { "test1", "test2" }));

		mockMvc.perform(post(BASE_URL).header("Origin", "http://localhost:3000")
				.contentType(MediaType.APPLICATION_JSON_VALUE).content(objectMapper.writeValueAsString(newTool)))
				.andExpect(status().isCreated());

	}

	@Test
	void toolsListAll() throws Exception {

		Tool newTool = new Tool();

		newTool.setTitle("ListAll");
		newTool.setLink("test");
		newTool.setDescription("test");
		newTool.setTags(Arrays.asList(new String[] { "test1", "test2" }));

		mockMvc.perform(post(BASE_URL).header("Origin", "http://localhost:3000")
				.contentType(MediaType.APPLICATION_JSON_VALUE).content(objectMapper.writeValueAsString(newTool)))
				.andExpect(status().isCreated());

		mockMvc.perform(
				get(BASE_URL).header("Origin", "http://localhost:3000").contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andExpect(jsonPath("$.[0].title").value("ListAll"));

	}

	@Test
	void toolsListByTag() throws Exception {

		Tool newTool = new Tool();

		newTool.setTitle("ListAll");
		newTool.setLink("test");
		newTool.setDescription("test");
		newTool.setTags(Arrays.asList(new String[] { "test1", "test2" }));

		mockMvc.perform(post(BASE_URL).header("Origin", "http://localhost:3000")
				.contentType(MediaType.APPLICATION_JSON_VALUE).content(objectMapper.writeValueAsString(newTool)))
				.andExpect(status().isCreated());

		mockMvc.perform(get(BASE_URL).header("Origin", "http://localhost:3000")
				.contentType(MediaType.APPLICATION_JSON_VALUE).param("tag", "test1")).andExpect(status().isOk())
				.andExpect(jsonPath("$.[0].title").value("ListAll"));

	}

	@Test
	void toolDelete() throws Exception {
		mockMvc.perform(delete("/tools/99").header("Origin", "http://localhost:3000")
				.contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isNoContent());
	}
}
