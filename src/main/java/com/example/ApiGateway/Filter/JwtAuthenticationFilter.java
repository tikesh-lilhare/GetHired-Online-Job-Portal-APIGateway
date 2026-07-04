package com.example.ApiGateway.Filter;

import com.example.ApiGateway.Util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter implements GlobalFilter {
    private final JwtUtil jwtUtil;

    @Override
    public Mono<Void> filter(
            ServerWebExchange exchange,
            GatewayFilterChain chain) {

        String path =
                exchange.getRequest()
                        .getURI()
                        .getPath();

        System.out.println("PATH = " + path);

        if(path.contains("/users/login")
                || path.contains("/users/register")) {

            return chain.filter(exchange);
        }

        String authHeader =
                exchange.getRequest()
                        .getHeaders()
                        .getFirst(
                                HttpHeaders.AUTHORIZATION);

        if(authHeader == null
                || !authHeader.startsWith(
                "Bearer ")) {

            exchange.getResponse()
                    .setStatusCode(
                            HttpStatus.UNAUTHORIZED);

            return exchange.getResponse()
                    .setComplete();
        }

        String token =
                authHeader.substring(7);
        String role =
                jwtUtil.extractRole(token);

        String method =
                exchange.getRequest()
                        .getMethod()
                        .name();

        if(path.startsWith("/jobs")
                && (method.equals("POST")
                || method.equals("PUT")
                || method.equals("DELETE"))) {

            if(!role.equals("RECRUITER")
                    && !role.equals("ADMIN")) {

                exchange.getResponse()
                        .setStatusCode(HttpStatus.FORBIDDEN);

                return exchange.getResponse()
                        .setComplete();
            }
        }

        if(path.startsWith("/applications")
                && method.equals("POST")) {

            if(!role.equals("JOB_SEEKER")
                    && !role.equals("ADMIN")) {

                exchange.getResponse()
                        .setStatusCode(HttpStatus.FORBIDDEN);

                return exchange.getResponse()
                        .setComplete();
            }
        }

        if(!jwtUtil.validateToken(token)) {

            exchange.getResponse()
                    .setStatusCode(
                            HttpStatus.UNAUTHORIZED);

            return exchange.getResponse()
                    .setComplete();
        }

        return chain.filter(exchange);
    }

}
