package com.example.HotelManagmentSystem.User;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/me")
    public ResponseEntity<User> getCurrentUser(@AuthenticationPrincipal UserDetails userDetails) {
        User user = userService.findByEmail(userDetails.getUsername());
        return ResponseEntity.ok(user);
    }

    //    @PutMapping("/me")
//    public ResponseEntity<User> updateCurrentUser(@RequestBody User userUpdate, @AuthenticationPrincipal UserDetails userDetails) {
//        User user = userService.updateUser(userDetails.getUsername(), userUpdate);
//        return ResponseEntity.ok(user);
//    }
    @PatchMapping("/profile")
    public ResponseEntity<?> updateProfile(
            @RequestBody UpdateProfileRequest request,
            Principal connectedUser
    ) {
        userService.updateProfile(request, connectedUser);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/me/password")
    public ResponseEntity<?> changePassword(@RequestBody ChangePasswordRequest changePasswordRequest,
                                            @AuthenticationPrincipal UserDetails userDetails) {
        userService.changePassword(changePasswordRequest, userDetails.getUsername());
        return ResponseEntity.ok().build();
    }
}
