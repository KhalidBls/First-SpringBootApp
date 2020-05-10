package com.springcourse.demo.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.springcourse.demo.domain.WatchlistItem;

@Service
public class WatchlistRepository {

	private List<WatchlistItem> watchlistItems = new ArrayList<WatchlistItem>();
	private static int index = 1;
	
	public List<WatchlistItem> getList(){
		return watchlistItems;
	}
	
	public void addItem(WatchlistItem watchlistItem){
		watchlistItem.setId(index++);
		watchlistItems.add(watchlistItem);
	}
	
	public WatchlistItem findItemById(Integer id) {
		
		for (WatchlistItem watchlistItem : watchlistItems) {
			if(watchlistItem.getId().equals(id))
				return watchlistItem;
		}
		
		return null;
		
	}
	
	public WatchlistItem findItemByTitle(String title) {
		
		for (WatchlistItem watchlistItem : watchlistItems) {
			if(watchlistItem.getTitle().equals(title))
				return watchlistItem;
		}
		return null;
	}
	
	public boolean isFilled() {
		if(watchlistItems.size() == 5) {
			return true;
		}
		return false;
	}
	
}
