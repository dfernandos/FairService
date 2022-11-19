package com.feirapp.fairService.controller;

import com.feirapp.fairService.entity.Fair;
import com.feirapp.fairService.service.FairService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FairController {

    @Autowired
    private FairService fairService;

    @PostMapping("/addFair")
    public Fair addFair(@RequestBody Fair fair){
        return fairService.saveFair(fair);
    }

    @GetMapping("/fairs")
    public List<Fair> getAllFairs(){
        return fairService.getFairs();
    }
}
