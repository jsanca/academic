package spring.tutorial;

public class MyBean {
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void init() {

        System.out.println("Init MyBean");

        if (null == name) {

            this.name = "Anonymous";
        }
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("MyBean{");
        sb.append("name='").append(name).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
