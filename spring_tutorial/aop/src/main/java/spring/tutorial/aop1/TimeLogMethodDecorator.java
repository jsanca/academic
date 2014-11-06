package spring.tutorial.aop1;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class TimeLogMethodDecorator implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {

        Object result = null;

        System.out.println("Invoking the method: " + methodInvocation.getMethod().getName());

        long now = System.currentTimeMillis();

        result = methodInvocation.proceed();

        System.out.println("The method: " + methodInvocation.getMethod().getName() +
                " call took: " + ((System.currentTimeMillis() - now)/1000) + " seconds");

        return result;
    }
}
