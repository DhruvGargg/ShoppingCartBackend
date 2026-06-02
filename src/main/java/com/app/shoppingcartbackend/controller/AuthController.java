package com.app.shoppingcartbackend.controller;

import com.app.shoppingcartbackend.request.LoginRequest;
import com.app.shoppingcartbackend.response.APIResponse;
import com.app.shoppingcartbackend.response.JwtResponse;
import com.app.shoppingcartbackend.security.jwt.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${api.prefix}/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    @PostMapping("/login")
    public ResponseEntity<APIResponse> login(@RequestBody LoginRequest request) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
            );
            String jwt = jwtUtils.generateTokenForUser(authentication);
            com.app.shoppingcartbackend.security.user.ShopUserDetails userDetails = 
                    (com.app.shoppingcartbackend.security.user.ShopUserDetails) authentication.getPrincipal();
            JwtResponse jwtResponse = new JwtResponse(userDetails.getId(), jwt);
            return ResponseEntity.ok(new APIResponse("Login Success", jwtResponse));
        } catch (AuthenticationException e) {
            return ResponseEntity.status(org.springframework.http.HttpStatus.UNAUTHORIZED)
                    .body(new APIResponse("Invalid Email or Password", e.getMessage()));
        }
    }
}
