package com.springcourse.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.springcourse.demo.domain.WatchlistItem;
import com.springcourse.demo.exception.DuplicateTitleException;
import com.springcourse.demo.exception.SizeWatchlistException;
import com.springcourse.demo.repository.WatchlistRepository;

@Service
public class WatchlistService {

	WatchlistRepository watchlistRepository;
	MovieRatingService movieRatingService;
	
	
	@Autowired
	public WatchlistService(WatchlistRepository watchlistRepository,MovieRatingService movieRatingService){
		super();
		this.watchlistRepository = watchlistRepository;
		this.movieRatingService = movieRatingService;
	}
	
	
	public List<WatchlistItem> getWatchlistItems(){
		
		List<WatchlistItem> watchlistItems = watchlistRepository.getList();
		for (WatchlistItem watchlistItem : watchlistItems) {
			String rating = movieRatingService.getMovieRating(watchlistItem.getTitle());
			
			if(rating!=null || rating!="")
				watchlistItem.setRating(rating);
		}
		
		return watchlistItems;
	}
	
	
	public int getWatchlistItemsSize() {
		return watchlistRepository.getList().size();
	}
	
	
	public WatchlistItem findWatchlistItemById(Integer id) {
		return watchlistRepository.findItemById(id);
	}
	
	
	public void addOrUpdate(WatchlistItem watchlistItem) throws DuplicateTitleException, SizeWatchlistException {
		
		WatchlistItem existingItem = findWatchlistItemById(watchlistItem.getId());
		
		if (existingItem == null) {
			
			if(watchlistRepository.findItemByTitle(watchlistItem.getTitle()) != null) {
				throw new DuplicateTitleException();
			}
			if(watchlistRepository.isFilled()) {
				throw new SizeWatchlistException();
			}
			watchlistRepository.addItem(watchlistItem);
		}else {
			existingItem.setComment(watchlistItem.getComment());
			existingItem.setRating(watchlistItem.getRating());
			existingItem.setTitle(watchlistItem.getTitle());
			existingItem.setPriority(watchlistItem.getPriority());
		}
	}
	
}
