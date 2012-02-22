package com.ost.semsurf.domain;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;

@NodeEntity
public class Attribute {
	@GraphId
	public Long nodeId;

	@Indexed
	private String key;
	private String value;
	@RelatedTo(elementClass = Value.class, type = "CONTAINS")
	private Set<Value> values;

	public Attribute() {
	}

	public Attribute(String key, String value) {
		this.key = key;
		parseValueString(value);
	}

	public Attribute(String key, Value value) {
		this.key = key;
		this.getValues().add(value);
	}

	public Attribute(String key, Set<Value> values) {
		this.key = key;
		this.values = values;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		parseValueString(value);
	}

	public Set<Value> getValues() {
		if (null == values)
			values = new HashSet<Value>();
		return values;
	}

	public void setValues(Set<Value> values) {
		this.values = values;
	}

	private void parseValueString(String value) {
		if (null != value) {
			for (String splitValue : value.split(",")) {
				if (!StringUtils.isBlank(splitValue)) {
					getValues().add(new Value(splitValue));
				}
			}
			this.value = null;
		}
	}

	@Override
	public String toString() {
		return org.apache.commons.lang.builder.ToStringBuilder
				.reflectionToString(this);
	}

}
