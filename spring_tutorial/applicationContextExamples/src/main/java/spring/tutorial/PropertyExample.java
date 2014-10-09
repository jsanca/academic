package spring.tutorial;

import java.util.Date;

public class PropertyExample {
    private String name;
    private boolean isDeveloper;
    private Date birthDate;
    private float height;
    private int yearsWorking;
    private ConstructorExample aObject;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setIsDeveloper(boolean isDeveloper) {
        this.isDeveloper = isDeveloper;
    }

    public boolean getIsDeveloper() {
        return isDeveloper;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getHeight() {
        return height;
    }

    public void setYearsWorking(int yearsWorking) {
        this.yearsWorking = yearsWorking;
    }

    public int getYearsWorking() {
        return yearsWorking;
    }

    @Override
    public String toString() {

        final StringBuilder sb = new StringBuilder("PropertyExample{");
        sb.append("name='").append(name).append('\'');
        sb.append(", isDeveloper=").append(isDeveloper);
        sb.append(", birthDate=").append(birthDate);
        sb.append(", height=").append(height);
        sb.append(", yearsWorking=").append(yearsWorking);
        sb.append(", ConstructorExample=").append(aObject);
        sb.append('}');
        return sb.toString();
    }

    public void setaObject(ConstructorExample aObject) {
        this.aObject = aObject;
    }

    public ConstructorExample getaObject() {
        return aObject;
    }
}
