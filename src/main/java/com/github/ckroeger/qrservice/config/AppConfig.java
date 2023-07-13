package com.github.ckroeger.qrservice.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class AppConfig {

    private String serverUrl = "http://localhost:8080";
    private String serverDescription = "A service to create qrcodes as image";

    @Bean
    public OpenAPI springUserOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("SpringUser API")
                        .description("Spring user sample application")
                        .version("v0.0.1") // TODO from POM
                        .license(new License()
                                .name("MIT License")
                                .url("http://www.opensource.org/licenses/mit-license.php")))
                .servers(List.of(new Server().url(serverUrl).description(serverDescription)));
    }
}
