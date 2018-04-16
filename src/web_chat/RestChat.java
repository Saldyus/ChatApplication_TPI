/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web_chat;

import java.io.IOException;
import java.net.URI;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

/**
 *
 * @author Dinaro Salvatore
 * @version 1.0
 */
public class RestChat {

    private static final URI BASE_URI = URI.create("http://localhost:8080/chat/");
    public static final String ROOT_PATH = "";

    private HttpServer server;
    private ResourceConfig resources;

    public RestChat() {
        initLogger();
        initResources();
        initServer();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        RestChat server = new RestChat();
        server.start();
    }

    public void start() {
        try {
            server.start();
            System.out.println(String.format("Application started.\nTry out %s%s\nStop the application using CTRL+C",
                    BASE_URI, ROOT_PATH));
            Thread.currentThread().join();
        } catch (IOException | InterruptedException ex) {
            Logger.getLogger(RestChat.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void initLogger() {
        Logger l = Logger.getLogger("org.glassfish.grizzly.http.server.HttpHandler");
        l.setLevel(Level.FINE);
        l.setUseParentHandlers(false);
        ConsoleHandler ch = new ConsoleHandler();
        ch.setLevel(Level.ALL);
        l.addHandler(ch);
    }
    
    private void initServer() {
        server = GrizzlyHttpServerFactory.createHttpServer(BASE_URI, resources, false);

    }

    private void initResources() {
        resources = new ResourceConfig();
        resources.register(ChatService.class);
    }
    
}
