package spring.tutorial.command;

public class SayHelloCommandEng implements SayHelloCommand {

    @Override
    public void hello() {

        System.out.println("Hello World");
    }
}
