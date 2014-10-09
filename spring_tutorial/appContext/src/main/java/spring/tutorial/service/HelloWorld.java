package spring.tutorial.service;

public class HelloWorld {

    private String message;

    public String getMessage() {
        return "Your Message : " + message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
