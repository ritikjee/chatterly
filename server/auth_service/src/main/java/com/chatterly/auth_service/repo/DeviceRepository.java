package com.chatterly.auth_service.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.chatterly.auth_service.dto.DeviceDTO;
import com.chatterly.auth_service.entity.Device;
import com.chatterly.auth_service.entity.User;

import jakarta.transaction.Transactional;

public interface DeviceRepository extends JpaRepository<Device, String> {

    Optional<Device> findByUserAndIpAddressAndDeviceType(User user, String ipAddress, String deviceType);

    @Modifying
    @Transactional
    @Query("DELETE FROM Device d WHERE d.user.email = :username AND d.sessionId = :sessionId")
    void deleteByUserIdAndSessionId(String username, String sessionId);

    @Modifying
    @Transactional
    @Query("DELETE FROM Device d WHERE d.user.email = :email")
    void deleteAllByUsername(String email);

    @Query("SELECT new com.chatterly.auth_service.dto.DeviceDTO(d.id, d.ipAddress, d.deviceType, d.os, d.loginTime, d.deviceAgent) "
            +
            "FROM Device d WHERE d.user.email = :email")
    List<DeviceDTO> findAllByUsername(String email);

}
