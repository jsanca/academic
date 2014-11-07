package spring.tutorial.aop4;

public class IdGen {

    private long id = 0;

    public synchronized long getCurrentId () {

        return this.id;
    }

    public synchronized void genNewId () {

        this.id += 1;
    }
}
