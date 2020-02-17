package com.mps.insight.dao;

import java.time.LocalDate;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class MongoDAO {
	
	MongoClient mongoClient;
	MongoDatabase db;
	String databaseName="";
	public MongoDAO(String ip, String dbname) {
		this.mongoClient = new MongoClient(ip);
		this.databaseName = dbname;
	}
	public MongoDAO() {
		this.mongoClient=new MongoClient("10.31.1.109:27017");
	}
	
	public MongoDatabase getDatabase(String dbname){
		db=mongoClient.getDatabase(dbname);
		return db;
	}
	
	public MongoDatabase getDatabase(){
		db=mongoClient.getDatabase("team_kumar_log");
		return db;
	}
	
	public void close(){
		mongoClient.close();
	}
	public String insertData(Document doc){
		LocalDate localDate = LocalDate.now();
		String date="log_"+localDate.toString().replace("-", "");
		try{
		getDatabase();
		MongoCollection<Document> coll=db.getCollection(date);
		coll.insertOne(doc);
		return "success";
		}
		catch(Exception e){
			return "error";
		}
	}
	
}
