package server;

import com.google.gson.Gson;
import handler.*;
import spark.*;
import java.util.*;

/**
 * Server class
 */
public class Server
{
    /**
     * Creates a new server
     *
     * @param args from command line
     */
    public static void main(String[] args)
    {
        new Server().run(8080);
    }

    /**
     * Runs the server
     */
    private void run(int number)
    {
        System.out.println("Initializing HTTP server...");
        Spark.port(number);

        Spark.externalStaticFileLocation("web");

        Spark.get("/", (req, res) -> {
            res.type("text/html");
            return Spark.class.getResourceAsStream("/chess/web/index.html");
        });

        System.out.println("You have accessed a server successfully! You get a cookie.");

        Spark.delete("/db", new ClearDataHandler());
        Spark.post("/user", new RegisterHandler());
        Spark.post("/session", new LoginHandler());
        Spark.delete("/session", new LogoutHandler());
        Spark.get("/game", new ListGamesHandler());
        Spark.post("/game", new CreateGameHandler());
        Spark.put("/game", new JoinGameHandler());
    }


}
/*
import com.google.gson.Gson;
import spark.*;
import java.util.*;

public class ServerExample {
    private ArrayList<String> names = new ArrayList<>();

    public static void main(String[] args) {
        new ServerExample().run();
    }

    private void run() {
        // Specify the port you want the server to listen on
        Spark.port(8080);

        // Register a directory for hosting static files
        Spark.externalStaticFileLocation("public");

        // Register handlers for each endpoint using the method reference syntax
        Spark.post("/name/:name", this::addName);
        Spark.get("/name", this::listNames);
        Spark.delete("/name/:name", this::deleteName);
    }

    private Object addName(Request req, Response res) {
        names.add(req.params(":name"));
        return listNames(req, res);
    }

    private Object listNames(Request req, Response res) {
        res.type("application/json");
        return new Gson().toJson(Map.of("name", names));
    }

    private Object deleteName(Request req, Response res) {
        names.remove(req.params(":name"));
        return listNames(req, res);
    }
}
 */