package spring.tutorial.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import spring.tutorial.service.HelloWorld;

@Configuration
public class HelloWorldConfig {

    @Bean
    public HelloWorld helloWorld(){

        final HelloWorld helloWorld =
                new HelloWorld();

        helloWorld.setMessage("Hello World annotation lovers!");

        return helloWorld;
    }
}
