package com.app.shoppingcartbackend.service.user;

import com.app.shoppingcartbackend.dto.UserDTO;
import com.app.shoppingcartbackend.exception.AlreadyExistsException.AlreadyExistsException;
import com.app.shoppingcartbackend.exception.ResourceNotFound.ResourceNotFoundException;
import com.app.shoppingcartbackend.model.User;
import com.app.shoppingcartbackend.repository.user.UserRepository.UserRepository;
import com.app.shoppingcartbackend.request.CreateUserRequest;
import com.app.shoppingcartbackend.request.UserUpdateRequest;
import com.app.shoppingcartbackend.model.Role;
import com.app.shoppingcartbackend.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserService implements UserServiceInterface {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    @Override
    public User getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    @Override
    public User createUser(CreateUserRequest request) {
        return Optional.of(request)
                .filter(user -> !userRepository.existsByEmail(request.getEmail()))
                .map(req -> {
                    User user = new User();
                    user.setEmail(request.getEmail());
                    user.setFirstName(request.getFirstName());
                    user.setLastName(request.getLastName());
                    user.setPassword(passwordEncoder.encode(request.getPassword()));
                    Role userRole = roleRepository.findByName("ROLE_USER").orElse(null);
                    if (userRole != null) {
                        user.setRoles(Set.of(userRole));
                    }
                    return userRepository.save(user);
                })
                .orElseThrow(() -> new AlreadyExistsException("User with email " + request.getEmail() + " already exists!"));
    }

    @Override
    public User updateUser(UserUpdateRequest request, Long userId) {
        return userRepository.findById(userId)
                .map(existingUser -> {
                   existingUser.setFirstName(request.getFirstName());
                   existingUser.setLastName(request.getLastName());
                   return userRepository.save(existingUser);
                })
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.findById(userId)
                .ifPresentOrElse(userRepository ::delete, () -> {
                    throw new ResourceNotFoundException("User not found");
                });
    }

    @Override
    public UserDTO convertUserToUserDTO(User user) {
        return modelMapper.map(user, UserDTO.class);
    }
}
