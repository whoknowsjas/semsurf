package com.ost.semsurf.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.ost.semsurf.domain.Page;
import com.ost.semsurf.domain.User;
import com.ost.semsurf.service.DBPopulator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.template.Neo4jOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestBody;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Master Facade that facilitates interaction
 * between the client (browser) and server (database)
 * @author jworkman1
 */
@Controller
public final class RESTController {

	static Logger log = LoggerFactory.getLogger(RESTController.class);
	
	@Autowired DBPopulator repository;
	
	@RequestMapping(method=RequestMethod.GET, value="/test")
	@ResponseBody
	public final String test() {
		String msg = "success";
		log.debug(msg);
		return msg;
	}	
	
	@RequestMapping(method=RequestMethod.POST, value="/vpost")
	@ResponseBody
	public final boolean validatePost(@RequestBody final Page page) {	
		log.debug("URL: "+page.getUrl());
		if(null == page.getUrl() || page.getAttributes().size() < 1) return false;
		return true;
	}	
		
	@RequestMapping(method=RequestMethod.POST, value="/post")
	@ResponseBody
	public final boolean post(@RequestBody final Page[] pages) {			
		for(Page page : pages){
			//todo: add condition to check whether valid
			log.info("isValid: "+validatePost(page));
//			repository.populateDatabase(page);
//			repository.save(new User("Jayson"));			
		}
		return true;
	}	
	
	@RequestMapping(method=RequestMethod.POST, value="/post2/{nameId}")
	@ResponseBody
	public final boolean post(@PathVariable String nameId, @RequestBody final Page[] pages) {			
		log.info("THIS IS A USER: "+nameId);
//		List<Page> pages2 = new ArrayList<Page>();
//		pages2.add(new Page("test.com",new LinkedHashMap<String, String>()));
//		pages2.add(new Page(pages.get(0).getUrl(), null));
		User user = new User(nameId, pages);
//		ArrayList<Page> pages2 = new ArrayList<Page>();
//		for(Page page : pages){
//			pages2.add(new Page("test.com",null));
//			log.info("URL: "+page.getUrl());
//		}
//		user.setPages(pages2);
		repository.populateDatabase(user);
		return true;
	}	
	
	

}