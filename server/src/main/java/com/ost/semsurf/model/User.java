package com.ost.semsurf.model;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;

@NodeEntity
public class User {
	@GraphId Long sys_id;
	@Indexed String userName;	
	
	public User() {}

	public User(String userName) {
		this.userName = userName;
	}
	
	public Long getSys_id() {
		return sys_id;
	}
	public void setSys_id(Long sys_id) {
		this.sys_id = sys_id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	

}
