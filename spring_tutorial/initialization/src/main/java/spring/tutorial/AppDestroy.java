package spring.tutorial;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 *
 */
public class AppDestroy
{
    public static void main( String[] args )
    {

        ApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("applicationContext.xml");

        System.out.println(applicationContext.getBean("myBean"));
        System.out.println(applicationContext.getBean("myBean2"));

        System.out.println(applicationContext.getBean("myBeanWithInitInterface"));
        System.out.println(applicationContext.getBean("myBeanWithInitInterface2"));
    }
}
