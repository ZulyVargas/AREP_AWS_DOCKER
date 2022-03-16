package edu.escuelaing.arep.database;


import com.mongodb.MongoClientURI;
import com.mongodb.ServerAddress;
import com.mongodb.MongoCredential;
import com.mongodb.MongoClientOptions;
import com.mongodb.client.MongoClient;


/**
 * Represents a message that will be stored in the database.
 * @author Zuly Vargas.
 */

public class ConnectionMongoDB {
    MongoClientURI uri;
    MongoClient mongoClient;




    public ConnectionMongoDB(){
        //https://programmerclick.com/article/8253664516/

        uri = new MongoClientURI("mongodb+srv://admin:admin@cluster0.ko7ci.mongodb.net/messages?retryWrites=true&w=majority");

    }


}