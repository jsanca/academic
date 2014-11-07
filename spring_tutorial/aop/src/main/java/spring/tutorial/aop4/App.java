package spring.tutorial.aop4;

import org.springframework.aop.framework.ProxyFactory;
import spring.tutorial.aop1.MyMessage;
import spring.tutorial.aop3.SecurityBeforeAdvise;
import spring.tutorial.aop3.SecurityManager;

import java.util.HashMap;
import java.util.Map;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {

        final SaveSomethingService saveSomethingService =
                new SaveSomethingService();
        final ProxyFactory proxyFactory = new ProxyFactory();

        proxyFactory.addAdvice(new IdGeneratorAdvise());
        proxyFactory.setTarget(saveSomethingService);

        final SaveSomethingService proxy =
                (SaveSomethingService) proxyFactory.getProxy();

        final Map<String, Object> contextMap =
                new HashMap<String, Object>();

        proxy.save(contextMap);

        proxy.save(contextMap);

        proxy.save(contextMap);

    }
}
