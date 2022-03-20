package edu.escuelaing.arep;

import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.exceptions.UnirestException;

public class LBRoundRobin {
    private int currentServer = 0;
    private String[] ports2 = {":35001",":35002",":35003"};
    private String[] ports = {":4561",":4561",":4561"};
    private String url = "http://localhost";

    /**
     * Performs the get request to the server connected to the database.
     * @return body of the responde.
     * @throws UnirestException - Some unirest error in the request.
     */
    public String getMessages() throws UnirestException {
        HttpResponse<String> apiResponse = Unirest.get(url+ports[currentServer]+"/messages").asString();
        System.out.println("peticion GET desde--------------- "+url+ports[currentServer]);
        return apiResponse.getBody();
    }
    /**
     * Performs the post request to the server connected to the database.
     * @return body of the responde.
     * @throws UnirestException - Some unirest error in the request.
     */
    public String postMessage(String message) throws UnirestException {
        HttpResponse<String> apiResponse = Unirest.post(url+ports[currentServer]+"/messages")
                .body(message)
                .asString();
        System.out.println("peticion POST desde--------------- "+url+ports[currentServer]);
        return apiResponse.getBody();
    }
    /**
     * Switches between servers.
     */
    public void changeNumberServer(){
        currentServer=(currentServer + 1) % ports.length;
    }
}