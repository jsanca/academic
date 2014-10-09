package spring.tutorial.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import spring.tutorial.service.HelloWorld;

@Configuration
@ComponentScan({"spring.tutorial.moreservices", "spring.tutorial.otherservices"})
public class HelloWorldConfigScan {


}
