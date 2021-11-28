package com.vt.fish.logging;

import java.util.function.Supplier;

public class LogContextSupplier <T> implements Supplier <T> {

    private final Supplier<T> supplier;
    private final LogContext parentLogContext;

    public LogContextSupplier (Supplier <T> supplier){
        this.supplier = supplier;
        this.parentLogContext = LogContextHolder.getLogContext();
    }

    @Override
    public T get() {
        LogContextHolder.initLogContext(this.parentLogContext);
        try{
            return supplier.get();
        } finally {
            LogContextHolder.cleanLogContext();
        }
    }
}
