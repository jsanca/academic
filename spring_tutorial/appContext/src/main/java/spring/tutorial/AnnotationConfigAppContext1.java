package spring.tutorial;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import spring.tutorial.config.HelloWorldConfig;
import spring.tutorial.service.HelloWorld;

public class AnnotationConfigAppContext1 {

    public static void main( String[] args )
    {

        final ApplicationContext applicationContext =
                getAnnotationConfigApplicationContext();

        final HelloWorld helloWorld =
                applicationContext.getBean(HelloWorld.class);

        System.out.println(helloWorld.getMessage());
    }

    private static ApplicationContext getAnnotationConfigApplicationContext() {

        final AnnotationConfigApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(HelloWorldConfig.class);



        return applicationContext;
    }
}
