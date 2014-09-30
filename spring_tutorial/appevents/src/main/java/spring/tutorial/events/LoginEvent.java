package spring.tutorial.events;

import org.springframework.context.ApplicationEvent;
import spring.tutorial.bean.User;

/**
 * This event is triggered when an user access the app (sucessfully login)
 */
public class LoginEvent extends ApplicationEvent {

    private User user = null;

    public LoginEvent(Object source, User user) {

        super(source);
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("LoginEvent{");
        sb.append("user=").append(user);
        sb.append('}');
        return sb.toString();
    }
} // E:O:F:LoginEvent.
