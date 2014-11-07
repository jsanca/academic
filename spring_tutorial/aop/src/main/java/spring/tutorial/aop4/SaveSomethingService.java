package spring.tutorial.aop4;

import java.util.Map;

public class SaveSomethingService {

    public void save (Map<String, Object> context) {

        long id = (Long)context.get("id");

        System.out.println("Saving the id: "  + id);
    }

}
