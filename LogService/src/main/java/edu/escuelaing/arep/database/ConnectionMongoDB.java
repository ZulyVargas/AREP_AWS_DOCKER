package edu.escuelaing.arep.database;


import com.mongodb.MongoClientURI;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import edu.escuelaing.arep.entry.Message;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Date;


/**
 * Represents a message that will be stored in the database.
 * @author Zuly Vargas.
 */

public class ConnectionMongoDB {
    MongoClientURI uri;
    MongoClient mongoClient;


    /**
     * Create the connection to the database.
     */
    public ConnectionMongoDB(){
        //https://programmerclick.com/article/8253664516/

        uri = new MongoClientURI("mongodb+srv://admin:admin@cluster0.ko7ci.mongodb.net/messages?retryWrites=true&w=majority");
        mongoClient = new MongoClient(uri);
    }
    /**
     * Inserts a message in the database.
     * @param message - message to insert.
     */
    public void insertMessage(Message message){
        mongoClient = new MongoClient(uri);
        MongoDatabase db = mongoClient.getDatabase("lab4");
        MongoCollection<Document> collection = db.getCollection("messages");
        Document  document =new Document();
        document.put("info",message.getContent());
        document.put("date",message.getDate());
        collection.insertOne(document);
    }

    /**
     * Get and return last 10 messages
     * @return messagesToShow- last 10 messages.
     */
    public ArrayList<Message> getTenMessages() {
        MongoDatabase database = mongoClient.getDatabase("lab4");
        MongoCollection<Document> collection = database.getCollection("messages");
        FindIterable<Document> findIterable = collection.find();
        ArrayList<Document> documents = new ArrayList<Document>();
        ArrayList<Message> messages = new ArrayList<Message>();
        findIterable.into(documents);
        for (Document doc : documents) {
            if (doc.get("info") != null && doc.get("date") != null) {
                messages.add(new Message((String) doc.get("info"), (Date) doc.get("date")));
            }
        }
        ArrayList<Message> messagesToShow = new ArrayList<Message>();

        for (int count = 10 ;  count <= 10; count-- ){
            if (count < messages.size() )messagesToShow.add(messages.get(count));
        }
        return messagesToShow;
    }


}