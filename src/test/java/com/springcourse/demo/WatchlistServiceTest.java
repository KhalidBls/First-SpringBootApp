package com.springcourse.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import com.springcourse.demo.domain.WatchlistItem;
import com.springcourse.demo.repository.WatchlistRepository;
import com.springcourse.demo.service.MovieRatingService;
import com.springcourse.demo.service.WatchlistService;


@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)
public class WatchlistServiceTest {

	@Mock
	WatchlistRepository watchlistRepositoryMock;
	
	@Mock
	MovieRatingService movieRatingServiceMock;
	
	@InjectMocks
	WatchlistService watchlistService;
	
	
	@Test
	public void testGetWatchlistItemsShouldReturnsAllItemsFromRepository() {
		
		//Given
		WatchlistItem item1 = new WatchlistItem("Matrix", "8", "H", "",1 );
		WatchlistItem item2 = new WatchlistItem("Avatar", "8", "H", "",1 );
		List<WatchlistItem> ourList = new ArrayList<WatchlistItem>();
		
		ourList.add(item1);
		ourList.add(item2);
		
		when(watchlistRepositoryMock.getList()).thenReturn(ourList);
		
		//When
		List<WatchlistItem> result = watchlistService.getWatchlistItems();
		
		//Then
		assertTrue(result.size() == 2);
		assertTrue(result.get(0).getTitle().equals("Matrix"));
		assertTrue(result.get(1).getTitle().equals("Avatar"));
		
	}

	
	@Test
	public void testGetwatchlistItemsRatingFormOmdbServiceOverrideTheValueInItems() {
		//Given
		WatchlistItem item1 = new WatchlistItem("Matrix", "8", "H", "",1 );
		List<WatchlistItem> ourList = new ArrayList<WatchlistItem>();
		
		ourList.add(item1);
		
		when(watchlistRepositoryMock.getList()).thenReturn(ourList);
		when(movieRatingServiceMock.getMovieRating(any(String.class))).thenReturn("10");
	
		//When
		List<WatchlistItem> result = watchlistService.getWatchlistItems();
		
		//Then
		assertTrue(result.get(0).getRating().equals("10"));
		
	}
	
	
	@Test
	public void testGetWatchlistItemSizeWith2ItemsShouldReturn2() {
		//Arrange
		WatchlistItem item1 = new WatchlistItem("Matrix", "8", "H", "",1 );
		WatchlistItem item2 = new WatchlistItem("Avatar", "8", "H", "",1 );
		List<WatchlistItem> ourList = new ArrayList<WatchlistItem>();
		
		ourList.add(item1);
		ourList.add(item2);
		
		when(watchlistRepositoryMock.getList()).thenReturn(ourList);
		
		//Act
		int ourListSize = watchlistService.getWatchlistItemsSize();
		
		//Assert
		assertEquals(ourListSize,2);
		
	}
	
}
