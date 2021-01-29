package ru.spb.voskhod.file_processor_maven.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties (prefix = "spring.kafka.producer")
@Getter
@Setter
public class KafkaProperties {

    public String retries;

}
