package lk.ijse.CropMonitoringSystemBackend.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.ijse.CropMonitoringSystemBackend.service.JWTService;
import lk.ijse.CropMonitoringSystemBackend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@Configuration
@RequiredArgsConstructor
public class JWTConfigFilter extends OncePerRequestFilter {
    private final JWTService jwtService;
    private final UserService userService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader("Authorization");
        String UserEmail;
        String extractedToken;
        if (StringUtils.isEmpty(token) || !token.startsWith("Bearer")) {
            filterChain.doFilter(request, response);
            return;
        }
        extractedToken = token.substring(7);
        UserEmail = jwtService.extractUsername(extractedToken);
        if (!(StringUtils.isEmpty(UserEmail)) && SecurityContextHolder.getContext().getAuthentication() == null) {
            var userDetails = userService.userDetailsService().loadUserByUsername(UserEmail);
            if (jwtService.validateToken(extractedToken, userDetails)) {
                SecurityContext emptyContext = SecurityContextHolder.createEmptyContext();
                var authToken = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                emptyContext.setAuthentication(authToken);
                SecurityContextHolder.setContext(emptyContext);
            }
        }
        filterChain.doFilter(request, response);
    }
}
