package spring.tutorial;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {

        ApplicationContext applicationContext = null;

        applicationContext =
                new ClassPathXmlApplicationContext("applicationContext.xml");

        ConstructorExample constructorExample1 =
                (ConstructorExample) applicationContext.getBean("constructorExample1");

        ConstructorExample constructorExample2 =
                (ConstructorExample) applicationContext.getBean("constructorExample2");

        System.out.println( "Constructor 1: " + constructorExample1.toString() );
        System.out.println( "Constructor 2: " + constructorExample2.toString() );

        PropertyExample propertyExample =
                (PropertyExample) applicationContext.getBean("propertyExample");

        System.out.println( "PropertyExample : " + propertyExample.toString() );

        MapCollectionExample mapCollectionExample =
                (MapCollectionExample) applicationContext.getBean("mapCollectionExample");

        System.out.println( "mapCollectionExample : " + mapCollectionExample );

        Date date        = (Date) applicationContext.getBean("date1");
        Date birthdate   = (Date) applicationContext.getBean("birthdate");
        Date anniversary = (Date) applicationContext.getBean("anniversary");

        System.out.println( "Date comparison" );
        System.out.println( date == birthdate );
        System.out.println( date == anniversary );

    }
}
