package com.soumyajit.ISA.HIT.HALDIA.Controller;

import com.soumyajit.ISA.HIT.HALDIA.Advices.ApiResponse;
import com.soumyajit.ISA.HIT.HALDIA.Dtos.ContactSupportDTO;
import com.soumyajit.ISA.HIT.HALDIA.Service.ContactSupportService;
import com.soumyajit.ISA.HIT.HALDIA.Service.ContactSupportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/support")
@RequiredArgsConstructor
public class ContactSupportController {

    private final ContactSupportService contactSupportService;

    @PostMapping("/contact")
    public ResponseEntity<ApiResponse<String>> contactSupport(@RequestBody ContactSupportDTO dto) {
        try {
            contactSupportService.sendSupportMessage(dto);
            return ResponseEntity.ok(new ApiResponse<>("✅ Message sent to support successfully."));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(new ApiResponse<>("❌ Failed to send message: " + e.getMessage()));
        }
    }
}

