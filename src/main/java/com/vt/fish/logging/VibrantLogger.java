package com.vt.fish.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VibrantLogger {
    Logger logger = LoggerFactory.getLogger(VibrantLogger.class);

    public void info(String message){
        this.logger.info(message);
    }

    public void debug(String message){
        this.logger.debug(message);
    }

    public void trace(String message){
        this.logger.trace(message);
    }

    public void warn(String message){
        this.logger.warn(message);
    }

    public void error(String message){
        this.logger.error(message);
    }
}
