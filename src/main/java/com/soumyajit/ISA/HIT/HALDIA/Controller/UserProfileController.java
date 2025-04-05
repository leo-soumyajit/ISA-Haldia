package com.soumyajit.ISA.HIT.HALDIA.Controller;

import com.soumyajit.ISA.HIT.HALDIA.Dtos.UserProfileDTOS;
import com.soumyajit.ISA.HIT.HALDIA.Service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user-profile")
@RequiredArgsConstructor
public class UserProfileController {
    private final UserProfileService userProfileService;



    @GetMapping("/{userId}")
    public ResponseEntity<UserProfileDTOS> getUserProfile(@PathVariable Long userId) {

        return ResponseEntity.ok(userProfileService.getUserProfile(userId));
    }


    @PatchMapping(value = "/update/details")
    public ResponseEntity<UserProfileDTOS> updateUserDetails(@RequestParam Map<String, Object> updates) throws IOException {
        return ResponseEntity.ok(userProfileService.updateUserDetails(updates));
    }

    @PatchMapping(value = "/update/profile-picture", consumes = {"multipart/form-data"})
    public ResponseEntity<UserProfileDTOS> updateProfilePicture(@RequestParam("profilePicture") MultipartFile profilePicture) throws IOException {
        return ResponseEntity.ok(userProfileService.updateProfilePicture(profilePicture));
    }


    @GetMapping("/search")
    public ResponseEntity<List<UserProfileDTOS>>searchUserByName(@RequestParam String name){
        return ResponseEntity.ok(userProfileService.searchUserByName(name));
    }

}














