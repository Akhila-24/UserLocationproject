package com.userLocation.userLocationApplication.Repository;

import com.userLocation.userLocationApplication.Entity.UserLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface UserRepository extends JpaRepository<UserLocation, Long> {
}
