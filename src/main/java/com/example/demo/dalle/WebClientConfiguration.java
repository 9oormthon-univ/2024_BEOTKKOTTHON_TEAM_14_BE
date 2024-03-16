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

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

@Configuration
public class WebClientConfiguration {
    @Bean
    WebClient.Builder webClientBuilder() { //webClient를 설정하고 구성
        return WebClient.builder()
                .defaultHeader(HttpHeaders.USER_AGENT, "dallecool") //모든 http 요청에 대한 기본 사용자 에이전트 헤더를 dallecool로 설정
                .clientConnector(new ReactorClientHttpConnector(HttpClient.create().proxyWithSystemProperties())); //프록시 구성
    }

    @Bean
    WebClient webClient(WebClient.Builder clientBuilder) { //webClient 인스턴스 생성
        return clientBuilder.build();
    }
}
