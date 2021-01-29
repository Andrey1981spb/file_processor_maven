package ru.spb.voskhod.file_processor_maven.config;

import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.spb.voskhod.file_processor_maven.metrics.TimeMetrics;

@Configuration
public class MetricsConfig {
    @Autowired
    private MeterRegistry meterRegistry;
    @Bean
    public TimeMetrics getCounterMetrics() {
        return new TimeMetrics(meterRegistry);
    }
}
