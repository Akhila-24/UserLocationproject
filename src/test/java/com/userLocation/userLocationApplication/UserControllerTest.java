package com.userLocation.userLocationApplication;


import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.userLocation.userLocationApplication.Controller.UserController;
import com.userLocation.userLocationApplication.Entity.UserLocation;
import com.userLocation.userLocationApplication.Service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@WebMvcTest(UserController.class)
@AutoConfigureMockMvc

public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    UserService userService;

    @Test
    public void testCreateUser() throws Exception {
        UserLocation userLocation = new UserLocation();
        userLocation.setName("John");
        userLocation.setLatitude(37.7749);
        userLocation.setLongitude(-122.4194);

        given(userService.createUser(any(UserLocation.class))).willReturn(userLocation);

        mockMvc.perform(post("/create_data").contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(userLocation))).andExpect(status().isOk()).andExpect(jsonPath("$.name", is(userLocation.getName()))).andExpect(jsonPath("$.latitude", is(userLocation.getLatitude()))).andExpect(jsonPath("$.longitude", is(userLocation.getLongitude())));
    }

    @Test
    public void testUpdateUser() throws Exception {
        UserLocation userLocation = new UserLocation();
        userLocation.setId(1L);
        userLocation.setName("John");
        userLocation.setLatitude(37.7749);
        userLocation.setLongitude(-122.4194);

        UserLocation updatedUserLocation = new UserLocation();
        updatedUserLocation.setId(1L);
        updatedUserLocation.setName("Jane");
        updatedUserLocation.setLatitude(40.7128);
        updatedUserLocation.setLongitude(-74.0060);

        given(userService.updateUser(eq(1L), any(UserLocation.class))).willReturn(updatedUserLocation);

        mockMvc.perform(put("/update_data/1").contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(userLocation))).andExpect(status().isOk()).andExpect(jsonPath("$.name", is(updatedUserLocation.getName()))).andExpect(jsonPath("$.latitude", is(updatedUserLocation.getLatitude()))).andExpect(jsonPath("$.longitude", is(updatedUserLocation.getLongitude())));
    }

    @Test
    public void testGetNearestUsers() throws Exception {
        UserLocation user1 = new UserLocation();
        user1.setId(1L);
        user1.setName("John");
        user1.setLatitude(37.7749);
        user1.setLongitude(-122.4194);

        UserLocation user2 = new UserLocation();
        user2.setId(2L);
        user2.setName("Jane");
        user2.setLatitude(40.7128);
        user2.setLongitude(-74.0060);

        List<UserLocation> nearestUsers = Arrays.asList(user2, user1);

        given(userService.getNearestUsers(eq(2))).willReturn(nearestUsers);

        mockMvc.perform(get("/get_users/2")).andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name", is(user2.getName())))
                .andExpect(jsonPath("$[0].latitude", is(user2.getLatitude())))
                .andExpect(jsonPath("$[0].longitude", is(user2.getLongitude())))
                .andExpect(jsonPath("$[1].name", is(user1.getName())));
    }
}

