package com.seongjoon.springbasic.service.implement;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;

import com.seongjoon.springbasic.dto.PostSample1RequestDto;
import com.seongjoon.springbasic.entity.SampleTable1Entity;
import com.seongjoon.springbasic.entity.SampleUserEntity;
import com.seongjoon.springbasic.provider.JwtProvider;
import com.seongjoon.springbasic.repository.SampleTable1Repository;
import com.seongjoon.springbasic.repository.SampleUserRepository;
import com.seongjoon.springbasic.service.SampleService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SampleServiceImplement implements SampleService {
    
    private final SampleTable1Repository sampleTable1Repository;
    private final SampleUserRepository sampleUserRepository;
    private final JwtProvider jwtProvider;

    @Override
    public ResponseEntity<String> postSample1(PostSample1RequestDto dto) {
        
        String sampleId = dto.getSampleId();
        Integer sampleColumn = dto.getSampleColumn();

        // SELECT (SQL: SELECT)
        // 1. repositroy를 이용하여 조회 (findAll, findById)
        // SampleTable1Entity existEntity = sampleTable1Repository.findById(sampleId).get();
        // 2. repository를 이용하여 조회 (existsById)
        boolean isExisted = sampleTable1Repository.existsById(sampleId); 
        if (isExisted) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("이미 존재하는 기본키입니다.");

        // CREATE (SQL : INSERT)
        // 1. Entity 클래스의 인스턴스 생성
        SampleTable1Entity entity = new SampleTable1Entity(sampleId, sampleColumn);
        
        // 2. 생성한 인스턴스를 repository를 이용하여 저장
        // save() : 저장
        // - 만약에 Primary Key가 동일한 레코드가 존재하지 않으면 레코드 생성
        // - 동일한 레코드가 존재하면 수정
        sampleTable1Repository.save(entity);

        // CREATED : 201 // 생성되었음을 의미
        return ResponseEntity.status(HttpStatus.CREATED).body("성공");
    }

    @Override
    public ResponseEntity<String> deleteSample1(String sampleId) {
        
        // DELETE (SQL : DELETE)
        // 1. repository를 이용하여 ID(pk)에 해당하는 레코드 삭제
        // - 해당하는 레코드가 존재하지 않아도 에러가 발생하지 않음
        // sampleTable1Repository.deleteById(sampleId);
        // 2. repository를 이용하여 Entity에 해당하는 레코드 삭제
        // - 해당하는 레코드가 존재하지 않을 때 수행 불가능
        SampleTable1Entity entity = sampleTable1Repository.findById(sampleId).get();
        sampleTable1Repository.delete(entity);

        return ResponseEntity.status(HttpStatus.OK).body("성공");

    }

    @Override
    public ResponseEntity<String> qureString() {
        
        // List<SampleUserEntity> sampleUserEntities = sampleUserRepository.getJpql("서타몽", "서울특별시");
        // List<SampleUserEntity> sampleUserEntities = sampleUserRepository.getJpq2("이동훈", "동래구");
        List<SampleUserEntity> sampleUserEntities = sampleUserRepository.getNativeSql("서타몽","서울특별시");
        return ResponseEntity.status(HttpStatus.OK).body(sampleUserEntities.toString());
    }

    @Override
    public String getJwt(String name) {
        String jwt = jwtProvider.create(name);
        return jwt;
    }

    @Override
    public String validateJwt(String jwt) {
        String subject = jwtProvider.validate(jwt); 
        return subject;
    }
    
}

