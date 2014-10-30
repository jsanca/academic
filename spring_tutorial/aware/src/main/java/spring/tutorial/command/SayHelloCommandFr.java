package spring.tutorial.command;

public class SayHelloCommandFr implements SayHelloCommand {

    @Override
    public void hello() {

        System.out.println("Bonjour tout le monde");
    }
}
