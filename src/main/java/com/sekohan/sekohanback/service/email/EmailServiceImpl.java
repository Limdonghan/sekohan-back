package com.sekohan.sekohanback.service.email;

import com.sekohan.sekohanback.dto.email.EmailMessageDTO;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.Random;

@Slf4j  //로깅을 처리하는데 사용, 로그를 기록
@Service //빈 등록
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService{


    private final JavaMailSender javaMailSender;

    private String authNum;  //인증번호를 저장할 변수

    
    //메일을 보내는 메서드
    @Override
    public String sendMail(EmailMessageDTO emailMessage, String type) { //객체와 이메일 유형을 매개변수로 받음
        authNum = createCode();  //createCode메서드를 호출하여 무작위 인증번호를 생성하고 저장

        MimeMessage mimeMessage = javaMailSender.createMimeMessage(); //MimeMessage 객체생성

        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, false, "UTF-8");
            mimeMessageHelper.setTo(emailMessage.getTo()); // 메일 수신자
            mimeMessageHelper.setSubject(emailMessage.getSubject()); // 메일 제목
            mimeMessageHelper.setText(authNum); // 메일 본문 내용, 인증번호
            javaMailSender.send(mimeMessage); //javaMailSender.send를 호출하여 이메일을 보내고 성공시 로그에 Success 기록

            log.info("Success");
            return authNum; //사용자로부터 받은 인증번호를 확인하기 위해 리턴

        } catch (MessagingException e) {
            log.info("fail");
            throw new RuntimeException(e);
        }
    }
    //사용자로부터 받은 인증번호 체크 메서드
    @Override
    public boolean checkCode(String userInput) {
        return userInput.equals(authNum);  //입력한 인증번호와 저장된 인증번호 비교
    }

    //인증번호 및 임시 비밀번호 생성 메서드
    public String createCode() {
        Random random = new Random();
        StringBuffer key = new StringBuffer();

        for (int i = 0; i < 8; i++) {
            int index = random.nextInt(4);
            switch (index) {
                case 0:
                    key.append((char) ((int) random.nextInt(26) + 97));
                    break;
                case 1:
                    key.append((char) ((int) random.nextInt(26) + 65));
                    break;
                default:
                    key.append(random.nextInt(9));
            }
        }
        return key.toString();
    }
}
