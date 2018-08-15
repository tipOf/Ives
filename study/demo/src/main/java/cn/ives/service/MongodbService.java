package cn.ives.service;

import com.mongodb.DB;
import com.mongodb.DBCursor;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSBuckets;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSInputFile;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MongodbService {
   private MongoDatabase getMongoClient() {
      MongoClient mongoClient = new MongoClient("localhost", 27017);

      return mongoClient.getDatabase("ives");
   }

   private MongoCollection<Document> getCollection(String name) {
      MongoDatabase client = getMongoClient();
      MongoCollection<Document> attachment = client.getCollection(name);
      System.out.println("集合 attachment 选择成功");
      return  attachment;
   }

   private void insertDocument() {
      Document document = new Document("title", "MongoDB").
      append("description", "database").
      append("likes", 100).
      append("by", "Fly");
      MongoCollection<Document> collection = getCollection("attachment");
      List<Document> documents = new ArrayList<Document>();
      documents.add(document);
      collection.insertMany(documents);
   }

   private void getCollentionContent() {
      MongoCollection<Document> collection = getCollection("attachment");
      FindIterable<Document> documents = collection.find();
      MongoCursor<Document> iterator = documents.iterator();

      while(iterator.hasNext()) {
         System.out.println(iterator.next());
      }
   }

   private GridFSBucket getGridFSBucket() {
      return GridFSBuckets.create(getMongoClient());
   }

   private String uploadFile(String filePath) {
      String objId = null;
      File file = new File(filePath);
      String name = file.getName();

      try {
         FileInputStream fileInputStream = new FileInputStream(file);
         GridFSBucket fsBucket = getGridFSBucket();
         objId = fsBucket.uploadFromStream(name, fileInputStream).toString();
      }
      catch(FileNotFoundException e) {
         e.printStackTrace();
      }

      return objId;
   }

   private void delFile(String objId) {
      ObjectId id = new ObjectId(objId);
      GridFSBucket fsBucket = getGridFSBucket();
      fsBucket.delete(id);
   }

   private void uploadFile() {
      long startTime = System.currentTimeMillis();
      Mongo db = new Mongo("127.0.0.1", 27017);
      DB ives = db.getDB("ives");
      File file = new File("C:\\Users\\Xu\\Desktop\\tempFile\\license.dat");
      GridFS gridFS = new GridFS(ives);

      try {
         GridFSInputFile inputFile = gridFS.createFile(file);
         inputFile.save();
      }
      catch(IOException e) {
         e.printStackTrace();
      }

      DBCursor cursor = gridFS.getFileList();
      while(cursor.hasNext()) {
         System.out.println(cursor.next());
      }

      db.close();
      long endTime = System.currentTimeMillis();
      System.out.println((startTime - endTime) / 10000000);
   }

   public static void main(String[] args) {
      MongodbService service = new MongodbService();
      service.uploadFile("C:\\Users\\Xu\\Desktop\\tempFile\\license.dat");
   }
}
