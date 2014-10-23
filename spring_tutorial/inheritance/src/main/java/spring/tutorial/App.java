package spring.tutorial;

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

        ApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("applicationContext.xml");

        System.out.println(applicationContext.getBean("baseClass"));
        System.out.println(applicationContext.getBean("subClass"));

        try {

            System.out.println(applicationContext.getBean("abstractClass"));

        } catch (Exception e) {

            e.printStackTrace(System.out);
        }

        System.out.println(applicationContext.getBean("concreteClass"));
        System.out.println(applicationContext.getBean("concreteOverrideClass"));

    }
}
