package edu.escuelaing.arep;

import static spark.Spark.*;
/**
 * Controller of the requests of the main page for the application
 * @author Zuly Vargas
 */
public class App {
    /**
     * Get and post request manager
     */
    public static void main( String[] args ) {
        port(getPort());
        staticFileLocation("/static");
        LBRoundRobin LBRR = new LBRoundRobin();
        get("/", (req, res) -> {
            res.redirect( "index.html");
            return null;
        });
        get("/results", (req, res) -> {
            System.out.println("Entre en result en get");
            res.status(200);
            res.type("application/json");
            String resp = LBRR.getMessages();
            LBRR.changeNumberServer();
            System.out.println("REEES " + res.status());
            return resp;
        });
        post("/results", (req, res) -> {
            System.out.println("Entre en result en post");
            res.type("application/json");
            LBRR.postMessage(req.body());
            LBRR.changeNumberServer();
            return null;
        });
    }

    static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567;
    }
}