package com.vt.fish.logging;

public class LogContextRunnable implements Runnable {

    private final Runnable runnable;
    private final LogContext parentLogContext;

    public LogContextRunnable (Runnable runnable) {
        this.runnable = runnable;
        this.parentLogContext = LogContextHolder.getLogContext();
    }

    @Override
    public void run(){
        LogContextHolder.initLogContext(this.parentLogContext);
        try {
            runnable.run();
        } finally {
            LogContextHolder.cleanLogContext();
        }
    }
}
