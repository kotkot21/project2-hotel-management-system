package com.example.HotelManagmentSystem.User;

import com.example.HotelManagmentSystem.User.ChangePasswordRequest;
import com.example.HotelManagmentSystem.User.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @PatchMapping("/me")
    public ResponseEntity<?> changePassword(
            @RequestBody ChangePasswordRequest request,
            Principal connectedUser
    ) {
        service.changePassword(request, connectedUser);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/me")
    public ResponseEntity<UserProfileResponse> getProfile(Principal principal) {
        UserProfileResponse profile = service.getProfile(principal);
        return ResponseEntity.ok(profile);
    }

    @PutMapping("/me")
    public ResponseEntity<UserProfileResponse> updateProfile(@RequestBody UpdateUserProfileRequest request, Principal principal) {
        UserProfileResponse updatedProfile = service.updateProfile(request, principal);
        return ResponseEntity.ok(updatedProfile);
    }
}
