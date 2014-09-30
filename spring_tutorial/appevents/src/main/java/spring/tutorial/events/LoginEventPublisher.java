package spring.tutorial.events;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import spring.tutorial.bean.User;

/**
 * User Login events publisher
 *
 * @author jsanca
 */
public class LoginEventPublisher implements ApplicationEventPublisherAware {

    private ApplicationEventPublisher applicationEventPublisher = null;

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {

        this.applicationEventPublisher =
                applicationEventPublisher;
    }

    /**
     * Publish a login user.
     * @param user User
     */
    public void publish (User user) {

        final LoginEvent loginEvent =
                new LoginEvent(this, user);

        this.applicationEventPublisher.publishEvent(loginEvent);
    } // publish
} // E:O:F:LoginEventPublisher
