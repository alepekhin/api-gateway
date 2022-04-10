package alepekhin.apigateway.config;

import io.micrometer.core.aop.TimedAspect;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {

    /**
     * TimedAspect создает метрику для оценки времени выполнения
     * метода с аннотацией @Timed(имя метрики)
     * Значение метрики доступно по адресу /actuator/metrics/имя метрики
     */
    @Bean
    TimedAspect timedAspect(MeterRegistry registry) {
        return new TimedAspect(registry);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
