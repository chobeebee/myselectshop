package com.sparta.myselectshop.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

//RestTemplate 수동 Bean 등록
//RestTemplate은 추가적으로 옵션을 사용하는 경우가 많아서 수동으로 Bean등록을 하는 편
@Configuration
public class RestTemplateConfig  {
    // 방법 1.
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
        return restTemplateBuilder
                // RestTemplate 으로 외부 API 호출 시 일정 시간이 지나도 응답이 없을 때
                // 무한 대기 상태 방지를 위해 강제 종료 설정
                .connectTimeout(Duration.ofSeconds(5)) // 5초
                .readTimeout(Duration.ofSeconds(5)) // 5초
                .build();
    }

    // 방법 2.
    /*@Bean
    public RestClient restTemplate() {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setConnectTimeout(5 * 1000);
        factory.setReadTimeout(5 * 1000);

        return RestClient.builder()
                .requestFactory(factory)
                .build();
//        return restTemplateBuilder
//                // RestTemplate 으로 외부 API 호출 시 일정 시간이 지나도 응답이 없을 때
//                // 무한 대기 상태 방지를 위해 강제 종료 설정
//                .setConnectTimeout(Duration.ofSeconds(5)) // 5초
//                .setReadTimeout(Duration.ofSeconds(5)) // 5초
//                .build();
    }*/
}