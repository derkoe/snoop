package com.porscheinformatik.koc.snoop.snoop;

import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

@SpringBootApplication
@RestController
public class SnoopApplication {

    public static void main(String[] args) {
        SpringApplication.run(SnoopApplication.class, args);
    }

    @GetMapping("/")
    public Map<String, Object> handle(HttpServletRequest request) {
        Map<String, Object> headers = new HashMap<>();
        for (Enumeration<String> headerNames = request.getHeaderNames(); headerNames.hasMoreElements();) {
            String headerName = headerNames.nextElement();
            headers.put(headerName, Collections.list(request.getHeaders(headerName)));
        }

        return Map.of("headers", headers, "remoteAddr", request.getRemoteAddr(), "remoteHost", request.getRemoteHost(),
                "remotePort", request.getRemotePort(), "serverName", request.getServerName(),
                "serverPort", request.getServerPort(), "scheme", request.getScheme());
    }
}
