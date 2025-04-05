package com.soumyajit.ISA.HIT.HALDIA.Service;

import com.soumyajit.ISA.HIT.HALDIA.Dtos.UserProfileDTOS;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface UserProfileService {
    //UserProfileDTOS updateUserProfile(Map<String, Object> updates, MultipartFile image) throws IOException;

    UserProfileDTOS getUserProfile(Long userId);

    List<UserProfileDTOS> searchUserByName(String name);

    UserProfileDTOS updateProfilePicture(MultipartFile profilePicture) throws IOException;

    UserProfileDTOS updateUserDetails(Map<String, Object> updates);
}
