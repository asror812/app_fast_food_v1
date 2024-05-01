package com.example.app_fast_food.user;


import com.example.app_fast_food.common.response.CommonResponse;
import com.example.app_fast_food.security.JwtService;
import com.example.app_fast_food.user.dto.*;
import com.example.app_fast_food.user.otp.OtpService;
import com.example.app_fast_food.user.otp.dto.ValidatePhoneNumberDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final JwtService jwtService;
    private final OtpService otpService;

    @PostMapping("/auth/validate")
    public CommonResponse<String> validatePhoneNumber(@RequestBody @Valid ValidatePhoneNumberDTO validatePhoneNumber){
        return otpService.sendSms(validatePhoneNumber);
    }

    @PostMapping("/auth/signUp")
    public CommonResponse<String> signUp(@RequestBody @Valid UserCreateDTO createDTO){

           CommonResponse<UserResponseDTO> userResponseDTO = authService.create(createDTO);
           String token = jwtService.generateToken(userResponseDTO.getData().getPhoneNumber());

           return CommonResponse.succeed(token);
    }


    @PostMapping("auth/signIn")
    public CommonResponse<String> signIn(@RequestBody @Valid SignInDTO signInDTO){
        String token = authService.signIn(signInDTO).getData();

        return CommonResponse.succeed(token);
    }
}
