package com.example.demo.service.ghost;


import com.example.demo.controller.dto.request.GhostTestRequest;
import org.springframework.stereotype.Service;

@Service
public class GhostTestService {

    public int ghostType(GhostTestRequest request) {
        int q1 = request.getQ1();
        int q2 = request.getQ2();
        int q3 = request.getQ3();
        int q4 = request.getQ4();

        boolean consistent = q1 == q2 && q3 != q4; // 1번2번 질문에 대해서 답이 다르면 -> 신비로운 유령으로!

        if (!consistent) {
            return 5; // 신비로운 유령
        } else {
            int[] scores = new int[4];
            scores[0] = (q1 == 0 && q2 == 0) ? 1 : 0; // 고요한 유령
            scores[1] = (q1 == 1 && q3 == 1) ? 1 : 0; // 열정 넘치는 유령
            scores[2] = (q1 == 1 && q3 == 1 && q4 == 0) ? 2 : 0; // 흥많은 유령
            scores[3] = (q1 == 1 && q2 == 1 && q3 == 1) ? 2 : 0; // 영향력 있는 유령

            // 가장 높은 점수를 가진 유형 반환
            int maxIndex = 0;
            int maxValue = scores[0];
            for (int i = 1; i < scores.length; i++) {
                if (scores[i] > maxValue) {
                    maxValue = scores[i];
                    maxIndex = i;
                }
            }

            // 유령 유형 반환하는거는 프론트랑 상의하고 바꿀예정
            switch (maxIndex) {
                case 0: return 3; // 고요한 유령
                case 1: return 1; // 열정 넘치는 유령
                case 2: return 2; // 흥많은 유령
                case 3: return 4; // 영향력 있는 유령
                default: return 5; // 신비로운 유령
            }
        }
    }
}

