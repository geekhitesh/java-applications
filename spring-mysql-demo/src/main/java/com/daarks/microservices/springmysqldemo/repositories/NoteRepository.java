package com.daarks.microservices.springmysqldemo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.daarks.microservices.springmysqldemo.entities.Note;

public interface NoteRepository extends JpaRepository<Note, Long> {

}
