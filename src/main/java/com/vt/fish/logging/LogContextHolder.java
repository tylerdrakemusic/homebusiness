package com.vt.fish.logging;

import javax.servlet.http.HttpServletRequest;

public class LogContextHolder {

    private LogContextHolder(){}

    private static final ThreadLocal<LogContext> localLogContextHolder = new ThreadLocal<>();

    public static LogContext getLogContext() {return localLogContextHolder.get();}

    public  static void initLogContext( LogContext parentContext) {
        LogContext context = getLogContext();
        if(context == null && parentContext != null) {
            context = new LogContext(parentContext);
            localLogContextHolder.set(context);
            context.setup();
        }
    }

    public static void initLogContext(HttpServletRequest request) {
        LogContext context = getLogContext();
        if(context == null) {
            context = new LogContext(request);
            localLogContextHolder.set(context);
        }
        context.setup();
    }

    public static void cleanLogContext(){
        LogContext logContext = getLogContext();
        if(logContext!= null && logContext.tearDown()){
            localLogContextHolder.remove();
        }
    }

    public static LogContext currentLogContext() {
        LogContext accessor = localLogContextHolder.get();

        if (accessor==null){
            throw new IllegalStateException("No log context available.  Use @VibrantLog annotation above method to make.");
        }
        return accessor;
    }
}
