package com.soumyajit.ISA.HIT.HALDIA.Controller;

import com.soumyajit.ISA.HIT.HALDIA.Advices.ApiResponse;
import com.soumyajit.ISA.HIT.HALDIA.Security.AuthService;
import com.soumyajit.ISA.HIT.HALDIA.Service.adminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final adminService adminService;

    // Promote user to admin
    @PostMapping("/make-admin/{userId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<String>> makeUserAdmin(@PathVariable Long userId) {
        adminService.makeUserAdmin(userId);
        return ResponseEntity.ok(new ApiResponse<>("User promoted to ADMIN successfully 🚀"));
    }

}
