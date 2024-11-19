package org.example.backend.sendEmail;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    private final JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String senderEmail;

    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendNewAccountNVEmail(String recipientEmail, String email ,String matKhau) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(senderEmail);
        message.setTo(recipientEmail);
        message.setSubject("Chào Mừng: Thư xác nhận nhân viên của");
        message.setText("Chào "+ recipientEmail + " ,\n\n" +
                        "Bạn vừa dùng mail này để đăng kí tài khoản http://localhost:3000,\n\n" +
                        "Tài khoản mới với tên đăng nhập : "+ email+" ,\n\n" +
                        "Mật Khẩu đăng nhập : " + matKhau + " ,\n\n" +
                        "Một lần nữa chúc mừng bạn là thành viên của http://localhost:3000 : http://localhost:3000  ,\n\n" +
                        " * Quý khách vui lòng không trả lời email này * ,\n\n" +
                        "Trân trọng,\n[]");

        javaMailSender.send(message);
    }
    public void sendNewAccountKHEmail(String recipientEmail, String email ,String matkhau) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(senderEmail);
        message.setTo(recipientEmail);
        message.setSubject("Thông báo: Tài khoản mới đã được tạo");
        message.setText("Chào '"+ recipientEmail +"' ,\n\n" +
                        "Bạn vừa dùng mail này để đăng kí tài khoản BeeStore,\n\n" +
                        "Tài khoản mới với tên đăng nhập : "+ email +" ,\n\n" +
                        "Mật Khẩu đăng nhập : " +matkhau  + " ,\n\n" +
                        "Cùng đăng nhập để trải nhiệm nhiều tiện ích tuyệt vời nhé :  http://localhost:3000  ,\n\n" +
                        " * Quý khách vui lòng không trả lời email này * ,\n\n" +
                        "Trân trọng,\n[]");

        javaMailSender.send(message);
    }



    public void sendPasswordEmail(String recipientEmail, String newPassword) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(senderEmail);
        message.setTo(recipientEmail);
        message.setSubject("THÔNG BÁO : BẠN ĐÃ YÊU CẦU KHÔI PHỤC MẬT KHẨU !");
        message.setText("Chào "+ recipientEmail + " ,\n\n" +
                        "Bạn vừa dùng mail này để xác nhận quên mật khẩu tài khoản BeeStore,\n\n" +
                        "Mật Khẩu đăng nhập mới của bạn là : " + newPassword + " ,\n\n" +
                        "Nếu bạn không xác nhận quên mật khẩu mà vẫn nhận được mail này thì hãy liên hệ lại với http://localhost:3000 ngay lập tức qua hotline :   ,\n\n" +
                        "Trân trọng,\n[]");

        javaMailSender.send(message);
    }
}
