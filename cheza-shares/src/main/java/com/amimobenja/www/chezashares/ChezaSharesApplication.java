package com.amimobenja.www.chezashares;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ChezaSharesApplication {

    private static final Logger logger = LogManager.getLogger(ChezaSharesApplication.class);

    public static void main(String[] args) {
        logger.info("Starting the Cheza Shares Application");
        SpringApplication.run(ChezaSharesApplication.class, args);
    }

}
