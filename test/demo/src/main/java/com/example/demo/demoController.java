package com.example.demo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class demoController {
    @GetMapping("/")
    public String home(HttpServletRequest request) {
        System.out.println("Remote-User: " + request.getHeader("Remote-User"));
        System.out.println("Remote-Groups: " + request.getHeader("Remote-Groups"));
        return "Hello";
    }

    @GetMapping("/protected")
    public ResponseEntity<String> protectedResource(
            @RequestHeader(value = "Remote-User", required = false) String remoteUser,
            @RequestHeader(value = "Remote-Email", required = false) String remoteEmail,
            @RequestHeader(value = "Remote-Groups", required = false) String remoteGroups
    ) {
        if (remoteUser == null || remoteUser.isEmpty()) {
            // No authenticated session
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized - Invalid or expired session.");
        }

        // Valid session
        String response = String.format("Welcome %s (%s), Groups: %s", remoteUser, remoteEmail, remoteGroups);
        return ResponseEntity.ok(response);
    }
}

