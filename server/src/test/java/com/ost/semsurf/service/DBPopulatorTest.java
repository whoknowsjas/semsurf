package com.ost.semsurf.service;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.index.Index;
import org.neo4j.helpers.collection.ClosableIterable;
import org.neo4j.helpers.collection.IteratorUtil;
import org.neo4j.kernel.EmbeddedGraphDatabase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.template.Neo4jOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.ost.semsurf.domain.Attribute;
import com.ost.semsurf.domain.Link;
import com.ost.semsurf.domain.Page;
import com.ost.semsurf.domain.User;
import com.ost.semsurf.domain.Value;
import com.ost.semsurf.service.DBPopulator;

@ContextConfiguration(locations = "classpath:test-spring-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class DBPopulatorTest {

	static Logger log = LoggerFactory.getLogger(DBPopulatorTest.class);

	@Autowired DBPopulator repository;
	@Autowired Neo4jOperations template;

	User user;
	Set<Page> pages;

	@Before
	public void setup() {
		pages = new HashSet<Page>();
		user = new User("JaysonTest",null);
		
		this.user = repository.createUser(user);
		assertTrue("verify the user was persisted okay and an id was assigned", 
				(null != this.user.nodeId));	
	}
	
	@After
	public void tearDown(){	
		assertEquals("verify 1 users before purge ",1, IteratorUtil.count(template.findAll(User.class)));
		repository.deleteUser(this.user);
		assertEquals("confirm no users after purge",0, IteratorUtil.count(template.findAll(User.class)));
	}

	@Test
	public void testGetUser() {		
		assertTrue("verify existing user was found",null != repository.getUserByName(this.user.getName()));
		assertTrue("verify user has a stored id",repository.getUserByName(this.user.getName()).nodeId >= 0);
	}
	
	@Test
	public void testSavePages() {		
		this.pages.add(new Page("test1.com"));
		this.pages.add(new Page("test2.com"));
		assertEquals("confirm pages contains mre than 1 before saving", 2,pages.size());
		repository.savePages(user.getName(), this.pages);
		assertEquals(2,repository.getUserByName(this.user.getName()).getPages().size());
	}
	
	@Test
	public void testSavePagesVerifyNoDuplicates() {		
		this.pages.add(new Page("test1.com"));
		this.pages.add(new Page("test1.com"));
		repository.savePages(user.getName(), this.pages);
		assertEquals(2,repository.getUserByName(this.user.getName()).getPages().size());
	}
	
	@Test
	public void testSavePagesVerifyNoSavedDuplicates() {		
		this.pages.add(new Page("test1.com"));
		repository.savePages(user.getName(), this.pages);		
		assertEquals(1,repository.getUserByName(this.user.getName()).getPages().size());
		this.pages.add(new Page("test2.com"));
		assertEquals("confirm pages contains mre than 1 before saving", 2,pages.size());
		repository.savePages(user.getName(), this.pages);
		assertEquals(2,repository.getUserByName(this.user.getName()).getPages().size());
	}
	
	@Test
	public void testSaveMetaData(){				
		Page page = new Page("test1.com");
		page.getAttributes().add(new Attribute("title",new Value("test")));
		this.pages.add(page);
		repository.savePages(user.getName(), this.pages);
		assertTrue("ensure pages are found",repository.getUserByName(this.user.getName()).getPages().size() > 0);
		for(Page sPage : repository.getUserByName(this.user.getName()).getPages()){
			assertEquals(1,repository.getPage(sPage.nodeId).getAttributes().size());
		}		
	}
	
	@Test
	public void testSaveMetaDataWithMultipleValues(){				
		Page page = new Page("test1.com");
		page.getAttributes().add(new Attribute("keywords","one,two,three,four"));
		this.pages.add(page);
		repository.savePages(user.getName(), this.pages);
		assertTrue("ensure pages are found",repository.getUserByName(this.user.getName()).getPages().size() > 0);
		for(Page sPage : repository.getUserByName(this.user.getName()).getPages()){
			assertTrue("ensure attributes are found",repository.getPage(sPage.nodeId).getAttributes().size() > 0);
			for(Attribute sAttribute : repository.getPage(sPage.nodeId).getAttributes()){
				assertEquals(4,repository.getAttribute(sAttribute.nodeId).getValues().size());
			}
		}
	}

	@Test
	public void test() {
		assertTrue(true);
	}

}
