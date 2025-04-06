package com.soumyajit.ISA.HIT.HALDIA.EmailService;

import com.soumyajit.ISA.HIT.HALDIA.Entities.Post;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailSenderService {

    private final JavaMailSender mailSender;

    public void sendPostNotification(String recipientEmail, String adminName, Post post) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setTo(recipientEmail);
            helper.setSubject("📢 New Post on ISA & ISOI HIT SC!");

            String htmlContent = buildStyledPostEmail(post);
            String plainText = "New post on ISA & ISOI HIT SC:\n\n" + post.getTitle() + "\n\n" + post.getDescription();

            helper.setText(plainText, htmlContent);
            mailSender.send(message);

        } catch (Exception e) {
            log.error("❌ Failed to send post notification: {}", e.getMessage());
        }
    }

    private String buildStyledPostEmail(Post post) {
        String imageUrl = post.getImgOrVdos().isEmpty() ? "" : post.getImgOrVdos().get(0);
        return """
            <div style="font-family: 'Segoe UI', sans-serif; background-color: #f4f8fc; padding: 20px;">
                <div style="max-width: 600px; margin: auto; background-color: #ffffff; padding: 30px; border-radius: 10px; box-shadow: 0 2px 20px rgba(0,0,0,0.1);">
                    
                    <div style="text-align: center; margin-bottom: 20px;">
                        <img src="https://res.cloudinary.com/dcrmg4j1l/image/upload/v1743970965/isalogo-removebg-preview_h5wtkm.png"
                             alt="ISA Logo" style="width: 60px; height: auto;" />
                        <h2 style="color: #003366; margin: 10px 0 0 0;">ISA & ISOI HIT SC</h2>
                        <p style="color: #666; font-size: 14px;">Haldia, West Bengal</p>
                    </div>

                    <h3 style="color: #222222; font-size: 22px; text-align: center; margin-top: 20px;">%s</h3>
                    
                    %s

                    <p style="font-size: 16px; color: #444; line-height: 1.6; margin-top: 20px;">%s</p>

                    <div style="text-align: center; margin: 30px 0;">
                        <a href="https://your-app-link.com/post/%d" style="background-color: #004080; color: #ffffff; padding: 12px 24px; text-decoration: none; font-weight: bold; border-radius: 8px;">📲 View Post</a>
                    </div>

                    <hr style="margin-top: 30px; border: none; border-top: 1px solid #ccc;" />
                    <p style="font-size: 12px; color: #888888; text-align: center;">
                        © ISA & ISOI HIT SC, All Rights Reserved
                    </p>
                </div>
            </div>
        """.formatted(
                post.getTitle(),
                imageUrl.isEmpty() ? "" :
                        "<div style='text-align: center;'><img src='" + imageUrl + "' style='width: 100%; max-height: 350px; object-fit: cover; border-radius: 12px; margin: 20px 0;'/></div>",
                post.getDescription().replace("\n", "<br/>"),
                post.getId()
        );
    }
}
