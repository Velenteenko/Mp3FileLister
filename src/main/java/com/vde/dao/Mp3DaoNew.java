package com.vde.dao;

import java.util.List;
import java.util.Map;

import com.vde.object.Mp3;

public interface Mp3DaoNew {
	int insert(Mp3 mp3);

	int insertList(List<Mp3> mp3List);

	void delete(Mp3 mp3);

	void delete(int id);

	Mp3 getMP3ByID(int id);

	List<Mp3> getMP3ListByName(String name);

	List<Mp3> getMP3ListByAuthor(String author);

	int getMP3Count();

	Map<String, Integer> getStat();
}
