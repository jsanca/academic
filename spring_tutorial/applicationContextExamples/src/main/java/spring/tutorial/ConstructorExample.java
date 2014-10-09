package spring.tutorial;

public class ConstructorExample {

    private String someValue;

    public ConstructorExample(String someValue) {

        this.someValue = someValue;
    }

    public ConstructorExample(int someValue) {

        this.someValue = "Number: " + Integer.toString(someValue);
    }

    @Override
    public String toString() {

        return this.someValue;
    }
}
