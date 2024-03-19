//package com.example.demo.service.will;
//
//import com.example.demo.controller.dto.response.will.WillResponseDto;
//import com.example.demo.domain.will.Will;
//import com.example.demo.exception.ErrorCode;
//import com.example.demo.exception.model.NotFoundException;
//import com.example.demo.repository.WillRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class WillService {
//
//    private final WillRepository willRepository;
//
//    @Autowired
//    public WillService(WillRepository willRepository) {
//        this.willRepository = willRepository;
//    }
//
//    public Will createNote(String name, String content, String password) {
//        Will will = new Will();
//        will.setName(name);
//        will.setContent(content);
//        will.setPassword(password);
//        return willRepository.save(will);
//    }
//
//    public Will updateNote(String name, String password, WillResponseDto request) {
//        // 사용자 이름과 비밀번호를 사용하여 해당 사용자의 유서를 확인
//        Will existingWill = willRepository.findBynameAndPassword(name, password)
//                .orElseThrow(() -> new NotFoundException(ErrorCode.NOT_FOUND_USER_EXCEPTION, "Note not found for user: " + name));
//
//        // 유서의 내용을 업데이트
//        existingWill.setName(request.getName());
//        existingWill.setContent(request.getContent());
//        existingWill.setPassword(request.getPassword());
//
//        // 변경된 유서를 저장하고 반환
//        return willRepository.save(existingWill);
//    }
//
//
//    public List<Will> getAllNotes() {
//        return willRepository.findAll();
//    }
//}