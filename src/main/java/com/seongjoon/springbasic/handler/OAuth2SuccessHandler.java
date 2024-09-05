package com.seongjoon.springbasic.handler;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.seongjoon.springbasic.service.object.CustomOAuth2User;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// OAuth2 인증 서비스 로직이 정상적으로 완료되었을 때 실행할 처리기
// - SimpleUrlAuthenticationSuccessHandler 클래스 확정
// - response 처리 담당
// Spring Bean 등록하기위한 어노테이션
@Component
public class OAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler{
    
    @Override
    public void onAuthenticationSuccess (
        HttpServletRequest request,
        HttpServletResponse response,
        // Authentication 매개변수에 서비스의 loadUser 메서드의 반환 값이 담겨있음
        Authentication authentication
    ) throws IOException, ServletException {

        CustomOAuth2User oAuth2User = (CustomOAuth2User)authentication.getPrincipal();

        response.sendRedirect("https://naver.com");

    }

}
