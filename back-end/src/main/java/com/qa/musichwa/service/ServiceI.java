package com.qa.musichwa.service;

import java.util.List;

public interface ServiceI<T> {
	
	T create(T t);
	
	List<T> getAll();
	
	T getOne(long id);
	
	T update(long id, T t);
	
	boolean delete(long id);
	
}
