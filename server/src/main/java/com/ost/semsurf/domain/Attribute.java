package com.ost.semsurf.domain;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;

@NodeEntity
public class Attribute {
	static Logger log = LoggerFactory.getLogger(Attribute.class);
	
	@GraphId
	public Long nodeId;

	@Indexed
	private String key;
	private String value;
	@RelatedTo(elementClass = Value.class, type = "CONTAINS")
	private Set<Value> values;

	// @RelatedTo(elementClass = Page.class, type = "CONTAINS")
	// private Set<Value> values;

	public Attribute() {
	}
	
	public Attribute(String key, String value) {
		this.key = key;
		this.values = parseValueString(value);
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
		this.value = value;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Attribute other = (Attribute) obj;
		if (key == null) {
			if (other.key != null)
				return false;
		} else if (!key.equals(other.key))
			return false;
		return true;
	}

	public Set<Value> getValues() {
		if(null == values) values = new HashSet<Value>();
		return values;
	}

	private Set<Value> parseValueString(String value) {
		Set<Value> values = new HashSet<Value>();
		if(null != value) {			
			for(String splitValue : value.split(",")){ 
				if(!StringUtils.isBlank(splitValue))	{
//					log.debug("SplitValue:  "+splitValue);					
					values.add(new Value(splitValue));
				}
			}
			this.value = null;		
		}
//		log.debug("value size: "+values.size());
		return values;
	}

	public void setValues(Set<Value> values) {
		this.values = values;
	}

	// public Set<Value> getValues() {
	// return values;
	// }
	// public void setValues(Set<Value> values) {
	// this.values = values;
	// }
	
	@Override
	public String toString() {
		return org.apache.commons.lang.builder.ToStringBuilder.reflectionToString(this);
	}

}
