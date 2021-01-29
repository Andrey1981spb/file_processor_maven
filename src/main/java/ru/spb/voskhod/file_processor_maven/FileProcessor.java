package ru.spb.voskhod.file_processor_maven;

import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.spb.voskhod.file_processor_maven.metrics.TimeMetrics;
import ru.spb.voskhod.file_processor_maven.model.ReadFile;
import ru.spb.voskhod.file_processor_maven.model.WriteFile;

import java.time.Duration;
import java.util.function.Function;

@Configuration
public class FileProcessor {

    @Autowired
    private MeterRegistry meterRegistry;

    public TimeMetrics getTimeMetrics() {
        return new TimeMetrics(meterRegistry);
    }

    @Bean
    public Function<ReadFile, WriteFile> processFile() {

        TimeMetrics timeMetrics = getTimeMetrics();
        Duration duration = null;
        long start = System.currentTimeMillis();

        return readFile -> {
            WriteFile writeFile = new WriteFile();
            int lineAvgLength = 0;
            //    lineAvgLength = lineAvgLength + readFile.getDate().toString().length();
            lineAvgLength = lineAvgLength + readFile.getOperation().length();
            lineAvgLength = lineAvgLength + readFile.getAmount().toString().length();
            lineAvgLength = lineAvgLength + readFile.getItemOfExpenditure().length();
            lineAvgLength = lineAvgLength + readFile.getOldBalance().toString().length();
            writeFile.setLineAvgLength(lineAvgLength / 5);

            long end = System.currentTimeMillis();
            long durationInSeconds = (long) ((end - start) / 1000F);
            timeMetrics.countDuration(duration.plusSeconds(durationInSeconds));

            return writeFile;
        };

    }

}
