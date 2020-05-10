package com.springcourse.demo;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.springcourse.demo.service.WatchlistService;

@WebMvcTest
@RunWith(SpringRunner.class)
public class WatchlistControllerTest {
	
	@Autowired
	MockMvc mockMvc;
	
	@MockBean
	WatchlistService watchlistService;

	@Test
	public void testShowWatchlistItemForm() throws Exception {
		
		mockMvc.perform(get("/watchlistItemForm"))
		 .andExpect(status().is2xxSuccessful())
		 .andExpect(view().name("watchlistItemForm"))
		 .andExpect(model().size(1))
		 .andExpect(model().attributeExists("watchlistItem"));
	}
	
	@Test
	public void testSubmitWatchlistItemForm() throws Exception {
		mockMvc.perform(post("/watchlistItemForm")
				.param("title", "Top Gun")
				.param("rating", "8")
				.param("priority", "M"))
		.andExpect(status().is3xxRedirection())
		.andExpect(redirectedUrl("/watchlist"));
	}
	
	@Test
	public void testGetWatchList() throws Exception {
		
		mockMvc.perform(get("/watchlist"))
		 .andExpect(status().is2xxSuccessful())
		 .andExpect(view().name("watchlist"))
		 .andExpect(model().size(2))
		 .andExpect(model().attributeExists("watchlistItems"))
		 .andExpect(model().attributeExists("numberOfMovies"));
		
	}
	
}
