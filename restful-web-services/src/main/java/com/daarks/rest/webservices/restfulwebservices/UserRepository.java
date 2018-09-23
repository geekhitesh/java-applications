package com.daarks.rest.webservices.restfulwebservices;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.daarks.rest.webservices.restfulwebservices.users.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

}
