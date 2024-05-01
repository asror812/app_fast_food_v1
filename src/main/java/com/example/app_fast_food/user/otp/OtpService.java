package com.example.app_fast_food.user.otp;

import com.example.app_fast_food.common.exceptions.OtpException;
import com.example.app_fast_food.common.notification.sms.SmsNotificationService;
import com.example.app_fast_food.common.response.CommonResponse;
import com.example.app_fast_food.user.otp.dto.ValidatePhoneNumberDTO;
import com.example.app_fast_food.user.otp.entity.Otp;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class OtpService {
    private final OtpRepository otpRepository;
    private final Random random = new Random();
    private final String VERIFICATION_MASSAGE = "Your verification code is: %d%n";
    @Value("${spring.otp.retry-wait-time}")
    private int retryWaitTime;
    @Value("${spring.otp.retry-count}")
    private int retryCount;
    @Value("${spring.otp.time-to-live}")
    private int timeToLive;

    private final SmsNotificationService smsNotificationService;


    public CommonResponse<String> sendSms(ValidatePhoneNumberDTO validatePhoneNumberDTO) {

        String phoneNumber = validatePhoneNumberDTO.getPhoneNumber();
        Optional<Otp> existingOtp = otpRepository.findByPhoneNumber(phoneNumber);


        if(validatePhoneNumberDTO.getOtp() == null){
            if(existingOtp.isPresent()){
                return reTry(existingOtp.get());
            }
           Otp otp = sendSmsInternal(phoneNumber) ;
           otpRepository.save(otp);
           return CommonResponse.succeed("Sms was sent successfully");
        }
        Otp otp = existingOtp.orElseThrow(() -> new EntityNotFoundException("We didnt send any verification"));

        if(otp.getCode() == validatePhoneNumberDTO.getOtp()){
           otp.setVerified(true);
           otpRepository.save(otp);
           return CommonResponse.succeed("Otp was successfully verified");
        }else return CommonResponse.fail("Otp was incorrect");
    }

    private CommonResponse<String> reTry(Otp otp) {
        if (otp.getLastSendTime().plusSeconds(retryWaitTime).isAfter(LocalDateTime.now())) {
            long resentTime = Duration.between(otp.getLastSendTime(), LocalDateTime.now()).getSeconds();
            throw new OtpException.OtpEarlyResentException(retryWaitTime - resentTime);
        }

        if (otp.getSendCount() >= retryCount) {
            throw new OtpException.OtpLimitExitedException(otp.getSendCount(), otp.getCreatedAt().plusSeconds(timeToLive));
        }

        Otp otp1 = sendSmsInternal(otp);
        otpRepository.save(otp1);
        return CommonResponse.succeed("Sms was re-send successfully") ;
    }

    private Otp sendSmsInternal(String phoneNumber) {
        int code = random.nextInt(10000, 99999);
        smsNotificationService.sendNotification(phoneNumber , VERIFICATION_MASSAGE.formatted(code));
        return new Otp(phoneNumber , code ,1 , LocalDateTime.now() , LocalDateTime.now() , false);
    }


    private Otp sendSmsInternal(Otp otp) {
        int code = random.nextInt(10000, 99999);
        smsNotificationService.sendNotification(otp.getPhoneNumber(), VERIFICATION_MASSAGE.formatted(code));

        otp.setLastSendTime(LocalDateTime.now());
        otp.setSendCount(otp.getSendCount() + 1);
        otp.setCode(code);

        return otp;
    }
}
