package com.vt.fish.logging;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedList;

public class LogContext {

    private String vibrantTropicalRequestId = null;

    private final LinkedList<Boolean> initStack = new LinkedList<>();

    LogContext(LogContext parent) {

        vibrantTropicalRequestId = parent.vibrantTropicalRequestId;
    }

    LogContext(HttpServletRequest request) {
    }

    public String getVibrantTropicalRequestId() {
        return vibrantTropicalRequestId;
    }

    void setup() {
        initStack.push(initStack.isEmpty());
    }

    boolean tearDown() {
        return initStack.pop();
    }
}
