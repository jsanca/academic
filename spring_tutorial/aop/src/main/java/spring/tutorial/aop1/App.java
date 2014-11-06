package spring.tutorial.aop1;

import org.springframework.aop.framework.ProxyFactory;

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

        proxyFactory.addAdvice(new TimeLogMethodDecorator());
        proxyFactory.setTarget(myMessageTarget);

        final MyMessage myMessageProxy =
                (MyMessage) proxyFactory.getProxy();

        System.out.println("Calling just the target...");

        myMessageTarget.sayHello();


        System.out.println("\nCalling the proxy...");

        myMessageProxy.sayHello();

    }
}
