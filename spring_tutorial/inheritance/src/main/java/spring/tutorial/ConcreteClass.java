package spring.tutorial;

/**
 * Created by jsanchez8 on 10/22/14.
 */
public class ConcreteClass extends AbstractClass {

    private String alias;

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("SubClass{");
        sb.append("alias='").append(alias).append('\'').append(" ");
        sb.append(super.toString());
        sb.append('}');
        return sb.toString();
    }

    @Override
    public void sayHello() {

        System.out.println("Helloooo");
    }
}
