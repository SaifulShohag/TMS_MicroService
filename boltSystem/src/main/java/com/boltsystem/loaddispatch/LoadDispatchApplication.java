package com.boltsystem.loaddispatch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class LoadDispatchApplication {

    public static void main( String[] args ) {
        SpringApplication.run( LoadDispatchApplication.class, args );
    }

    @GetMapping("/")
    public String home() {
        return """
            <h1>Load Dispatch Microservice API</h1>
            <p>Welcome to the Load Dispatch and Handling service!</p>
            <h2>Available Endpoints:</h2>
            <ul>
                <li><strong>GET: /api/loads</strong><br>
                    Retrieve all loads.<br>
                    <em>No body required.</em></li>
                <li><strong>POST: /api/loads</strong><br>
                    Create a new load.<br>
                    <em>Body: string (description, e.g., "Cargo from NYC to LA")</em></li>
                <li><strong>POST: /api/loads/{id}/dispatch</strong><br>
                    Dispatch a load to an available driver.<br>
                    <em>No body required. Path param: id (Long).</em></li>
                <li><strong>PUT: /api/loads/{id}/status</strong><br>
                    Update load status (e.g., to DELIVERED).<br>
                    <em>Body: string (LoadStatus: PENDING, DISPATCHED, DELIVERED).<br>
                    Path param: id (Long).</em></li>
                <li><strong>GET: /api/drivers</strong><br>
                    Retrieve all drivers.<br>
                    <em>No body required.</em></li>
                <li><strong>GET: /api/drivers/available</strong><br>
                    Retrieve available drivers only.<br>
                    <em>No body required.</em></li>
                <li><strong>POST: /api/drivers</strong><br>
                    Create a new driver.<br>
                    <em>Body: string (name, e.g., "Alice Johnson")</em></li>
                <li><strong>PUT: /api/drivers/{id}/availability</strong><br>
                    Update driver availability.<br>
                    <em>Body: boolean (true/false).<br>
                    Path param: id (Long).</em></li>
            </ul>
            <p><em>All endpoints return JSON.</em></p>
        """;
    }
}
