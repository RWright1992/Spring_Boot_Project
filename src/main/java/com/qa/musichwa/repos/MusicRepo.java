package com.qa.musichwa.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.musichwa.domain.Music;

@Repository
public interface MusicRepo extends JpaRepository <Music, Long> {
	
	
	
}
