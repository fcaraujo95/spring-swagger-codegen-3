package com.fcaraujo.spring_swagger_codegen_3.controller;

import com.devertelo.springswaggercodegen3.api.UsersApi;
import com.devertelo.springswaggercodegen3.model.UserRequest;
import com.devertelo.springswaggercodegen3.model.UserResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
public class UsersController implements UsersApi {

    private Map<String, UserResponse> users = new HashMap<>();

    @Override
    public ResponseEntity<UserResponse> createUser(UserRequest userRequest) {
        var user = toUserResponse(userRequest);
        users.put(user.getId(), user);
        return ResponseEntity.ok(user);
    }

    @Override
    public ResponseEntity<Void> deleteUser(String id) {
        return UsersApi.super.deleteUser(id);
    }

    @Override
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        return UsersApi.super.getAllUsers();
    }

    @Override
    public ResponseEntity<UserResponse> getUserById(String id) {
        return UsersApi.super.getUserById(id);
    }

    @Override
    public ResponseEntity<UserResponse> updateUser(String id, UserRequest userRequest) {
        return UsersApi.super.updateUser(id, userRequest);
    }

    private static UserResponse toUserResponse(UserRequest userRequest) {
        return new UserResponse()
                .id(UUID.randomUUID().toString())
                .createdAt(OffsetDateTime.now())
                .name(userRequest.getName())
                .username(userRequest.getUsername())
                .lastName(userRequest.getLastName())
                .email(userRequest.getEmail())
                .status(userRequest.getStatus())
                .password(userRequest.getPassword());
    }
}
