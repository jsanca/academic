package spring.tutorial;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import spring.tutorial.service.HelloWorld;

/**
 * Hello world!
 *
 */
public class FileSystemAppContext
{
    public static void main( String[] args )
    {

        //http://docs.spring.io/spring/docs/2.5.x/reference/beans.html#context-introduction-ctx-vs-beanfactory
        final ApplicationContext applicationContext =
                getFileSystemAppContext();

        final HelloWorld helloWorld =
                (HelloWorld)applicationContext.getBean("helloWorld");

        System.out.println(helloWorld.getMessage());
    }

    private static ApplicationContext getFileSystemAppContext() {

        final FileSystemXmlApplicationContext applicationContext =
                new FileSystemXmlApplicationContext("./config/applicationContext.xml");

        return applicationContext;
    }
}
