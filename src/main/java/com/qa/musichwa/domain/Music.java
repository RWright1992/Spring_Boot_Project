package com.qa.musichwa.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Music {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull(message = "Type can not be null")
    private String type;

    @NotNull(message = "Name can not be null")
    private String name;

    @NotNull(message = "Artist can not be null")
    private String artist;

    @NotNull(message = "Year can not be null")
    private long year;

    // Default constructor
    public Music() {
    	super();
    }
    
	public Music(String type, String name, String artist, long year) {
		super();
		this.type = type;
		this.name = name;
		this.artist = artist;
		this.year = year;
	}
	
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public long getYear() {
		return year;
	}

	public void setYear(long year) {
		this.year = year;
	}
}
