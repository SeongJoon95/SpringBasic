package com.seongjoon.springbasic.service;

import com.seongjoon.springbasic.dto.PostUserRequestDto;
import com.seongjoon.springbasic.dto.SignInRequestDto;

public interface AuthService {
    String signUp(PostUserRequestDto dto);
    String signIn(SignInRequestDto dto);
}
