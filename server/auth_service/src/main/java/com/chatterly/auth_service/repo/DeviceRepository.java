package com.chatterly.auth_service.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chatterly.auth_service.entity.Device;

public interface DeviceRepository extends JpaRepository<Device, String> {

}
