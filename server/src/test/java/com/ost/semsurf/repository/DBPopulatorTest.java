package com.ost.semsurf.repository;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.index.Index;
import org.neo4j.kernel.EmbeddedGraphDatabase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ost.semsurf.model.Page;
import com.ost.semsurf.model.User;

@ContextConfiguration(locations = "classpath:test-spring-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class DBPopulatorTest {

	static Logger log = LoggerFactory.getLogger(DBPopulatorTest.class);

	@Autowired
	DBPopulator repository;

	Page page;

	@Before
	public void setup() {
		page = new Page("test.com", null);
	}

	@Test
	public void testPopulateDatabase() {
		repository.populateDatabase(page);
	}

	@Test
	public void test() {
		assertTrue(true);
	}

	@Test
	public void createUser() {
		GraphDatabaseService repository = new EmbeddedGraphDatabase("build/test-db3");
		String userName = "Jayson";
		Index<Node> nodeIndex = repository.index().forNodes("nodes");
		Transaction tx = repository.beginTx();
		Node user = nodeIndex.get("userName", userName).getSingle();
		log.debug("USER IS: " + user);
		boolean isExistingUser = (user != null);
		log.debug("isExisting User: " + isExistingUser);
		if (!isExistingUser) {
			user = repository.createNode();
			user.setProperty("userName", userName);
			nodeIndex.add(user, "userName", userName);
			log.debug("User Created: " + userName);
		}
		tx.success();
		tx.finish();
	}

	public void populateDatabase(Page page) {
		GraphDatabaseService repository = new EmbeddedGraphDatabase("build/test-db4");
		log.debug("URL: " + page.getUrl());
		Index<Node> nodeIndex = repository.index().forNodes("nodes");
		Transaction tx = repository.beginTx();
		try {

			String userNm = "Jayson";
			Node user = nodeIndex.get("name", userNm).getSingle();
			if (null == user) {
				log.debug("creating user: " + userNm);
				user = repository.createNode();
				user.setProperty("name", userNm);
				nodeIndex.add(user, "name", userNm);
			}

			Node link = repository.createNode();
			link.setProperty("url", page.getUrl());
			// link.setProperty("title", page.getMetaMap().get("title"));

			Relationship rel1 = user.createRelationshipTo(link, DBPopulator.RelTypes.LINKS);
			rel1.setProperty("visibility", "public");

			// parse key words
			String keywordString = page.getMetaMap().get("keywords");
			if (null != keywordString) {
				String[] keywords = keywordString.split(",");
				for (String keyword : keywords) {
					Node kwNode = nodeIndex.get("keyword", keyword).getSingle();
					if (null == kwNode) {
						log.debug("creating keyword: " + keyword);
						kwNode = repository.createNode();
						kwNode.setProperty("keyword", keyword);
						nodeIndex.add(kwNode, "keyword", keyword);
					}
					Relationship rel2 = link.createRelationshipTo(kwNode,DBPopulator.RelTypes.RELATED);
					rel2.setProperty("visibility", "public");
				}
			}

			tx.success();

		} finally {
			tx.finish();
		}
	}
}
