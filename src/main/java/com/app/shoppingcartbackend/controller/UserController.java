package com.app.shoppingcartbackend.controller;

import com.app.shoppingcartbackend.dto.UserDTO;
import com.app.shoppingcartbackend.exception.AlreadyExistsException.AlreadyExistsException;
import com.app.shoppingcartbackend.exception.ResourceNotFound.ResourceNotFoundException;
import com.app.shoppingcartbackend.model.User;
import com.app.shoppingcartbackend.request.CreateUserRequest;
import com.app.shoppingcartbackend.request.UserUpdateRequest;
import com.app.shoppingcartbackend.response.APIResponse;
import com.app.shoppingcartbackend.service.user.UserService;
import com.app.shoppingcartbackend.service.user.UserServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("{api.prefix}/users")
public class UserController {
    private final UserServiceInterface userService;

    @GetMapping("/{userId}/user")
    public ResponseEntity<APIResponse> getUserById(@PathVariable Long userId) {
        try {
            User user = userService.getUserById(userId);
            UserDTO userDTO = userService.convertUserToUserDTO(user);
            return ResponseEntity.ok(new APIResponse("Success", userDTO));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new APIResponse(e.getMessage(), null));
        }
    }

    @PostMapping("/add")
    public ResponseEntity<APIResponse> createUser(@RequestBody CreateUserRequest createUserRequest) {
        try {
            User user = userService.createUser(createUserRequest);
            UserDTO userDTO = userService.convertUserToUserDTO(user);
            return ResponseEntity.ok(new APIResponse("User Creation Success", userDTO));
        } catch (AlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new APIResponse(e.getMessage(), null));
        }
    }

    @PutMapping("/{userId}/update")
    public ResponseEntity<APIResponse> updateUser(@RequestBody UserUpdateRequest userUpdateRequest, @PathVariable Long userId) {
        try {
            User user = userService.updateUser(userUpdateRequest, userId);
            UserDTO userDTO = userService.convertUserToUserDTO(user);
            return ResponseEntity.ok(new APIResponse("User Update Success", userDTO));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new APIResponse(e.getMessage(), null));
        }
    }

    @DeleteMapping("/{userId}/delete")
    public ResponseEntity<APIResponse> deleteUser(@PathVariable Long userId) {
        try {
            userService.deleteUser(userId);
            return ResponseEntity.ok(new APIResponse("User Delete Success", null));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new APIResponse(e.getMessage(), null));
        }
    }
}
