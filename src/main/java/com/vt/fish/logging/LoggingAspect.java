package com.vt.fish.logging;

import com.vt.fish.logging.annotation.VibrantLog;
import io.micrometer.core.instrument.util.StringUtils;
import org.apache.juli.logging.Log;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.expression.BeanFactoryResolver;
import org.springframework.context.expression.MapAccessor;
import org.springframework.core.annotation.Order;
import org.springframework.expression.Expression;
import org.springframework.expression.common.TemplateParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.lang.NonNull;
import org.aspectj.lang.annotation.Aspect;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


@Aspect
@Order(0)
public class LoggingAspect {

    private final SpelExpressionParser spelExpressionParser = new SpelExpressionParser();
    private final TemplateParserContext templateParserContext = new TemplateParserContext();

    @NonNull
    private final VibrantLogger vibrantLogger;
    @NonNull
    private final Optional<HttpServletRequest> httpServletRequestOptional;
    @NonNull
    private final BeanFactory beanFactory;

    public LoggingAspect(@NonNull VibrantLogger vibrantLogger, @NonNull Optional<HttpServletRequest> httpServletRequestOptional, @NonNull BeanFactory beanFactory) {
        this.vibrantLogger = vibrantLogger;
        this.httpServletRequestOptional = httpServletRequestOptional;
        this.beanFactory = beanFactory;
    }

    @Before(value = "execution(* *(..)) && @annotation(vibrantLog)")
    public void beforeLogAspectAnnotation(JoinPoint joinPoint, VibrantLog vibrantLog) {
        LogContextHolder.initLogContext(httpServletRequestOptional.orElse(null));
        StandardEvaluationContext evaluationContext = getEvaluationContext(joinPoint);
        pushContext(joinPoint, evaluationContext, vibrantLog);

        if (!StringUtils.isEmpty(vibrantLog.before())) {
            //todo vibrantLog.level();
            String vibrantTropicalRequestId = "";
            if (!StringUtils.isEmpty(vibrantLog.vibrantTropicalRequestId())) {
                vibrantTropicalRequestId = vibrantLog.vibrantTropicalRequestId();
            }
            vibrantLogger.info(evaluateExpression(evaluationContext, vibrantLog.before()
                    + " VibrantTropicalRequestId: " + vibrantTropicalRequestId
                    + " CorrelationId:" + getCorrelationId(httpServletRequestOptional.get()), String.class));
        }
    }

    @AfterReturning(pointcut = "execution(* *(..)) && @annotation(vibrantLog)", returning = "result")
    public void afterReturningLogAspectAnnotation(JoinPoint joinPoint, VibrantLog vibrantLog, Object result) {
        StandardEvaluationContext evaluationContext = getEvaluationContext(joinPoint);
        String vibrantTropicalRequestId = "";
        if (!StringUtils.isEmpty(vibrantLog.after())) {
            if (!StringUtils.isEmpty(vibrantLog.vibrantTropicalRequestId())) {
                vibrantTropicalRequestId = vibrantLog.vibrantTropicalRequestId();
            }
            vibrantLogger.info(evaluateExpression(evaluationContext, vibrantLog.after()
                    + " VibrantTropicalRequestId: " + vibrantTropicalRequestId
                    + " CorrelationId:" + getCorrelationId(httpServletRequestOptional.get()), String.class)
            );
        }
        LogContextHolder.cleanLogContext();
    }

    private StandardEvaluationContext getEvaluationContext(JoinPoint joinPoint) {
        Map<String, Object> arguments = getArguments(joinPoint);
        if (httpServletRequestOptional.isPresent()) {
            arguments.put("$httpServletRequest", httpServletRequestOptional.get());
        }
        arguments.put("$logContext", LogContextHolder.getLogContext());
        StandardEvaluationContext evaluationContext = new StandardEvaluationContext(arguments);
        evaluationContext.addPropertyAccessor(new MapAccessor());
        evaluationContext.setBeanResolver(new BeanFactoryResolver(beanFactory));
        return evaluationContext;
    }

    private void pushContext(JoinPoint joinPoint, StandardEvaluationContext standardEvaluationContext, VibrantLog vibrantLog) {

    }

    private Map<String, Object> getArguments(JoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String[] parameterNames = methodSignature.getParameterNames();

        Map<String, Object> arguments = new HashMap<>();
        for (int i = 0; i < joinPoint.getArgs().length; i++) {
            String name = parameterNames[i];
            Object value = joinPoint.getArgs()[i];
            arguments.put(name, value);
        }
        return arguments;
    }

    private String getCorrelationId(HttpServletRequest httpServletRequest) {
        String correlationId = null;
        if (StringUtils.isNotBlank(httpServletRequest.getHeader("CorrelationId"))) {
            correlationId = httpServletRequest.getHeader("CorrelationId");
        }
        return correlationId;
    }

    private <T> T evaluateExpression(StandardEvaluationContext standardEvaluationContext, String expression, Class<T> evaluateTo) {
        try {
            Expression exp = spelExpressionParser.parseExpression(expression, this.templateParserContext);
            return exp.getValue(standardEvaluationContext, evaluateTo);
        } catch (Exception e) {
            vibrantLogger.error("Error evaluating expression " + expression + ". Value will be replaced with null.");
            return null;
        }
    }

}
