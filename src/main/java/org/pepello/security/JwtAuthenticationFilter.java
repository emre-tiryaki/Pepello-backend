package org.pepello.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.pepello.service.IAuthenticationService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final IAuthenticationService authenticationService;


    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String path = request.getServletPath();
        boolean shouldNotFilter = path.startsWith("/auth");
        System.out.println("Filter check - Path: " + path + ", Should NOT filter: " + shouldNotFilter);
        return shouldNotFilter;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        try {
            String token = extractTokent(request);

            System.out.println("JWT Filter - extracted token: " + (token == null ? "<null>" : token));

            if (token != null) {
                UserDetails userDetails = authenticationService.validateToken(token);

                if (userDetails == null) {
                    System.out.println("JWT Filter - token validated but no user found");
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid token: user not found");
                    return;
                }

                System.out.println("JWT Filter - authenticated user: " + userDetails.getUsername() + ", authorities: " + userDetails.getAuthorities());

                UsernamePasswordAuthenticationToken authenticationFilter = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );

                SecurityContextHolder.getContext().setAuthentication(authenticationFilter);

                if (userDetails instanceof CustomUserDetails) {
                    request.setAttribute("userId", ((CustomUserDetails) userDetails).getId());
                }
            }
        } catch (Exception e) {
            System.out.println("JWT Filter - token validation failed: " + e.getClass().getSimpleName() + " -> " + e.getMessage());
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid or expired token");
            return;
        }

        filterChain.doFilter(request, response);
    }

    private String extractTokent(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}
