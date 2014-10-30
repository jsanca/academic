package spring.tutorial.command;

public class SayHelloCommandEsp implements SayHelloCommand {

    @Override
    public void hello() {

        System.out.println("Hola Mundo");
    }
}
