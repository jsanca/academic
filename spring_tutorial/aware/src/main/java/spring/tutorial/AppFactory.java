package spring.tutorial;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import spring.tutorial.command.SayHelloCommand;

/**
 * Hello world!
 *
 */
public class AppFactory
{
    public static void main( String[] args )
    {

        ApplicationContext applicationContext =
                new ClassPathXmlApplicationContext
                        ("applicationContext.xml");

        SayHelloFactory sayHelloFactory =
                applicationContext.getBean(SayHelloFactory.class);

        SayHelloCommand sayHelloCommand =
                sayHelloFactory.createSayHelloCommand("Esp");

        sayHelloCommand.hello();

        sayHelloCommand =
                sayHelloFactory.createSayHelloCommand("Eng");

        sayHelloCommand.hello();

        sayHelloCommand =
                sayHelloFactory.createSayHelloCommand("Fr");

        sayHelloCommand.hello();

        sayHelloCommand =
                sayHelloFactory.createSayHelloCommand("Por");

        sayHelloCommand.hello();

    }
}
