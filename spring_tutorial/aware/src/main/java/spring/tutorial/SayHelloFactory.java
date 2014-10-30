package spring.tutorial;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import spring.tutorial.command.SayHelloCommand;
import spring.tutorial.command.SayHelloCommandEng;

import java.util.Map;

public class SayHelloFactory implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    private Map<String, SayHelloCommand> commandMap;

    private static final SayHelloCommandEng DEFAULT_SAY_HELLO_COMMAND =
            new SayHelloCommandEng();

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

        this.applicationContext = applicationContext;
    }


    public void setCommandMap(Map<String, SayHelloCommand> commandMap) {
        this.commandMap = commandMap;
    }

    public SayHelloCommand createSayHelloCommand (String lang) {

        SayHelloCommand sayHelloCommand = DEFAULT_SAY_HELLO_COMMAND;

        if(this.applicationContext.containsBean("sayHello" + lang)) {

            sayHelloCommand =
                    (SayHelloCommand)this.applicationContext.getBean("sayHello" + lang);
        }

        return sayHelloCommand;
    }
}
