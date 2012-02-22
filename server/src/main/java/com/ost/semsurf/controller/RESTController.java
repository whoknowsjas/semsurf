package com.ost.semsurf.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ost.semsurf.domain.Page;
import com.ost.semsurf.domain.User;
import com.ost.semsurf.service.DBPopulator;

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
		
	@RequestMapping(method=RequestMethod.POST, value="/vposts")
	@ResponseBody
	public final boolean validatePosts(@RequestBody final Page[] pages) {			
		boolean isValid = true;
		for(Page page : pages){
			isValid = validatePost(page);
			if(!isValid) break;
		}
		return isValid;
	}	
	
	@RequestMapping(method=RequestMethod.POST, value="/post2/{nameId}")
	@ResponseBody
	public final boolean post(@PathVariable String nameId, @RequestBody final Page[] pages) {
		log.debug("user: " + nameId + ", posting to server.");
		//todo: perform a lookup on the nameId to resolve to the userId 
		//(dependency: nameId has previously been created and registered in new login inst)
		User user = new User(nameId, pages);
		repository.populateDatabase(user);
		return true;
	}	
	
}