package com.ost.semsurf.domain;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;

@NodeEntity
public class User {
	@GraphId
	public Long nodeId;

	@Indexed
	String name;
	@RelatedTo(elementClass = Page.class, type = "LINKED")
	private Set<Page> pages;

	public User() {
	}

	public User(String name, Page[] pages) {
		this.name = name;
		if (null != pages)
			setPages(new HashSet<Page>(Arrays.asList(pages)));
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Page> getPages() {
		if (null == pages)
			pages = new HashSet<Page>();
		return pages;
	}

	public void setPages(Set<Page> pages) {
		this.pages = pages;
	}
}
