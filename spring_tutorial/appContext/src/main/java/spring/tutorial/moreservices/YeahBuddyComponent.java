package spring.tutorial.moreservices;

import org.springframework.stereotype.Component;

@Component("yeahBuddy")
public class YeahBuddyComponent {

    public void sayHello () {

        System.out.println("Yeaaaaah buddy");
    }
}
