package com.chatterly.auth_service.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.chatterly.auth_service.dto.DeviceDTO;
import com.chatterly.auth_service.repo.DeviceRepository;

@Service
public class UserService {

    private final DeviceRepository deviceRepository;

    public UserService(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    public void logout(String userId, String sessionId) {
        deviceRepository.deleteByUserIdAndSessionId(userId, sessionId);
    }

    public void logoutAllDevices(String email) {
        deviceRepository.deleteAllByUsername(email);
    }

    public List<DeviceDTO> getAllDevices(String email) {
        return deviceRepository.findAllByUsername(email);
    }

}
