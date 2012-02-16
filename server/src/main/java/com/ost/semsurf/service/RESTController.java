package com.ost.semsurf.service;

import java.util.List;

import com.ost.semsurf.domain.Page;
import com.ost.semsurf.domain.User;
import com.ost.semsurf.repository.DBPopulator;
import com.ost.semsurf.repository.GraphRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
//	@Autowired UserRepository repository;

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
		if(null == page.getUrl() || page.getMetaMap().size() < 1) return false;
		return true;
	}	
		
	@RequestMapping(method=RequestMethod.POST, value="/post")
	@ResponseBody
	public final boolean post(@RequestBody final Page[] pages) {			
		for(Page page : pages){
			//todo: add condition to check whether valid
			log.info("isValid: "+validatePost(page));
			repository.populateDatabase(page);
//			repository.save(new User("Jayson"));			
		}
		return true;
	}	
	
	

}