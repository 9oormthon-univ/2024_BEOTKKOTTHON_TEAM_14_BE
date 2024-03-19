package com.example.demo.controller;


import com.example.demo.common.dto.BaseResponse;
import com.example.demo.controller.dto.request.WillRequestDto;
import com.example.demo.exception.SuccessCode;
import com.example.demo.service.will.WillService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/will")
public class WillController {
    private final WillService willService;

    @PostMapping("/create")
    public BaseResponse<String> createWill(@RequestBody @Valid WillRequestDto willRequestDto){
        willService.createWill(willRequestDto);
        return BaseResponse.success(SuccessCode.CREATE_COMPLETE_SUCCESS,SuccessCode.CREATE_COMPLETE_SUCCESS.getMessage());
    }

    @DeleteMapping("/delete/{userId}")
    public BaseResponse<String> deleteWill(@PathVariable Long userId){
        willService.deleteWill(userId);
        return BaseResponse.success(SuccessCode.DELETE_SUCCESS, SuccessCode.DELETE_SUCCESS.getMessage());
    }



}