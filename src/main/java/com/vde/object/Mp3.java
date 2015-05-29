package com.vde.object;

public class Mp3 {

	private int trackId;
	private Artist author;
	private String name;
	
	

	public int getTrackId() {
		return trackId;
	}

	public void setTrackId(int trackId) {
		this.trackId = trackId;
	}

	public Artist getAuthor() {
		return author;
	}

	public void setAuthor(Artist author) {
		this.author = author;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Mp3 [id=" + trackId + ", author=" + author + ", name=" + name + "]";
	}

	
}
