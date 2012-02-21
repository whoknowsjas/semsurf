package com.ost.semsurf.controller;

import com.ost.semsurf.domain.Page;

import static junit.framework.Assert.assertTrue; 
import static junit.framework.Assert.assertFalse; 
import static junit.framework.Assert.assertEquals; 

import org.junit.Test; 
import org.junit.runner.RunWith; 
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner; 
import org.springframework.test.context.ContextConfiguration; 
import org.springframework.web.client.RestTemplate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RESTControllerTest {
	
	static String BASE_URL = "http://localhost:8080/server/service";
	static Logger log = LoggerFactory.getLogger(RESTControllerTest.class);	

	@Test
	public void testService(){
		String response = new RestTemplate().getForObject(BASE_URL+"/test", String.class, new Object[]{});
		log.debug("testResponse: "+response);
		assertEquals("success",response);
	}
	
	@Test
	public void testVPostService(){
		boolean response = new RestTemplate().postForObject(BASE_URL+"/vpost", new Page(), Boolean.class, new Object[]{});
		log.debug("vPostResponse: "+response);
		assertFalse(response);
	}
	
	@Test 
	public void test(){
		assertTrue(true);
	}

}