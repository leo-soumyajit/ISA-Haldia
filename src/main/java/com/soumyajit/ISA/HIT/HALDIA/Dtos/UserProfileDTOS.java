package com.soumyajit.ISA.HIT.HALDIA.Dtos;

import lombok.Data;

import java.util.List;

@Data
public class UserProfileDTOS {
    private Long id;
    private String userName;
    private List<ProfilePostDTOS> postsList;
    private String bio;
    private String profileImage;

}
