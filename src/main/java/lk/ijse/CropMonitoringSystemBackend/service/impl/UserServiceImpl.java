package lk.ijse.CropMonitoringSystemBackend.service.impl;

import jakarta.transaction.Transactional;

import lk.ijse.CropMonitoringSystemBackend.dao.UserDAO;
import lk.ijse.CropMonitoringSystemBackend.dto.impl.UserDTO;
import lk.ijse.CropMonitoringSystemBackend.exeption.UserNotFoundException;
import lk.ijse.CropMonitoringSystemBackend.service.UserService;
import lk.ijse.CropMonitoringSystemBackend.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;


@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDAO userDAO;
    @Override
    public UserDetailsService userDetailsService() {
        return username -> userDAO.findById(username)
                .orElseThrow( () -> new UserNotFoundException("User Not Found"));
    }
}
