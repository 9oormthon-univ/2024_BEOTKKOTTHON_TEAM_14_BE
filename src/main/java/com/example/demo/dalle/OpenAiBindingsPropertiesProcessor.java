/*
 * Copyright (c) 2023 VMware, Inc. or its affiliates
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.demo.dalle;


import org.springframework.cloud.bindings.boot.BindingsPropertiesProcessor;
import org.springframework.core.env.Environment;
import org.springframework.cloud.bindings.Bindings;
import java.util.Map;

public class OpenAiBindingsPropertiesProcessor implements BindingsPropertiesProcessor {
    public static final String TYPE = "openai";

    @Override
    public void process(Environment environment, Bindings bindings, Map<String, Object> properties) { //openai와 관련된 바인딩에서 시크릿 키와 api를 가져와 속성 맵에 추가 -> 설정 정보 자동 로드
        bindings.filterBindings(TYPE).forEach(binding -> { //필터링
            properties.putIfAbsent("openai.key", binding.getSecret().get("key"));
            properties.putIfAbsent("openai.api", binding.getSecret().get("api"));
        });
    }
}
