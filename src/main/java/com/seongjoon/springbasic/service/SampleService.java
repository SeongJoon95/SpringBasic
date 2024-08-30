package com.seongjoon.springbasic.service;

import org.springframework.http.ResponseEntity;

import com.seongjoon.springbasic.dto.PostSample1RequestDto;

public interface SampleService {
    
    ResponseEntity<String> postSample1 (PostSample1RequestDto dto);
    ResponseEntity<String> deleteSample1 (String sampleId);
    ResponseEntity<String> qureString();
    String getJwt(String name);
    String validateJwt(String jwt); 
}
