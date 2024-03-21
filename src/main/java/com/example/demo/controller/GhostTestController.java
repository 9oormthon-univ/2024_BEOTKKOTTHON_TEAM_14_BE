package com.example.demo.controller;

import com.example.demo.common.dto.BaseResponse;
import com.example.demo.controller.dto.request.GhostTestRequest;
import com.example.demo.exception.SuccessCode;
import com.example.demo.service.ghost.GhostTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ghostTest")
public class GhostTestController {
    @Autowired
    private GhostTestService ghostTestService;

    @PostMapping
    public ResponseEntity<BaseResponse<Integer>> ghostType(@RequestBody GhostTestRequest request) {
        int ghostType = ghostTestService.ghostType(request);
        BaseResponse<Integer> response = BaseResponse.success(SuccessCode.CREATE_GHOSTTYPE_SUCCESS, ghostType);
        return ResponseEntity.ok(response);
    }

}
