package com.chatterly.auth_service.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chatterly.auth_service.entity.Device;
import com.chatterly.auth_service.entity.User;

public interface DeviceRepository extends JpaRepository<Device, String> {

    Optional<Device> findByUserAndIpAddressAndDeviceType(User user, String ipAddress, String deviceType);

}
