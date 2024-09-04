package com.seongjoon.springbasic.service.implement;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.seongjoon.springbasic.dto.PostUserRequestDto;
import com.seongjoon.springbasic.dto.SignInRequestDto;
import com.seongjoon.springbasic.entity.SampleUserEntity;
import com.seongjoon.springbasic.provider.JwtProvider;
import com.seongjoon.springbasic.repository.SampleUserRepository;
import com.seongjoon.springbasic.service.AuthService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImplement implements AuthService {

    // JwtProvider : 토큰 만들수 있는 곳
    private final JwtProvider jwtProvider;
    private final SampleUserRepository sampleuserRepository;

    // PasswordEncoder 인터페이스:
    // - 비밀번호를 안전하고 쉽게 암호화하여 관리할 수 있도록 도움주는 인터페이스
    // - 구현체
    // - BCryptPasswordEncoder, SCryptPasswordEncoder, Pbkdf2PasswordEncoder
    // - String encode(평문비밀번호)를 매개변수로 받음 : 평문비밀번호를 암호화하여 반환
    // - boolean matches(평문비밀번호, 암호화된비밀번호): 평문비밀번호와 암호화된 비밀번호가 일치하는지 여부를 반환
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public String signUp(PostUserRequestDto dto) {
        
        try {
            
            String userId = dto.getUserId();
            boolean isExistedId = sampleuserRepository.existsById(userId);
            if(isExistedId)  return "존재하는 아이디!";

            String telNumber = dto.getTelNumber();
            boolean isExistedTelNumber = sampleuserRepository.existsByTelNumber(telNumber);
            if(isExistedTelNumber) return"존재하는 전화번호!";

            // String password = dto.getPassword();
            // String name = dto.getName();
            // String address = dto.getAddress();
            // SampleUserEntity userEntity = new SampleUserEntity(userId, password, name, address, telNumber);
            // ! ↆ 흐름상 좋지 않은 작업 ↆ Entity 는 원래 setter를 가지면 안됨!!
            // SampleUserEntity userEntity = new SampleUserEntity();
            // userEntity.setUserId(userId);
            // userEntity.setPassword(password);
            
            // 특정한 값을 넣어주는 것을 build 패턴이라고 한다.
            // SampleUserEntity userEntity = 
            //     SampleUserEntity.builder()
            //         .userId(userId)
            //         .password(password)
            //         .name(name)
            //         .address(address)
            //         .telNumber(telNumber)
            //         .build();

            // 비밀번호 암호화
            String password = dto.getPassword();
            String encodedPassword = passwordEncoder.encode(password);
            dto.setPassword(encodedPassword);

            SampleUserEntity userEntity = new SampleUserEntity(dto);
            sampleuserRepository.save(userEntity);

            return "성공!";

        } catch (Exception e) {
            e.printStackTrace();
            return "예외발생!";
        }
    }

    @Override
    public String signIn(SignInRequestDto dto) {
        
        
        try {
            String userId = dto.getUserId();
            SampleUserEntity userEntity = sampleuserRepository.findByUserId(userId);
            if(userEntity == null) return "로그인 정보가 일치하지 않습니다.";

            String password = dto.getPassword();
            String encodedPassword = userEntity.getPassword(); // 암호회되어있는 패스워드

            // 비밀번호와 암호화된 비밀번호가 일치하는지 알수있는 작업
            boolean isMatched = passwordEncoder.matches(password, encodedPassword);
            if (!isMatched) return "로그인 정보가 일치하지 않습니다.";

            String token = jwtProvider.create(userId);
            return token;

        } catch (Exception e) {
            e.printStackTrace();
            return "예외 발생!";
        }

    }

}
