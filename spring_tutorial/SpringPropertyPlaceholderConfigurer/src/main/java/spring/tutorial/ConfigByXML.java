package spring.tutorial;

public class ConfigByXML {

    private String name1;

    private String name2;

    private String name3;

    public String getName1() {
        return name1;
    }

    public void setName1(String name1) {
        this.name1 = name1;
    }

    public String getName2() {
        return name2;
    }

    public void setName2(String name2) {
        this.name2 = name2;
    }

    public String getName3() {
        return name3;
    }

    public void setName3(String name3) {
        this.name3 = name3;
    }

    @Override
    public String toString() {



        final StringBuilder sb = new StringBuilder("ConfigByXML{");
        sb.append("name1='").append(name1).append('\'');
        sb.append(", name2='").append(name2).append('\'');
        sb.append(", name3='").append(name3).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
