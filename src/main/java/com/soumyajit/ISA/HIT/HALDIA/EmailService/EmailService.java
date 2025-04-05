package com.soumyajit.ISA.HIT.HALDIA.EmailService;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendPasswordResetEmail(String to, String code, String name) {
        String subject = "Your Password Reset Request";
        String htmlContent = buildStyledEmail("Password Reset Request",
                "To reset your password, please use the following code:",
                code,
                "Note: This code is valid for only 30 minutes.",
                name);
        sendHtmlEmail(to, subject, htmlContent);
    }

    public void sendOtpEmail(String to, String otp, String name) {
        String subject = "Your OTP Code";
        String htmlContent = buildStyledEmail("OTP Verification",
                "Your OTP code is:",
                otp,
                "Note: This code is valid for a short duration.",
                name);
        sendHtmlEmail(to, subject, htmlContent);
    }

    public void sendPasswordResetSuccessEmail(String to, String name) {
        String subject = "Your Password Reset Successful";
        String htmlContent = buildResetSuccessEmail(name);
        sendHtmlEmail(to, subject, htmlContent);
    }


    private void sendHtmlEmail(String to, String subject, String htmlContent) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(htmlContent, true);
            mailSender.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException("Failed to send styled email", e);
        }
    }

    private String buildStyledEmail(String title, String message, String code, String note, String userName) {
        return String.format("""
            <div style="font-family: 'Segoe UI', sans-serif; background-color: #f4f8fc; padding: 20px;">
                <div style="max-width: 600px; margin: auto; background-color: #ffffff; padding: 30px; border-radius: 10px; box-shadow: 0 0 10px rgba(0,0,0,0.1);">
                    <div style="text-align: center; margin-bottom: 10px;">
                        <img src="com.soumyajit.ISA.HIT.HALDIA.EmailService.Images/isalogo-removebg-preview.png"
                             alt="ISA Logo" style="width: 100px; height: auto;" />
                    </div>
                    <hr style="margin-top: 30px; border: none; border-top: 1px solid #ccc;" />
                    <p style="text-align: center; font-size: 16px; color: #003366; margin-bottom: 20px;">
                        <strong>Greetings from ISA HIT Student Chapter, %s</strong>
                    </p>
                    <h2 style="color: #003366; text-align: center;">%s</h2>
                    <p style="font-size: 16px; color: #333333; text-align: center;">%s</p>
                    <div style="margin: 30px auto; text-align: center;">
                        <span style="font-size: 24px; font-weight: bold; color: #004080; padding: 10px 20px; background-color: #e0ebf5; border: 2px dashed #004080; border-radius: 6px;">%s</span>
                    </div>
                    <p style="font-size: 14px; color: #555555; text-align: center;">%s</p>
                    <hr style="margin-top: 30px; border: none; border-top: 1px solid #ccc;" />
                    <p style="font-size: 12px; color: #888888; text-align: center; margin-top: 10px;">
                        ISA HIT Student Chapter | Haldia, West Bengal
                    </p>
                </div>
            </div>
            """, userName, title, message, code, note);
    }
    private String buildResetSuccessEmail(String userName) {
        return """
        <div style="font-family: 'Segoe UI', sans-serif; background-color: #f4f8fc; padding: 20px;">
            <div style="max-width: 600px; margin: auto; background-color: #ffffff; padding: 30px; border-radius: 10px; box-shadow: 0 0 10px rgba(0,0,0,0.1);">
                <div style="text-align: center; margin-bottom: 10px;">
                    <img src="https://upload.wikimedia.org/wikipedia/en/thumb/0/00/International_Society_of_Automation_logo.svg/1200px-International_Society_of_Automation_logo.svg.png"
                         alt="ISA Logo" style="width: 100px; height: auto;" />
                </div>
                <hr style="margin-top: 30px; border: none; border-top: 1px solid #ccc;" />
                <p style="text-align: center; font-size: 16px; color: #003366; margin: 10px 0;">
                    <strong>Greetings from ISA HIT Student Chapter, %s</strong>
                </p>
                <h2 style="color: #003366; text-align: center;">Password Reset Successful</h2>
                <p style="font-size: 16px; color: #333333; text-align: center;">
                    Your password has been successfully reset. You can now log in with your new password.
                </p>
                <p style="font-size: 14px; color: #555555; text-align: center;">
                    If you did not perform this action, please contact support immediately.
                </p>
                <hr style="margin-top: 30px; border: none; border-top: 1px solid #ccc;" />
                <p style="font-size: 12px; color: #888888; text-align: center; margin-top: 10px;">
                    ISA HIT Student Chapter | Haldia, West Bengal
                </p>
            </div>
        </div>
        """.formatted(userName);
    }
}
