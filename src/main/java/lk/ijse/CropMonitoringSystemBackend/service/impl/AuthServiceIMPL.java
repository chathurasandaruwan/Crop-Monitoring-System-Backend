package lk.ijse.CropMonitoringSystemBackend.service.impl;

import lk.ijse.CropMonitoringSystemBackend.dao.UserDAO;
import lk.ijse.CropMonitoringSystemBackend.dto.impl.UserDTO;
import lk.ijse.CropMonitoringSystemBackend.entity.impl.UserEntity;
import lk.ijse.CropMonitoringSystemBackend.exeption.UserNotFoundException;
import lk.ijse.CropMonitoringSystemBackend.secure.JWTAuthResponse;
import lk.ijse.CropMonitoringSystemBackend.secure.SignIn;
import lk.ijse.CropMonitoringSystemBackend.service.AuthService;
import lk.ijse.CropMonitoringSystemBackend.service.JWTService;
import lk.ijse.CropMonitoringSystemBackend.util.Mapping;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceIMPL implements AuthService {
    private final UserDAO userDAO;
    private final Mapping mapping;
    private final JWTService jwtService;
    private final AuthenticationManager authenticationManager;
    @Override
    public JWTAuthResponse signIn(SignIn signIn) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signIn.getEmail(), signIn.getPassword()));
        UserEntity user = userDAO.findById(signIn.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));
        var generateToken = jwtService.generateToken(user);
        return JWTAuthResponse.builder().token(generateToken).build();
    }

    @Override
    public JWTAuthResponse signUp(UserDTO userDTO) {
//        saveUser
        UserEntity savedUser = userDAO.save(mapping.toUserEntity(userDTO));
//        generate token and return
        var generateToken = jwtService.generateToken(savedUser);
        return JWTAuthResponse.builder().token(generateToken).build();
    }

    @Override
    public JWTAuthResponse refreshToken(String accessToken) {
//        extractUsername
        var username = jwtService.extractUsername(accessToken);
//        is exists the User
        UserEntity user = userDAO.findById(username)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        var generateToken = jwtService.refreshToken(user);
        return JWTAuthResponse.builder().token(generateToken).build();
    }
}
