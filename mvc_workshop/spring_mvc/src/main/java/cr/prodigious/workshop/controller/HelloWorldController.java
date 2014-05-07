package cr.prodigious.workshop.controller;

import cr.prodigious.CapabilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.Serializable;

/**
 * Hello word controller.
 *
 * call me like:
 * http://localhost:8080/spring_mvc/hello/?name=Jsanca
 *
 * Date: 4/11/14
 * Time: 12:27 PM
 * @author jsanca
 */
@Controller
public class HelloWorldController implements Serializable {


    private CapabilityService capabilityService;

    @Autowired
    public void setCapabilityService(CapabilityService capabilityService) {

        this.capabilityService = capabilityService;
    }

    /**
     * Just say hello and include all the capability guys
     * @param name String
     * @param model Model
     * @return String viewer name
     */
    @RequestMapping("/hello")
    public String hello(
            @RequestParam(value="name", required=false, defaultValue="World") final String name,
            final Model model) {

        model.addAttribute("name", name);

        model.addAttribute("capabilityList",
                this.capabilityService.getCapabilityList());

        return "helloworld";
    } // hello
} // E:O:F:HelloWorldController.
