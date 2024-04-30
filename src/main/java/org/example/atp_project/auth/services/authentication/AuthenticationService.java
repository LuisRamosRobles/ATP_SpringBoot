package org.example.atp_project.auth.services.authentication;

import org.example.atp_project.auth.dto.JwtAuthResponse;
import org.example.atp_project.auth.dto.UserSignInRequest;
import org.example.atp_project.auth.dto.UserSignUpRequest;

public interface AuthenticationService {
    JwtAuthResponse signUp(UserSignUpRequest request);

    JwtAuthResponse signIn(UserSignInRequest request);


}
