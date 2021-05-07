package com.qa.musichwa.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.qa.musichwa.domain.Music;
import com.qa.musichwa.repos.MusicRepo;

@Service
public class MusicService implements ServiceIF<Music> {
	
	private MusicRepo repo;
	
	public MusicService(MusicRepo repo) {
		this.repo = repo;
	}
	
	@Override
	public Music create(Music music) {
		return this.repo.save(music);
	}

	@Override
	public List<Music> getAll() {
		return this.repo.findAll();
	}

	@Override
	public Music getOne(long id) {
		return this.repo.findById(id).get();
	}

	@Override
	public Music update(long id, Music newMusic) {
		Optional<Music> existingOptional = this.repo.findById(id);
        Music existing = existingOptional.get();

        existing.setType(newMusic.getType());
        existing.setName(newMusic.getName());
        existing.setArtist(newMusic.getArtist());
        existing.setYear(newMusic.getYear());
        
        return this.repo.saveAndFlush(existing);
	}

	@Override
	public boolean delete(long id) {
        this.repo.deleteById(id);
        return !this.repo.existsById(id);
	}

}
