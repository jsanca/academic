package spring.tutorial.command;

public class SayHelloCommandPor implements SayHelloCommand {

    @Override
    public void hello() {

        System.out.println("Olá mundo");
    }
}
