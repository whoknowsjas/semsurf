package com.ost.semsurf.repository;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.PropertyContainer;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.RelationshipType;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.index.Index;
import org.neo4j.kernel.EmbeddedGraphDatabase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.template.Neo4jOperations;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ost.semsurf.model.Page;
import com.ost.semsurf.model.User;

@Transactional
@Repository
public class DBPopulator {
	
	static Logger log = LoggerFactory.getLogger(DBPopulator.class);

//	GraphDatabaseService repository = new EmbeddedGraphDatabase("target/test-db3");
	@Autowired UserRepository repository;
		
	public void populateDatabase(Page page) {
		log.debug("URL: "+page.getUrl());
		User user = createUser();
//		repository.save(new User(userName));
////		final User user = repository.findByPropertyValue("userName", "Jayson");
//		System.out.println("nameIndex: "+user.getSys_id());
		//User user = repository.("userName", userName, User.class);
				
	}

	private User createUser() {
		String userName = "Jayson";
		User user = repository.findByPropertyValue("userName", userName);
		boolean isExistingUser = (user!=null);
		log.debug("isExisting User: "+isExistingUser);
		if(!isExistingUser){
			user = new User(userName);
			repository.save(new User(userName));
			log.debug("User Created: "+userName);
		}
		return user;
	}
	
//	@Transactional
//	public void populateDatabase(Page page) {
//		log.debug("URL: "+page.getUrl());		
//		Index<Node> nodeIndex = repository.index().forNodes("nodes");
//		//Transaction tx = repository.beginTx();
//		//try {
//			
//			String userNm = "Jayson";
//			Node user = nodeIndex.get("name", userNm).getSingle();
//			if(null == user){
//				log.debug("creating user: "+userNm);
//				user = repository.createNode();
//				user.setProperty("name", userNm);
//				nodeIndex.add(user, "name", userNm);
//			}
//			
//			Node link = repository.createNode();
//			link.setProperty("url", page.getUrl());
//			//link.setProperty("title", page.getMetaMap().get("title"));
//
//			Relationship rel1 = user.createRelationshipTo(link,RelTypes.LINKS);
//			rel1.setProperty("visibility", "public");
//			
//			//parse key words
//			String keywordString = page.getMetaMap().get("keywords");
//			if(null != keywordString){
//				String[] keywords = keywordString.split(",");			
//				for(String keyword : keywords){
//					Node kwNode = nodeIndex.get("keyword", keyword).getSingle();
//					if(null == kwNode){
//						log.debug("creating keyword: "+keyword);
//						kwNode = repository.createNode();
//						kwNode.setProperty("keyword", keyword);
//						nodeIndex.add(kwNode, "keyword", keyword);
//					}
//					Relationship rel2 = link.createRelationshipTo(kwNode,RelTypes.RELATED);
//					rel2.setProperty("visibility", "public");
//				}			
//			}
//
//			//tx.success();
//			
////		} finally {
////			tx.finish();
////		}
//	}
	
	public enum RelTypes implements RelationshipType {
		LINKS, RELATED
	}
}
