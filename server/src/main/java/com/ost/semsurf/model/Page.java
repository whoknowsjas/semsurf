package com.ost.semsurf.model;

import java.util.Map;
import java.util.HashMap;
import java.lang.Long;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.GraphId;

@NodeEntity
public class Page {
		@GraphId private Long sys_id;
		private String url;
		private Map<String, String> metaMap;
					
		public Page() {}

		public Page(String url,Map<String, String> metaMap) {
			this.url = url;
			this.metaMap = metaMap;			
		}

		public Long getSys_id() {
			return sys_id;
		}

		public void setSys_id(Long sys_id) {
			this.sys_id = sys_id;
		}

		public String getUrl(){
			return url;
		}
		
		public void setUrl(String url){
			this.url=url;
		}
		
		public Map<String, String> getMetaMap(){
			if(metaMap == null) metaMap = new HashMap<String, String>();
			return metaMap;
		}
		
		public void setMetaMap(Map<String, String> metaMap){
			this.metaMap=metaMap;
		}
		
		@Override
		public String toString() {
		    return org.apache.commons.lang.builder.ToStringBuilder.reflectionToString(this);
		}
			
}