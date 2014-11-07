package spring.tutorial.aop3;

import org.springframework.aop.framework.ProxyFactory;
import spring.tutorial.aop1.MyMessage;
import spring.tutorial.aop2.BeforeCallLog;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {

        final SecurityManager securityManager = new SecurityManager();

        final MyMessage myMessageTarget = new MyMessage();
        final ProxyFactory proxyFactory = new ProxyFactory();

        proxyFactory.addAdvice(new SecurityBeforeAdvise());
        proxyFactory.setTarget(myMessageTarget);

        final MyMessage myMessageProxy =
                (MyMessage) proxyFactory.getProxy();

        // good credentials
        securityManager.login("admin", "12345");

        myMessageProxy.sayHello();


        // wrong credentials
        try {

            securityManager.login("admin", "wrong");
            myMessageProxy.sayHello();
        } catch (SecurityException e) {

            System.out.println(e.getMessage());
        } finally {

            securityManager.logout();
        }

        // without credentials
        try {

            myMessageProxy.sayHello();
        } catch (SecurityException e) {

            System.out.println(e.getMessage());
        }

    }
}
