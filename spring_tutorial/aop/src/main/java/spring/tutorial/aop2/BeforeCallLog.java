package spring.tutorial.aop2;

import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

public class BeforeCallLog implements MethodBeforeAdvice {


    @Override
    public void before(final Method   method,
                       final Object[] arguments,
                       final Object   target) throws Throwable {

        System.out.println("Invoking the method: " + method.getName());
    }
}
