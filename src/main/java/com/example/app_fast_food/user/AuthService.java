package com.example.app_fast_food.user;


import com.example.app_fast_food.common.exceptions.OtpException;

import com.example.app_fast_food.common.response.CommonResponse;
import com.example.app_fast_food.common.service.GenericService;
import com.example.app_fast_food.role.Role;
import com.example.app_fast_food.role.RoleRepository;
import com.example.app_fast_food.security.JwtService;

import com.example.app_fast_food.user.dto.UserCreateDTO;
import com.example.app_fast_food.user.dto.UserResponseDTO;
import com.example.app_fast_food.user.dto.UserUpdateDTO;
import com.example.app_fast_food.user.entity.User;
import com.example.app_fast_food.user.otp.OtpRepository;
import com.example.app_fast_food.user.otp.OtpService;
import com.example.app_fast_food.user.otp.entity.Otp;
import io.jsonwebtoken.security.Password;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.app_fast_food.user.dto.SignInDTO;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Getter
public class AuthService extends GenericService<User, UUID , UserResponseDTO , UserCreateDTO, UserUpdateDTO> implements UserDetailsService {

    private final UserRepository repository;
    private final Class<User> entityClass = User.class;
    private final UserDTOMapper mapper;
    private final OtpRepository otpRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;


    @Override
    protected CommonResponse<UserResponseDTO> internalCreate(UserCreateDTO userCreateDTO) {

        User entity = mapper.toEntity(userCreateDTO);

        System.out.println(entity);

        entity.setId(UUID.randomUUID());
        entity.setPassword(passwordEncoder.encode(entity.getPassword()));

        entity.setRoles(Set.of());
        isPhoneNumberVerified(userCreateDTO.getPhoneNumber());


        System.out.println("**************");
        User saved = repository.save(entity);


        return CommonResponse.succeed(mapper.toResponseDTO(saved));

    }

    private void isPhoneNumberVerified(String phoneNumber) {
        System.out.println("Otp start : " + phoneNumber);

        Otp otp = otpRepository
                .findByPhoneNumber(phoneNumber)
                .orElseThrow(() -> new OtpException.PhoneNumberNotVerified(phoneNumber));

        if (!otp.isVerified()) {
            throw new OtpException.PhoneNumberNotVerified(phoneNumber);
        }
        System.out.println("Otp end:");
    }

    @Override
    protected CommonResponse<UserResponseDTO> internalUpdate(UUID uuid, UserUpdateDTO userUpdateDTO) {

        return CommonResponse.succeed(mapper.toResponseDTO(repository.findById(uuid).orElseThrow()));
    }


    @Transactional
    public CommonResponse<String> signIn(SignInDTO signInDTO) {
        User user = userRepository.findByPhoneNumber(signInDTO.getPhoneNumber())
                .orElseThrow(() -> new UsernameNotFoundException("Phone number or password is incorrect"));

        if (!passwordEncoder.matches(signInDTO.getPassword(), user.getPassword())) {
            throw new BadCredentialsException("Username or password is not correct");
        }

        System.out.println(passwordEncoder.encode(user.getPassword()) +  "   :   " +  user.getPassword() + "  :  " + signInDTO.getPassword() );


        String token = jwtService.generateToken(signInDTO.getPhoneNumber());

        return CommonResponse.succeed(token);
    }





    @Override
    public UserDetails loadUserByUsername(String phoneNumber) throws UsernameNotFoundException {
            Optional<User> optionalUser = userRepository.findByPhoneNumber(phoneNumber);

            return optionalUser.orElseThrow(() ->
                    new UsernameNotFoundException("User not found with phone number %s".formatted(phoneNumber)));


    }
}
