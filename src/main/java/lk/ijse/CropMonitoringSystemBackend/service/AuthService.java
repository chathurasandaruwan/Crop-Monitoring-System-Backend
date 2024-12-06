package lk.ijse.CropMonitoringSystemBackend.service;


import lk.ijse.CropMonitoringSystemBackend.dto.impl.UserDTO;
import lk.ijse.CropMonitoringSystemBackend.secure.JWTAuthResponse;
import lk.ijse.CropMonitoringSystemBackend.secure.SignIn;

public interface AuthService {
    JWTAuthResponse signIn(SignIn signIn);

    JWTAuthResponse signUp(UserDTO userDTO);

    JWTAuthResponse refreshToken(String accessToken);
}
