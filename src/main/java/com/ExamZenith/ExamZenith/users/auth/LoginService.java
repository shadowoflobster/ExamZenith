package com.ExamZenith.ExamZenith.users.auth;


import com.ExamZenith.ExamZenith.errors.InvalidLoginException;
import com.ExamZenith.ExamZenith.users.UserService;
import com.ExamZenith.ExamZenith.users.persistence.Role;
import com.ExamZenith.ExamZenith.users.persistence.User;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimNames;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LoginService {

    @Value("${jwt.secret-key}")
    private String secretkey;

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public LoginResponse login(LoginRequest request){
        User user=userService.getUser(request.getEmail());
        if(passwordEncoder.matches(request.getPassword(), user.getPassword())){
                return generateLoginResponse(user);
        }
        throw new InvalidLoginException("Invalid login");
    }


    private LoginResponse generateLoginResponse(User user){
        try {
            JWTClaimsSet claims = new JWTClaimsSet.Builder()
                    .subject(user.getEmail())
                    .claim("user_id", user.getId())
                    .claim("roles", user.getRoles().stream().map(Role::getName).collect(Collectors.toSet()))
                    .issuer("exam_zenith")
                    .expirationTime(new Date(System.currentTimeMillis() + 36000 * 1000))
                    .build();
            JWSHeader header = new JWSHeader(JWSAlgorithm.HS256);
            SignedJWT signedJWT = new SignedJWT(header,claims);
            signedJWT.sign(new MACSigner(secretkey.getBytes()));

            return new LoginResponse(signedJWT.serialize());
        }catch (Exception a){
            throw new InvalidLoginException("Failed to generate token");
        }
    }

}
