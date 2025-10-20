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
            <p>This is Load Dispatch and Handling service!</p>
            <h2>Available Endpoints:</h2>
            <ul>
                <li><strong>GET: /api/loads</strong><br>
                    Retrieve all loads.<br>
                    <em>No body required. Requires ADMIN role.</em></li>
                <li><strong>POST: /api/loads</strong><br>
                    Create a new load.<br>
                    <em>Body: string (description, e.g., "Cargo from NYC to LA"). Requires ADMIN role.</em></li>
                <li><strong>POST: /api/loads/{id}/dispatch</strong><br>
                    Dispatch a load to an available driver.<br>
                    <em>No body required. Path param: id (Long). Requires ADMIN role.</em></li>
                <li><strong>PUT: /api/loads/{id}/status</strong><br>
                    Update load status (e.g., to DELIVERED).<br>
                    <em>Body: string (LoadStatus: PENDING, DISPATCHED, DELIVERED).<br>
                    Path param: id (Long). Requires ADMIN or DRIVER role.</em></li>
                <li><strong>GET: /api/drivers</strong><br>
                    Retrieve all drivers.<br>
                    <em>No body required. Requires ADMIN or DRIVER role.</em></li>
                <li><strong>GET: /api/drivers/available</strong><br>
                    Retrieve available drivers only.<br>
                    <em>No body required. Requires ADMIN or DRIVER role.</em></li>
                <li><strong>POST: /api/drivers</strong><br>
                    Create a new driver.<br>
                    <em>Body: string (name, e.g., "Alice Johnson"). Requires ADMIN role.</em></li>
                <li><strong>PUT: /api/drivers/{id}/availability</strong><br>
                    Update driver availability.<br>
                    <em>Body: boolean (true/false).<br>
                    Path param: id (Long). Requires ADMIN or DRIVER role.</em></li>
            </ul>
            <h2>Authentication with JWT Token</h2>
            <p>Protected endpoints require a JWT token. example:</p>
            <ol>
                <li><strong>Get Token from Auth Service:</strong><br>
                    POST <code>http://localhost:8384/api/auth/login</code><br>
                    Body: <code>{"username": "your-username", "password": "your-password"}</code><br>
                    Response: <code>{"token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."}</code></li>
                <li><strong>Use Token in Requests:</strong><br>
                    Add header: <code>Authorization: Bearer &lt;your-token&gt;</code><br>
                    (e.g., in Postman: Headers tab > Key: Authorization, Value: Bearer eyJ...)</li>
                <li><strong>Roles:</strong><br>
                    - ADMIN: Full access (create/dispatch).<br>
                    - DRIVER: Update status/availability.</li>
            </ol>
            <p><em>All endpoints return JSON.</em></p>
        """;
    }
}
