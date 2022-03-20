package edu.escuelaing.arep.database;


import com.mongodb.ConnectionString;
import com.mongodb.MongoClientURI;
import com.mongodb.client.*;
import com.mongodb.client.MongoCollection;
import edu.escuelaing.arep.entry.Message;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


/**
 * Represents a message that will be stored in the database.
 * @author Zuly Vargas.
 */

public class ConnectionMongoDB {
    String uri;
    MongoClient mongoClient;


    /**
     * Create the connection to the database.
     */
    public ConnectionMongoDB() throws ParseException {
        //https://programmerclick.com/article/8253664516/
        uri = "mongodb+srv://admin:admin@cluster0.ko7ci.mongodb.net/?retryWrites=true&w=majority";
        ConnectionString connection = new ConnectionString(uri);
        this.mongoClient = MongoClients.create(connection);
    }
    /**
     * Inserts a message in the database.
     * @param message - message to insert.
     */
    public void insertMessage(Message message){
        MongoDatabase mongoDatabase = mongoClient.getDatabase("lab4");
        //mongoClient = new MongoClient(uri);
        MongoDatabase db = mongoClient.getDatabase("lab4");
        MongoCollection<Document> collection = db.getCollection("messages");
        Document  document =new Document();
        document.put("content",message.getContent());
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
            if (doc.get("content") != null && doc.get("date") != null) {
                messages.add(new Message((String) doc.get("content"), (String)doc.get("date")));
            }
        }
        ArrayList<Message> messagesToShow = new ArrayList<Message>();
        int last = messages.size()-1;
        for (int count = 10 ;  count > 0 && count <= 10 && 0<= last && last< messages.size() ; count -- ){
                messagesToShow.add(messages.get(last));
                last = last -1;
        }
        for (Message m : messagesToShow)
            System.out.println("----Mensaje"+ m.getDate() + m.getContent());
        return messagesToShow;
    }
}