package spring.tutorial.aop4;

import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;
import spring.tutorial.aop3.SecurityManager;

import java.lang.reflect.Method;
import java.util.Map;

public class IdGeneratorAdvise implements MethodBeforeAdvice, AfterReturningAdvice {

    private IdGen idGen = new IdGen();

    @Override
    public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {



        idGen.genNewId();
    } // afterReturning.

    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {

        Map<String, Object> contextMap = null;

        if ((null != args) && (args.length > 0)) {

            if (args[0] instanceof Map) {

                contextMap = (Map)args[0];

                if (null != contextMap) {

                    contextMap.put("id", this.idGen.getCurrentId());
                }
            }
        }
    } // before
}
