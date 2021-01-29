package ru.spb.voskhod.file_processor_maven.metrics;

import io.micrometer.core.instrument.Timer;
import io.micrometer.core.instrument.MeterRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import ru.spb.voskhod.file_processor_maven.FileProcessorMavenApplication;

import java.time.Duration;

@Component
public class TimeMetrics {
    Logger logger = LoggerFactory.getLogger(FileProcessorMavenApplication.class);
    private MeterRegistry meterRegistry;
    private Timer timer;

    public TimeMetrics(final MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
        initCounter();
        logger.info("\nCounterMetrics created!");
    }

    private void initCounter() {
        timer = Timer.builder("time.measure")
                .tag("type", "time")
                .description("The duration of operation")
                .register(meterRegistry);
    }

    public void countDuration(final Duration duration) {
        timer.record(duration);
    }

}
