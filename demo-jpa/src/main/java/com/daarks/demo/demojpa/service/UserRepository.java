package com.daarks.demo.demojpa.service;

import org.springframework.data.jpa.repository.JpaRepository;

import com.daarks.demo.demojpa.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
