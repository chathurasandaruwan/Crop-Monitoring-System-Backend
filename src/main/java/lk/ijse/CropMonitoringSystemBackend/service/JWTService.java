package lk.ijse.CropMonitoringSystemBackend.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface JWTService {
    String generateToken(UserDetails user);

    String extractUsername(String token);
    boolean validateToken(String token, UserDetails userDetails);
    String refreshToken(UserDetails userDetails);
}
