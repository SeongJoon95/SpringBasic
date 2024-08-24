package com.seongjoon.springbasic.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.seongjoon.springbasic.service.BasicService;
import com.seongjoon.springbasic.service.implement.BasicServiceImplement;

@RestController
@RequestMapping("/service")
public class ServiceController {

    private BasicService basicService;

    @GetMapping("")
    public ResponseEntity<String> getService() {
        return basicService.getService();
    }

}