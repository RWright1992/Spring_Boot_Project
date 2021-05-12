package com.qa.musichwa.rest;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.musichwa.domain.Music;
import com.qa.musichwa.service.MusicService;

@RestController
@RequestMapping("/api")
public class MusicController {
	
	private MusicService service;
	
	public MusicController(MusicService service) {
		super();
		this.service = service;
	}
	
	// CREATE
	@PostMapping("/create")
	public ResponseEntity<Music> createMusic(@RequestBody Music music) {
		return new ResponseEntity<Music>(this.service.create(music), HttpStatus.CREATED);
	}
	
	// READ ALL
	@GetMapping("/getAll")
	public ResponseEntity<List<Music>> getAll() {
		return new ResponseEntity<List<Music>>(this.service.getAll(), HttpStatus.OK);
	}
	
	// READ ONE
	@GetMapping("/getOne/{id}")
	public ResponseEntity<Music> getOne(@PathVariable long id) {
		return new ResponseEntity<Music>(this.service.getOne(id), HttpStatus.OK);
	}
	
	// DELETE
	@DeleteMapping("/remove/{id}")
	public ResponseEntity<Boolean> delete(@PathVariable long id) {
		return (this.service.delete(id)) ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : 
			new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	// UPDATE
	@PutMapping("/update/{id}")
	public ResponseEntity<Music> update(@RequestBody Music newMusic, @PathVariable long id) {
		return new ResponseEntity<Music>(this.service.update(id, newMusic), HttpStatus.ACCEPTED);
	}
}
