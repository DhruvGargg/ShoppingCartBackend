package com.app.shoppingcartbackend.service.user;

import com.app.shoppingcartbackend.model.User;
import com.app.shoppingcartbackend.request.CreateUserRequest;
import com.app.shoppingcartbackend.request.UserUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements UserServiceInterface {

    @Override
    public User getUserById(Long userId) {
        return null;
    }

    @Override
    public User createUser(CreateUserRequest request) {
        return null;
    }

    @Override
    public User updateUser(UserUpdateRequest request, Long userId) {
        return null;
    }

    @Override
    public void deleteUser(Long userId) {

    }
}
