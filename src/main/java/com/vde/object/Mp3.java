package com.vde.object;

public class Mp3 {

	private int id;
	private String author;
	private String name;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
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
		return "Mp3 [id=" + id + ", author=" + author + ", name=" + name + "]";
	}

	
}
