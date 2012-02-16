package com.ost.semsurf.repository;

import org.springframework.data.neo4j.repository.GraphRepository;

import com.ost.semsurf.domain.User;

public interface UserRepository extends GraphRepository<User> {

}
