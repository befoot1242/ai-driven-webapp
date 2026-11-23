package com.example.app.common;

import java.time.Clock;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/** 現在時刻をテストで差し替えられるよう Clock を Bean にする。 */
@Configuration
public class ClockConfig {
    @Bean
    Clock clock() {
        return Clock.systemDefaultZone();
    }
}
