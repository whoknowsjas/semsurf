package com.ost.semsurf.domain;

import java.util.HashSet;
import java.util.Set;

import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;

@NodeEntity
public class Page {
	@GraphId
	public Long nodeId;

	@Indexed
	private String url;
	@RelatedTo(elementClass = Attribute.class, type = "HAS")
	private Set<Attribute> attributes;

	public Page() {
	}

	public Page(String url) {
		this.url = url;
	}

	public Page(String url, Set<Attribute> attributes) {
		this.url = url;
		this.attributes = attributes;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	public Set<Attribute> getAttributes() {
		if (null == attributes)
			attributes = new HashSet<Attribute>();
		return attributes;
	}

	public void setAttributes(Set<Attribute> attributes) {
		this.attributes = attributes;
	}

	@Override
	public String toString() {
		return org.apache.commons.lang.builder.ToStringBuilder.reflectionToString(this);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Page other = (Page) obj;
		if (url == null) {
			if (other.url != null)
				return false;
		} else if (!url.equals(other.url))
			return false;
		return true;
	}
}