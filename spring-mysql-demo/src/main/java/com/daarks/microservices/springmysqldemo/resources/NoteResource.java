package com.daarks.microservices.springmysqldemo.resources;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.daarks.microservices.springmysqldemo.entities.Note;
import com.daarks.microservices.springmysqldemo.repositories.NoteRepository;

@RestController
public class NoteResource {
	
	@Autowired
	private NoteRepository noteRepository;

	@GetMapping("/notes")
	public List<Note> getAllNotes() {
		
		return noteRepository.findAll();
	}
	
	@PostMapping("/notes")
	public Note create(@Valid @RequestBody Note note) {
		
		Note savedNote = noteRepository.save(note);
		return savedNote;
	}
}
