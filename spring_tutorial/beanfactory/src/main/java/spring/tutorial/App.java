package spring.tutorial;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.PropertiesBeanDefinitionReader;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {

        final BeanFactory factory =
                getBeanFactory();
                // Deprecated
                //new XmlBeanFactory
                //(new ClassPathResource("Beans.xml"));

        HelloWorld obj = (HelloWorld) factory.getBean("helloWorld");
        obj.getMessage();
    }

    private static BeanFactory getBeanFactory () {

        final DefaultListableBeanFactory factory =
                new DefaultListableBeanFactory();

        final XmlBeanDefinitionReader definitionReader =
                new XmlBeanDefinitionReader(factory);

        definitionReader.loadBeanDefinitions
                (new ClassPathResource("Beans.xml"));

        return factory;
    }
}
