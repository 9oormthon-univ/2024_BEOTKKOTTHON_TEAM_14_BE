package com.example.demo.controller;

import com.example.demo.controller.dto.request.GhostTestRequest;
import com.example.demo.service.ghost.GhostTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ghostTest")
public class GhostTestController {
    @Autowired
    private GhostTestService ghostTestService;

    @PostMapping
    public String detectGhostType(@RequestBody GhostTestRequest request) {
        return ghostTestService.ghostType(request);
    }
}
