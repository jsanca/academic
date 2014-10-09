package spring.tutorial;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring.tutorial.config.HelloWorldConfig;
import spring.tutorial.config.HelloWorldConfigScan;
import spring.tutorial.moreservices.YeahBuddyComponent;
import spring.tutorial.otherservices.HelloWorldComponent;
import spring.tutorial.service.HelloWorld;

public class AnnotationConfigAppContext2 {

    public static void main( String[] args )
    {

        final ApplicationContext applicationContext =
                getAnnotationConfigApplicationContext();

        final HelloWorldComponent helloWorld =
                applicationContext.getBean(HelloWorldComponent.class);

        helloWorld.sayHello();

        final YeahBuddyComponent yeahBuddy =
                (YeahBuddyComponent) applicationContext.getBean("yeahBuddy");

        yeahBuddy.sayHello();
    }

    private static ApplicationContext getAnnotationConfigApplicationContext() {

        final AnnotationConfigApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(HelloWorldConfigScan.class);
        // AnnotationConfigWebApplicationContext the same for web


        return applicationContext;
    }
}
