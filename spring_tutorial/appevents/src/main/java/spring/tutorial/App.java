package spring.tutorial;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import spring.tutorial.bean.User;
import spring.tutorial.events.LoginEventPublisher;

/**
 * Shows how the events works in the spring
 *
 */
public class App {

    public static void main( String[] args ) {

        final ConfigurableApplicationContext context =
                new ClassPathXmlApplicationContext("beans.xml");

        final LoginEventPublisher publisher =
                (LoginEventPublisher) context.getBean("loginEventPublisher");

        loginUser1(publisher);
        loginUser2(publisher);
    }

    public static void loginUser1(final LoginEventPublisher publisher) {

        final User user1 = new User("user 1", "user1@gmail.com");

        publisher.publish(user1);
    } // loginUser1.


    public static void loginUser2(final LoginEventPublisher publisher) {

        final User user2 = new User("user 2", "user2@hotmail.com");

        publisher.publish(user2);
    } // loginUser2.

} // E:O:F:App.
