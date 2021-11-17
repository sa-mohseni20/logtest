package com.baeldung;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import io.sentry.Sentry;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(DemoApplication.class);

        Sentry.init(options -> {
            options.setDsn("https://e6529b101fb1462caf5957bf0ffa935c@o1069467.ingest.sentry.io/6064128");
            options.setEnvironment("development");
        });

        SpringApplication.run(DemoApplication.class, args);

        try {
            throw new Exception("This is another test.");
        } catch (Exception e) {
            logger.warn("A warning appeared");
            logger.error("EVENT: "+ e.toString());
            Sentry.captureMessage("A new message captured.");
            Sentry.captureException(e);
        }
    }

}
