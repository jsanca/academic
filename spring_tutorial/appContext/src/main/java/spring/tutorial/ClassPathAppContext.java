package spring.tutorial;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import spring.tutorial.service.HelloWorld;

/**
 * Hello world!
 *
 */
public class ClassPathAppContext
{
    public static void main( String[] args )
    {

        //http://docs.spring.io/spring/docs/2.5.x/reference/beans.html#context-introduction-ctx-vs-beanfactory
        final ApplicationContext applicationContext =
                getClassPathAppContext();

        final HelloWorld helloWorld =
                (HelloWorld)applicationContext.getBean("helloWorld");

        System.out.println(helloWorld.getMessage());
    }

    private static ApplicationContext getClassPathAppContext() {

        final ClassPathXmlApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("applicationContext.xml");

        return applicationContext;
    }
}
