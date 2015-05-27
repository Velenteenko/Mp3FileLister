package com.vde.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.vde.dao.impl.SQLiteDAOImpl;
import com.vde.object.Mp3;

public class Start {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Create new Mp3 Object");
		System.out.println("Auhor - Dr. Alban; Name - Its My Life");
		Mp3 mp3 = new Mp3();
		mp3.setId(11);
		mp3.setAuthor("Dr. Alban");
		mp3.setName("");
		
		System.out.println("Read file context.xml");
		ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
		System.out.println("Getting bean");
		SQLiteDAOImpl sqLiteDAOImpl = (SQLiteDAOImpl) context.getBean("sqliteDao");
//		System.out.println("Insert new object");
//		sqLiteDAOImpl.insert(mp3);
//		System.out.println("Insert new object success!!!");
		
//		System.out.println("Delete object");
//		sqLiteDAOImpl.delete(mp3);
//		System.out.println("Delete object with success!!!");
		
		System.out.println("Getting mp3 object by ID");
		Mp3 mp3Id = sqLiteDAOImpl.getMp3ById(5);
		System.out.println(mp3Id.toString());
		
	}

}
