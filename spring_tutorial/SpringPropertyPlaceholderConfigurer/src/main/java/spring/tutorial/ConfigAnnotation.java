package spring.tutorial;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service("configAnnotation")
public class ConfigAnnotation {

    @Value("${property.config.name1}")
    private String name1;

    @Value("${property.config.name2}")
    private String name2;

    @Value("${property.config.nickname}")
    private String nickName;

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

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ConfigAnnotation{");
        sb.append("name1='").append(name1).append('\'');
        sb.append(", name2='").append(name2).append('\'');
        sb.append(", nickName='").append(nickName).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
