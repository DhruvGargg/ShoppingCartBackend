package com.app.shoppingcartbackend.service.user;

import com.app.shoppingcartbackend.model.User;
import com.app.shoppingcartbackend.request.CreateUserRequest;
import com.app.shoppingcartbackend.request.UserUpdateRequest;

public interface UserServiceInterface {

    User getUserById(Long userId);
    User createUser(CreateUserRequest request);
    User updateUser(UserUpdateRequest request, Long userId);
    void deleteUser(Long userId);
}
