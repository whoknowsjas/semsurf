package com.ost.semsurf.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

import com.ost.semsurf.domain.Attribute;
import com.ost.semsurf.domain.Link;
import com.ost.semsurf.domain.Page;
import com.ost.semsurf.domain.User;
import com.ost.semsurf.repository.UserRepository;

@Service
@Transactional
public class DBPopulator {
	
	static Logger log = LoggerFactory.getLogger(DBPopulator.class);
	@Autowired UserRepository repository;
	@Autowired Neo4jOperations template;
	
	public void populateDatabase(User user) {
//		User sUser = repository.findByPropertyValue("name", user.getName());
//		boolean isExistingUser = (sUser!=null);
//		if(isExistingUser) {
//			sUser.setPages(user.getPages());
//			user = sUser;
//		}
//		repository.save(user);		
		User sUser = getUserByName(user.getName());
		boolean isExistingUser = (sUser!=null);
		if(isExistingUser) user.nodeId = sUser.nodeId; 					
		repository.save(user);
	}
	
	public User createUser(User user) {		
		return repository.save(user);		
	}
	
	public User getUserByName(String name) {		
		return repository.findByPropertyValue("name", name);	
	}
	
	public void deleteUser(User user) {		
		repository.delete(user);		
	}
	
	public boolean savePages(String name, Set<Page> pages){
		User user = getUserByName(name);
		if(null == user) return false;
		user.setPages(pages);
		repository.save(user);
		return true;
	}
	
	public Page getPage(long id){
		return template.findOne(id, Page.class);
	}

	public Attribute getAttribute(long id){
		return template.findOne(id, Attribute.class);
	}

}
