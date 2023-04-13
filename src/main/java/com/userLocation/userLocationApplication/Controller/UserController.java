package com.userLocation.userLocationApplication.Controller;

import com.userLocation.userLocationApplication.Entity.UserLocation;
import com.userLocation.userLocationApplication.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
     UserService userService;

    @PostMapping("/create_data")
    public UserLocation createUser(@RequestBody UserLocation userLocation) {
        return userService.createUser(userLocation);
    }

    @PutMapping("/update_data/{id}")
    public UserLocation updateUser(@PathVariable Long id, @RequestBody UserLocation userLocation) {
        return userService.updateUser(id, userLocation);
    }

    @GetMapping("/get_users/{n}")
    public List<UserLocation> getNearestUsers(@PathVariable Integer n) {
        return userService.getNearestUsers(n);
    }
}

