package com.daarks.rest.webservices.restfulwebservices;

import org.springframework.data.jpa.repository.JpaRepository;

import com.daarks.rest.webservices.restfulwebservices.posts.Post;

public interface PostRepository extends JpaRepository<Post, Integer> {

}
