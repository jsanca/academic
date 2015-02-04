package spring.tutorial;

public class Target {

    private Bean1 bean1;
    private Bean1 beanTwo;

    private Bean2 bean2;

    public Target() {

    }

    public Target(Bean1 bean1) {

        System.out.print("Target(Bean1) called");
        this.bean1 = bean1;
    }

    public Target(Bean1 beanOne, Bean2 bean2) {

        System.out.print("Target(Bean1, Bean2) called");
        this.bean1 = bean1;
    }

    public void setBean1(Bean1 bean1) {
        this.bean1 = bean1;
        System.out.print("Property bean1 set");
    }

    public void setBeanTwo(Bean1 beanTwo) {
        this.beanTwo = beanTwo;
        System.out.print("Property bean1 set");
    }

    public void setMyBean2Property(Bean2 bean2) {
        this.bean2 = bean2;
        System.out.print("Property bean1 set");
    }
}
