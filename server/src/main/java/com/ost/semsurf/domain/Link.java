package com.ost.semsurf.domain;

import org.springframework.data.neo4j.annotation.EndNode;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.RelationshipEntity;
import org.springframework.data.neo4j.annotation.StartNode;

@RelationshipEntity(type = "LINKED")
public class Link {
	@GraphId public Long nodeId;
	
	@StartNode User user;
	@EndNode Page page;
	
	public Link() {}
	
	public Link(User user, Page page) {
		this.user = user;
		this.page = page;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Page getPage() {
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}
	
	

}
