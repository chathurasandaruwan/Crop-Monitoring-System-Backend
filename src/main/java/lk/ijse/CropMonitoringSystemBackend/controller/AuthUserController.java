package lk.ijse.CropMonitoringSystemBackend.controller;

import lk.ijse.CropMonitoringSystemBackend.dto.impl.UserDTO;
import lk.ijse.CropMonitoringSystemBackend.enums.Role;
import lk.ijse.CropMonitoringSystemBackend.exeption.DataPersistException;
import lk.ijse.CropMonitoringSystemBackend.secure.JWTAuthResponse;
import lk.ijse.CropMonitoringSystemBackend.secure.SignIn;
import lk.ijse.CropMonitoringSystemBackend.service.AuthService;
import lk.ijse.CropMonitoringSystemBackend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RequestMapping("api/v1/auth/")
@RestController
@RequiredArgsConstructor
public class AuthUserController {
    private final AuthService authService;
    private final PasswordEncoder passwordEncoder;
    @PostMapping(value = "signup", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JWTAuthResponse> saveUser(@RequestBody UserDTO userDTO) {
        try {
            UserDTO buildUserDTO = new UserDTO();
            buildUserDTO.setEmail(userDTO.getEmail());
            buildUserDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
            buildUserDTO.setRole(userDTO.getRole());
            return ResponseEntity.ok(authService.signUp(buildUserDTO));
        } catch (DataPersistException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping(value = "signin",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JWTAuthResponse> signIn(@RequestBody SignIn signIn){
        return ResponseEntity.ok(authService.signIn(signIn));
    }
    @PostMapping("refresh")
    public ResponseEntity<JWTAuthResponse> Token(@RequestParam("refreshToken") String refreshToken) {
        return ResponseEntity.ok(authService.refreshToken(refreshToken));
    }


}
