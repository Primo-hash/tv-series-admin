package no.abdulhadi.tvseriesadmin.service.external.api;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.web.reactive.config.WebFluxConfigurer;

@Configuration
public class WebFluxConfiguration implements WebFluxConfigurer {
    public static final int MAX_WEB_BUFFER_SIZE = 3 * 1024 * 1024;

    @Override
    public void configureHttpMessageCodecs(ServerCodecConfigurer configurer) {
        configurer.defaultCodecs().maxInMemorySize(MAX_WEB_BUFFER_SIZE);
    }
}
