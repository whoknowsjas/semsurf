GraphDatabaseService repository = new EmbeddedGraphDatabase("target/test-db3");
//	@Transactional
	public User createUser(User user) {				
		User user2 = repository.findByPropertyValue("name", user.getName());
		boolean isExistingUser = (user2!=null);
		log.debug("isExisting User: "+isExistingUser);
		if(!isExistingUser){	
//			template.save(user);			
			log.debug("User Created: "+user.getName());
		}else{
//			for(Page obj : user.getPages()){
//				template.createRelationshipBetween(user, obj, Link.class, "LINKED", true);
//				lrepository.save(new Link(user,page));
//				log.info(""+(null != lrepository));
//				assert(null != lrepository);
//				lrepository.save(link);
//			}
			user2.getPages().addAll(user.getPages());
//			repository.save(user2);
		}

		return user;
	}
	
	public void createPage(Set<Page> pages) {
//		for(Page page : pages){
//			prepository.save(page);
//		}
	}
	

	
//	public enum RelTypes implements RelationshipType {
//		LINKS, RELATED
//	}

GraphRepository<User>, NamedIndexRepository<User>, RelationshipOperationsRepository<User>

//		User user2 = new User("jasyon",null);
//		Page page = new Page("test.com");
//		Page page2 = new Page("test2.com");
//		Set<Page> pages = new HashSet<Page>();
//		pages.add(new Page("test.com",null));
//		user.setPages(pages);
//		pages.add(page2);
//		ArrayList<Page> pages = new ArrayList<Page>();
//		pages.toArray(user.getPages());
//		log.info("count -- "+user.getPages().size());
		
//		user2.setPages(pages);
//		prepository.save(user.getPages());
//		repository.save(user);
//		log.info("COUNT : "+user.getPages().size());
//		
//		Link link = new Link(user2, page);
//		lrepository.save(link);
//		template.createRelationshipBetween(user2, page, Link.class, "LINKED", true);
		



//	private void createUser(User user2) {
//		GraphDatabaseService repository = new EmbeddedGraphDatabase("build/test-db3");
//		Index<Node> nodeIndex = repository.index().forNodes("nodes");
//		Transaction tx = repository.beginTx();
//		try {
//			
//			String userNm = user2.getUserName();
//			Node user = nodeIndex.get("name", userNm).getSingle();
//			if(null == user){
//				log.debug("creating user: "+userNm);
//				user = repository.createNode();
//				user.setProperty("name", userNm);
//				nodeIndex.add(user, "name", userNm);
//			}
//			
//			Node link = repository.createNode();
//			link.setProperty("url", ((Page) (user2.getPages().toArray()[0])).getUrl());
//			
//			Relationship rel1 = user.createRelationshipTo(link,RelTypes.LINKS);
//			rel1.setProperty("visibility", "public");
//			
//			tx.success();
//			
//		} finally {
//			tx.finish();
//		}
//	}
	
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