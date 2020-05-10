package com.springcourse.demo.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.springcourse.demo.domain.WatchlistItem;
import com.springcourse.demo.exception.DuplicateTitleException;
import com.springcourse.demo.exception.SizeWatchlistException;
import com.springcourse.demo.service.WatchlistService;

@Controller
public class WatchlistController {

	private WatchlistService watchlistService;
	
	@Autowired
	public WatchlistController(WatchlistService watchlistService) {
		super();
		this.watchlistService = watchlistService;
	}


	@GetMapping("/watchlistItemForm")
	public ModelAndView showWatchlistItemForm(@RequestParam(required = false) Integer id) {
		
		String viewName = "watchlistItemForm";
		
		Map<String,Object> model = new HashMap<String,Object>();
		
		WatchlistItem watchlistItem = watchlistService.findWatchlistItemById(id);
		
		if(watchlistItem == null)
			model.put("watchlistItem", new WatchlistItem());
		else
			model.put("watchlistItem", watchlistItem);
		
		return new ModelAndView(viewName,model);	
	}
	

	//Gère la requète POST
	@PostMapping("/watchlistItemForm")
	public ModelAndView submitWatchlistItem(@Valid WatchlistItem watchlistItem, BindingResult bindingResult) {
		
		if(bindingResult.hasErrors()) {
			return new ModelAndView("watchlistItemForm");
		}
		
		try {
			watchlistService.addOrUpdate(watchlistItem);
		} catch (DuplicateTitleException | SizeWatchlistException e) {
			if(e.getClass() == DuplicateTitleException.class ) {
				bindingResult.rejectValue("title", "", "This title already exist on your watchlist");
				return new ModelAndView("watchlistItemForm");
			}
			if(e.getClass() == SizeWatchlistException.class ) {
				bindingResult.rejectValue(null, "", "Your watchlist is filled !");
				return new ModelAndView("watchlistItemForm");
			}
		}
		
		RedirectView redirect = new RedirectView();
		redirect.setUrl("/watchlist");
		
		return new ModelAndView(redirect);
	}

	
	//Gère la requète GET 
	@GetMapping("/watchlist")
	public ModelAndView getWatchList() {

		String viewName = "watchlist";
		
		Map<String,Object> model = new HashMap<String,Object>();
		
		model.put("watchlistItems", watchlistService.getWatchlistItems());
		model.put("numberOfMovies",watchlistService.getWatchlistItemsSize());
		
		return new ModelAndView(viewName,model);		
	}

	
}
