package com.chatterly.auth_service.service;

import java.util.List;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.chatterly.auth_service.dto.DeviceDTO;
import com.chatterly.auth_service.repo.DeviceRepository;

@Service
public class UserService {

    private final DeviceRepository deviceRepository;

    public UserService(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    @CacheEvict(value = "devices", key = "#userId")
    public void logout(String userId, String sessionId) {
        deviceRepository.deleteByUserIdAndSessionId(userId, sessionId);
    }

    @CacheEvict(value = "devices", key = "#email")
    public void logoutAllDevices(String email) {
        deviceRepository.deleteAllByUsername(email);
    }

    @Cacheable(value = "devices", key = "#email")
    public List<DeviceDTO> getAllDevices(String email) {
        return deviceRepository.findAllByUsername(email);
    }

}
