package com.startaideia.vuttr;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.startaideia.vuttr.model.Tool;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class ApplicationTests {

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

		mockMvc.perform(
				post("/tools").contentType("application/json").content(objectMapper.writeValueAsString(newTool)))
				.andExpect(content().contentType("application/json")).andExpect(status().isCreated());

	}

	@Test
	void toolsListAll() throws Exception {

		Tool newTool = new Tool();

		newTool.setTitle("ListAll");
		newTool.setLink("test");
		newTool.setDescription("test");
		newTool.setTags(Arrays.asList(new String[] { "test1", "test2" }));

		mockMvc.perform(
				post("/tools").contentType("application/json").content(objectMapper.writeValueAsString(newTool)))
				.andExpect(content().contentType("application/json")).andExpect(status().isCreated());

		mockMvc.perform(get("/tools").contentType("application/json"))
				.andExpect(content().contentType("application/json")).andExpect(status().isOk())
				.andExpect(jsonPath("$.title").value("ListAll"));

	}

	@Test
	void toolsListByTag() throws Exception {

		Tool newTool = new Tool();

		newTool.setTitle("ListByTag");
		newTool.setLink("test");
		newTool.setDescription("test");
		newTool.setTags(Arrays.asList(new String[] { "test1", "test2" }));

		mockMvc.perform(
				post("/tools").contentType("application/json").content(objectMapper.writeValueAsString(newTool)))
				.andExpect(content().contentType("application/json")).andExpect(status().isCreated());

		mockMvc.perform(get("/tools").contentType("application/json").param("tag", "test1")).andExpect(status().isOk())
				.andExpect(jsonPath("title").value("ListByTag"));

	}

	@Test
	void toolDelete() throws Exception {
		mockMvc.perform(delete("/tools/99").contentType("application/json")).andExpect(status().isNoContent());
	}
}
