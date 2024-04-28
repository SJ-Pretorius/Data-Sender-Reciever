package com.adddata;

import com.google.gson.Gson;
import spark.Request;
import spark.Response;
import spark.Route;
import static spark.Spark.*;

public class Main {
    public static void main(String[] args) {
        // Set up port and routes
        port(8080); // Choose a port number, 80 might require special permissions
        post("/api/endpoint", readData); // Change to 'post' method since client is sending a POST request
    }

    // Route handler to read data
    private static final Route readData = (Request request, Response response) -> {
        // Retrieve data from the request body
        String jsonData = request.body();

        // Process the data (e.g., print it)
        int length = jsonData.length();
        for (int i = -15; i < length; i++) {
            System.out.print("-");
        }
        System.out.println("\nReceived data: " + jsonData);
        for (int i = -15; i < length; i++) {
            System.out.print("-");
        }
        System.out.print("\n");

        // Respond with a message
        response.type("application/json");
        return new Gson().toJson("Data received successfully");
    };
}
