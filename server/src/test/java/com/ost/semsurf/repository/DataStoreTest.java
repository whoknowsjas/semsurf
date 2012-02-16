package com.ost.semsurf.repository;

import static junit.framework.Assert.assertTrue; 
import static junit.framework.Assert.assertEquals; 

import org.junit.Test; 
import org.junit.runner.RunWith; 
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner; 
import org.springframework.test.context.ContextConfiguration; 
import org.springframework.web.client.RestTemplate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.neo4j.kernel.EmbeddedGraphDatabase;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Transaction;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.support.Neo4jTemplate;
import org.springframework.transaction.annotation.Transactional;

import com.ost.semsurf.domain.Page;

@ContextConfiguration(locations = "classpath:test-spring-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class DataStoreTest {
	
	static Logger log = LoggerFactory.getLogger(DataStoreTest.class);
	
	@Autowired Neo4jTemplate template;
	
	@Test @Transactional public void testSpringGraphDbSave(){
		
		Page obj = new Page();
		obj.setUrl("test.com");		
		template.save(obj);	
		log.debug("finding");
		Page retObj = template.findOne(obj.getSys_id(), Page.class);
		log.debug("springGraphDbSaveResponse: "+retObj);
		assertEquals("test.com", retObj.getUrl());
	}
	
	@Test
	public void testSimpleSave(){
		GraphDatabaseService graphDb = new EmbeddedGraphDatabase( "build/test-db2" );
		registerShutdownHook( graphDb );	
		Transaction tx = graphDb.beginTx();
		try {
			Node firstNode = graphDb.createNode();
			firstNode.setProperty( "message", "Testing123" );
			log.debug("simpleSaveResponse: "+firstNode.getProperty( "message" ));
			assertEquals("Testing123", firstNode.getProperty( "message" ) );
			tx.success();
		}
		finally {
      tx.finish();
    }
	}	
	
	@Test 
	public void test(){
		assertTrue(true);
	}
	
	private static void registerShutdownHook( final GraphDatabaseService graphDb )
  {
    // Registers a shutdown hook for the Neo4j instance so that it
    // shuts down nicely when the VM exits (even if you "Ctrl-C" the
    // running example before it's completed)
    Runtime.getRuntime().addShutdownHook( new Thread()
    {
        @Override
        public void run()
        {
            graphDb.shutdown();
        }
    } );
  }

}