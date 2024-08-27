package com.seongjoon.springbasic.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.seongjoon.springbasic.dto.PostSample1RequestDto;
import com.seongjoon.springbasic.service.SampleService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/sample")
@RequiredArgsConstructor
// http://localhost:4000/sample
public class SampleController {
    
    // 외부주입 (Setter,필드,생성자) : spring에서는 생성자방식을 추구함 // lombok을 통해서
    private final SampleService sampleService;

    @PostMapping("")
    public ResponseEntity<String> postSample1 (
        @RequestBody @Valid PostSample1RequestDto requestBody
    ) {
        ResponseEntity<String> response = sampleService.postSample1(requestBody);
        return response;
    }
}
