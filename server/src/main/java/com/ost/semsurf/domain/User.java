package com.ost.semsurf.domain;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;
import org.springframework.data.neo4j.annotation.RelatedToVia;

@NodeEntity
public class User {
	@GraphId public Long nodeId;
	
	@Indexed String name;
	@RelatedTo(elementClass = Page.class, type = "LINKED") private Set<Page> pages;
//	@RelatedToVia private Set<Link> links;
	
	public User() {}

	public User(String name, Page[] pages) {
		this.name = name;	
		if(null != pages)
			setPages(new HashSet<Page>(Arrays.asList(pages)));
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Page> getPages() {
		if(null == pages) pages = new HashSet<Page>();
		return pages;
	}
	
	public void setPages(Set<Page> pages) {
		this.pages = pages;
	}

//	public void setLinks(Set<Link> links) {
//		this.links = links;
//	}
//	
//	public Set<Link> getLinks() {
//		if(null == links) links = new HashSet<Link>();
//		return links;
//	}

	
}
