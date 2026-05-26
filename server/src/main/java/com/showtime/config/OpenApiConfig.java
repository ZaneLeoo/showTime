package com.showtime.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI showTimeOpenAPI() {
        return new OpenAPI()
            .info(new Info()
                .title("ShowTime 演出订票系统 API")
                .description("C端用户接口：演出浏览、选座、下单、订单管理、用户中心")
                .version("1.0.0"))
            .components(new Components()
                .addSecuritySchemes("Bearer",
                    new SecurityScheme()
                        .type(SecurityScheme.Type.HTTP)
                        .scheme("bearer")
                        .bearerFormat("session-token")
                        .description("登录后获取的 token，Header 中传 Authorization: Bearer <token>")));
    }
}
