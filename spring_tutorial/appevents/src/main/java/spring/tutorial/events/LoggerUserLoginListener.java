package spring.tutorial.events;

import org.springframework.context.ApplicationListener;

/**
 *
 * This one login the user logins.
 *
 * @author jsanca
 */
public class LoggerUserLoginListener implements ApplicationListener<LoginEvent> {

    @Override
    public void onApplicationEvent(LoginEvent loginEvent) {


        System.out.println(loginEvent.toString());
    }
} // E:O:F:LoggerUserLoginListener
