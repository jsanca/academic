package spring.tutorial.aop2;

import org.springframework.aop.framework.ProxyFactory;
import spring.tutorial.aop1.MyMessage;
import spring.tutorial.aop1.TimeLogMethodDecorator;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {

        final MyMessage myMessageTarget = new MyMessage();
        final ProxyFactory proxyFactory = new ProxyFactory();

        proxyFactory.addAdvice(new BeforeCallLog());
        proxyFactory.setTarget(myMessageTarget);

        final MyMessage myMessageProxy =
                (MyMessage) proxyFactory.getProxy();

        myMessageProxy.sayHello();

    }
}
