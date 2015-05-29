package com.vde.main;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.vde.dao.impl.Mp3DaoImpl;
import com.vde.object.Artist;
import com.vde.object.Mp3;

public class Start {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Create new Author Object");
		
		Artist author = new Artist();
		author.setName("Skillet");
		
		Mp3 mp31 = new Mp3();
		mp31.setName("Hero (Radio edit)");
		mp31.setAuthor(author);
		

		System.out.println("Read file context.xml");
		ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
		System.out.println("Getting bean");
		Mp3DaoImpl sqLiteDAOImpl = (Mp3DaoImpl) context.getBean("sqliteDaoNew");
		
		sqLiteDAOImpl.insert(mp31);
		System.out.println("New composition inserted!!!");

//		System.out.println("Insert new song");
//		Mp3 mp3 = new Mp3();
//		mp3.setAuthor("1");
//		mp3.setName("1");
//		Mp3 mp3_toSet1 = new Mp3();
//		mp3_toSet1.setAuthor("Sum 41");
//		mp3_toSet1.setName("Undeclared Hero");
//		Mp3 mp3_toSet2 = new Mp3();
//		mp3_toSet2.setAuthor("Three days Grace");
//		mp3_toSet2.setName("Pain");
//		Mp3 mp3_toSet3 = new Mp3();
//		mp3_toSet3.setAuthor("Disturbed");
//		mp3_toSet3.setName("Stricked");
//		// int inn = sqLiteDAOImpl.insert(mp3);
//
//		System.out.println("Insert new object");
//
//		System.out.println("Insert new object success with id=" + sqLiteDAOImpl.insert(mp3));
//
//		System.out.println("Insert many objects");
//		List<Mp3> list = new ArrayList<Mp3>();
//		list.add(mp3_toSet1);
//		list.add(mp3_toSet2);
//		list.add(mp3_toSet3);
//		sqLiteDAOImpl.insertListBatch(list);
//		System.out.println("Inserted");
		//
		// // System.out.println("Delete object");
		// // sqLiteDAOImpl.delete(mp3);
		// // System.out.println("Delete object with success!!!");
		//
		// System.out.println("Getting mp3 object by ID");
		// Mp3 mp3Id = sqLiteDAOImpl.getMp3ById(5);
		// System.out.println(mp3Id.toString());
		//
		// System.out.println("Getting mp3 objects by name");
		// List<Mp3> mp3List = new
		// ArrayList<Mp3>(sqLiteDAOImpl.getMp3ListByAuthor("Skillet"));
		//
		// for (Mp3 mp32 : mp3List) {
		// System.out.println(mp32.toString());
		// }
		//
		// System.out.println("Get statistic");
		// Map<String, Integer> statmap = new TreeMap<String,
		// Integer>(sqLiteDAOImpl.getStatistic());
		//
		// for (Entry<String, Integer> mapStat : statmap.entrySet()) {
		// String author = mapStat.getKey();
		// int count = mapStat.getValue();
		// System.out.println("Author: " + author + " count of songs: " +
		// count);
		// }
		//
		// System.out.println("Count all songs: " +
		// sqLiteDAOImpl.getMp3Count());

	}

}
