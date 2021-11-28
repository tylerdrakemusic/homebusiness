package com.vt.fish.logging;

import org.springframework.core.task.TaskDecorator;

public class LogContextTaskDecorator implements TaskDecorator {

    @Override
    public Runnable decorate(Runnable runnable) {
        return new LogContextRunnable(runnable);
    }
}
