package com.qa.musichwa.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import com.qa.musichwa.domain.Music;
import com.qa.musichwa.repos.MusicRepo;

@RunWith(MockitoJUnitRunner.class)
public class MusicServiceUnitTest {
	
	@InjectMocks
	private MusicService service;
	
	@Mock
	private MusicRepo repo;
	
	@Test
	public void createTest() {
		Music input = new Music("Single", "Little More Love", "AJ Tracey", 2021);
		Music output = new Music(1L, "Single", "Little More Love", "AJ Tracey", 2021);
		
		Mockito.when(this.repo.save(input)).thenReturn(output);
		
		assertEquals(output, this.service.create(input));
		
		Mockito.verify(this.repo, Mockito.times(1)).save(input);
	}
	
	@Test
	public void getAllTest() {
		List<Music> output = new ArrayList<>();
		output.add(new Music("Single", "Little More Love", "AJ Tracey", 2021));
		
		Mockito.when(this.repo.findAll()).thenReturn(output);
		
		assertEquals(output, this.service.getAll());
		
		Mockito.verify(this.repo, Mockito.times(1)).findAll();
	}
	
	@Test
	public void getOneTest() {
		Optional<Music> OptionalOutput = Optional.of(new Music(1L, "Single", "Little More Love", "AJ Tracey", 2021));
		Music output = new Music(1L, "Single", "Little More Love", "AJ Tracey", 2021);
		
		Mockito.when(this.repo.findById(1L)).thenReturn(OptionalOutput);
		
		assertTrue(this.service.getOne(1L).equals(output));
		
		Mockito.verify(this.repo, Mockito.times(1)).findById(1L);
	}
	
	@Test
	public void updateTest() {
		Music input = new Music("Album", "Flu Game", "AJ Tracey", 2021);
		Optional<Music> existing = Optional.of(new Music(1L, "Single", "Little More Love", "AJ Tracey", 2021));
		Music output = new Music(1L, "Album", "Flu Game", "AJ Tracey", 2021);
		
		Mockito.when(this.repo.findById(1L)).thenReturn(existing);
		Mockito.when(this.repo.saveAndFlush(output)).thenReturn(output);
		
		assertEquals(output, this.service.update(1L, input));
		
		Mockito.verify(this.repo, Mockito.times(1)).findById(1L);
		Mockito.verify(this.repo, Mockito.times(1)).saveAndFlush(output);
	}
	
	@Test
	public void deleteTrueTest() {
		Mockito.when(this.repo.existsById(1L)).thenReturn(false);
		
		assertTrue(this.service.delete(1L));
		
		Mockito.verify(this.repo, Mockito.times(1)).deleteById(1L);
		Mockito.verify(this.repo, Mockito.times(1)).existsById(1L);
	}
	
	@Test
	public void deleteFalseTest() {
		Mockito.when(this.repo.existsById(1L)).thenReturn(true);
		
		assertFalse(this.service.delete(1L));
		
		Mockito.verify(this.repo, Mockito.times(1)).deleteById(1L);
		Mockito.verify(this.repo, Mockito.times(1)).existsById(1L);
	}
}
