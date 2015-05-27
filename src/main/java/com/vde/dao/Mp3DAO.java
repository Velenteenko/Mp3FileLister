package com.vde.dao;

import java.util.List;

import com.vde.object.Mp3;

public interface Mp3DAO {

	void insert(Mp3 mp3);
	
	void insert(List<Mp3> mp3s);

	void delete(Mp3 mp3);
	
	void delete(int id);

	Mp3 getMp3ById(int mp3Id);

	List<Mp3> getMp3ListByName(String name);

	List<Mp3> getMp3ListByAuthor(String author);

}
