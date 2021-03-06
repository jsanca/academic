package spring.tutorial;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {

        final ApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("applicationContext.xml");

        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                DefaultListableBeanFactory.class.cast
                        (applicationContext.getAutowireCapableBeanFactory()).destroySingletons();
            }
        }));

        System.out.println(applicationContext.getBean("myBean"));
        System.out.println(applicationContext.getBean("myBean2"));

        System.out.println(applicationContext.getBean("myBeanWithInitInterface"));
        System.out.println(applicationContext.getBean("myBeanWithInitInterface2"));


    }
}
