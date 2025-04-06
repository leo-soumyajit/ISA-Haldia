package com.soumyajit.ISA.HIT.HALDIA.Service;

import com.soumyajit.ISA.HIT.HALDIA.Entities.Enums.Roles;
import com.soumyajit.ISA.HIT.HALDIA.Entities.User;
import com.soumyajit.ISA.HIT.HALDIA.Exception.ResourceNotFound;
import com.soumyajit.ISA.HIT.HALDIA.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class adminServiceImpl implements adminService{

    private final UserRepository userRepository;

    @Override
    public void makeUserAdmin(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(()->new ResourceNotFound("User with this id not found"));

        user.getRoles().add(Roles.ADMIN);
        log.info("making user Admin with this id : {}",userId);
        userRepository.save(user);
    }
}
