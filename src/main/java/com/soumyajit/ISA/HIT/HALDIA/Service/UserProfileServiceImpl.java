package com.soumyajit.ISA.HIT.HALDIA.Service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.soumyajit.ISA.HIT.HALDIA.Dtos.UserProfileDTOS;
import com.soumyajit.ISA.HIT.HALDIA.Entities.User;
import com.soumyajit.ISA.HIT.HALDIA.Exception.ResourceNotFound;
import com.soumyajit.ISA.HIT.HALDIA.Repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserProfileServiceImpl implements UserProfileService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final Cloudinary cloudinary;

    @Transactional
    @CachePut(value = "userProfile", key = "#root.methodName + '_' + T(org.springframework.security.core.context.SecurityContextHolder).getContext().getAuthentication().getPrincipal().getId()")
    public UserProfileDTOS updateUserDetails(Map<String, Object> updates) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        List<String> restrictedFields = Arrays.asList("email", "password");

        updates.forEach((key, value) -> {
            if (restrictedFields.contains(key)) {
                throw new IllegalArgumentException("email and password cannot be updated through this method");
            }
            Field fieldToBeSaved = ReflectionUtils.findRequiredField(User.class, key);
            fieldToBeSaved.setAccessible(true);
            ReflectionUtils.setField(fieldToBeSaved, user, value);
        });

        User updatedUser = userRepository.save(user);
        return modelMapper.map(updatedUser, UserProfileDTOS.class);
    }

    @Transactional
    @CachePut(value = "userProfile", key = "#root.methodName + '_' + T(org.springframework.security.core.context.SecurityContextHolder).getContext().getAuthentication().getPrincipal().getId()")
    public UserProfileDTOS updateProfilePicture(MultipartFile profilePicture) throws IOException {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (profilePicture != null && !profilePicture.isEmpty()) {
            Map uploadResult = cloudinary.uploader().upload(profilePicture.getBytes(), ObjectUtils.emptyMap());
            String profilePictureUrl = uploadResult.get("url").toString();
            user.setProfileImage(profilePictureUrl);
        }

        User updatedUser = userRepository.save(user);
        return modelMapper.map(updatedUser, UserProfileDTOS.class);
    }

    @Override
    @Cacheable(value = "userProfile", key = "#userId")
    @CachePut(value = "userProfile", key = "#userId")
    public UserProfileDTOS getUserProfile(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() ->
                new ResourceNotFound("User not found with this id " + userId));
        return modelMapper.map(user, UserProfileDTOS.class);
    }

    @Override
    @Cacheable(value = "searchUsers", key = "#name")
    public List<UserProfileDTOS> searchUserByName(String name) {
        List<User> user = userRepository.findByNameContainingIgnoreCase(name);
        return user.stream()
                .map(user1 -> modelMapper.map(user1, UserProfileDTOS.class))
                .collect(Collectors.toList());
    }
}
