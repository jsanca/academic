package spring.tutorial.events;

import org.springframework.context.ApplicationListener;

/**
 *
 * This one sends an email anytime that the user get logged into the app
 *
 * @author jsanca
 */
public class EmailStatsLoginListener implements ApplicationListener<LoginEvent> {

    @Override
    public void onApplicationEvent(LoginEvent loginEvent) {


        System.out.println("Sending an email to: " + loginEvent.getUser().getEmail());
    }
} // E:O:F:LoggerUserLoginListener
