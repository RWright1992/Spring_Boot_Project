package com.qa.musichwa.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Music {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column (nullable = false)
    private String type;

    @Column (nullable = false)
    private String name;

    @Column (nullable = false)
    private String artist;

    @Column (nullable = false)
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
