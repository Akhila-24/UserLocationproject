package com.userLocation.userLocationApplication.Service;

import com.userLocation.userLocationApplication.Entity.UserLocation;
import com.userLocation.userLocationApplication.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
   public UserRepository userRepository;

    public UserLocation createUser(UserLocation userLocation) {
        return userRepository.save(userLocation);
    }

    public UserLocation updateUser(Long id, UserLocation userLocation) {
        UserLocation existingUserLocation = userRepository.findById(id).orElse(null);
        if (existingUserLocation != null) {
            existingUserLocation.setName(userLocation.getName());
            existingUserLocation.setLatitude(userLocation.getLatitude());
            existingUserLocation.setLongitude(userLocation.getLongitude());
            return userRepository.save(existingUserLocation);
        }
        return null;
    }

    public List<UserLocation> getNearestUsers(Integer n) {
        return userRepository.findAll().stream().sorted(Comparator.comparingDouble(user -> calculateDistance(user.getLatitude(), user.getLongitude()))).limit(n).collect(Collectors.toList());
    }

    private Double calculateDistance(Double latitude, Double longitude) {
        Double distance = Math.sqrt(Math.pow(latitude, 2) + Math.pow(longitude, 2));
        return distance;
    }
}
